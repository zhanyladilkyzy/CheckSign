import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.openssl.PEMWriter;
import model.Data;
import model.Jws;
import model.JwsHeader;
import model.JwsSignature;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpClientExample {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() throws IOException {
        httpClient.close();
    }

    public void sendApprove(int transId, String token) throws IOException {
        System.out.println("TRANS_ID" + transId);
        HttpGet request = new HttpGet("https://api.bcc.kz/bcc/production/v1/pgs/PAP/transactions/"+transId);
        request.addHeader("Accept", "application/json");
        request.addHeader("authorization", "Bearer " + token);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            System.out.println("Response approval: " + EntityUtils.toString(response.getEntity()));
        }
    }

    public Data sendTransaction(String token, Data payload) throws Exception {
//       HttpPost post = new HttpPost("https://api.bcc.kz:10443/bcc/production/v1/pgs/PAP/transactions");
        HttpPost request = new HttpPost("https://api.bcc.kz/bcc/production/v1/pgs/PAP/transactions");
        request.addHeader("Accept", "application/json");
        request.addHeader("content-type", "application/json");
        String data = payload.toJson();

        request.addHeader("x-jws-signature", jwsSign(data));
        request.addHeader("authorization", "Bearer " + token);
        StringEntity requestEntity = new StringEntity(
                data,
                ContentType.APPLICATION_JSON);
        request.setEntity(requestEntity);
        System.out.println("Request: " + request.toString());
        System.out.println("Header: ");
        Header[] headers = request.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header.toString());
        }
        System.out.println("Body: "+data);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            String respString = EntityUtils.toString(response.getEntity());
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            System.out.println("Response transaction: " + respString);
            return gson.fromJson(respString, Data.class);
        }
    }

    public String jwsSign(String payload) throws Exception {
        String jws = "";
        String password = "D123456d";
        Provider provider = new KalkanProvider();
        Security.addProvider(provider);
        InputStream key = HttpClientExample.class.getClassLoader().getResourceAsStream("keys/GOST.p12");

        KeyStore store = KeyStore.getInstance("PKCS12", provider.getName());

        store.load(key, password.toCharArray());
        Enumeration<String> aliases = store.aliases();
        String alias = null;

        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
        }

        PrivateKey privateKey = (PrivateKey) store.getKey(alias, password.toCharArray());
        X509Certificate x509Certificate = (X509Certificate) store.getCertificate(alias);
        String sigAlg = x509Certificate.getSigAlgOID();
        Signature sgn = Signature.getInstance(sigAlg, provider.getName());
        sgn.initSign(privateKey);
        sgn.update(payload.getBytes());
        byte[] signature = sgn.sign();
        List<String> certs = Collections.singletonList(
                convertToBase64PEMString(x509Certificate).
                        replaceAll("[\r\n]+", "").
                        replace("-----BEGIN CERTIFICATE-----", "").
                        replace("-----END CERTIFICATE-----", ""));

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        Jws result = new Jws();

        List<JwsSignature> mySign = new ArrayList<>();
        JwsHeader header = new JwsHeader(certs, "ECGOST34310");

        mySign.add(new JwsSignature(header, Base64.getEncoder().encodeToString(signature)));
        result.setSignatures(mySign);
        String res = gson.toJson(result);

        return Base64.getEncoder().encodeToString(res.getBytes());
    }

    public AuthResponse sendAuth(String clientId, String clientSecret) throws Exception {
        HttpPost post = new HttpPost("https://api.bcc.kz/bcc/production/v1/oauth2/token");
        post.addHeader("content-type", "application/x-www-form-urlencoded");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
        urlParameters.add(new BasicNameValuePair("client_secret", clientSecret));
        urlParameters.add(new BasicNameValuePair("scope", "access_payment_gateway"));
        urlParameters.add(new BasicNameValuePair("api_type", "pgw"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            String respString = EntityUtils.toString(response.getEntity());
            System.out.println(respString);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.fromJson(respString, AuthResponse.class);
        }

    }


    public static String convertToBase64PEMString(X509Certificate x509Cert) throws IOException {
        StringWriter sw = new StringWriter();

        try (PEMWriter pw = new PEMWriter(sw)) {
            pw.writeObject(x509Cert);
        }

        return sw.toString();
    }

}
