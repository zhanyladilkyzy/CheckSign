import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.openssl.PEMWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Example {

    /**
     * ЭЦП
     */
    private static InputStream key;

    /**
     * Пароль от приватного ключа
     */
    private static String password="D123456d";

    /**
     * Данные для подписи
     */
    private static byte[] data = "{\"extRefNo\":\"2106143110755000031\",\"srvId\":41,\"amount\":44.00,\"currency\":\"KZT\",\"fee\":0.0,\"point\":\"\",\"srvParams\":[{\"code\":\"kbk\",\"value\":\"101201\"},{\"code\":\"knp\",\"value\":\"911\"},{\"code\":\"tax\",\"value\":\"620601\"},{\"code\":\"iin\",\"value\":\"891229302127\"},{\"code\":\"name\",\"value\":\"ГОЛОВАТОВ ИВАН НИКОЛАЕВИЧ\"},{\"code\":\"vin\",\"value\":\"4USBT53544LT26841\"}]}".getBytes();
    public static void main(String[] args) throws Exception {
            Provider provider = new KalkanProvider();
            Security.addProvider(provider);
            final File initialFile = new File("C:\\Users\\1\\GOST.p12");
            key =
                    new DataInputStream(new FileInputStream(initialFile));

            KeyStore store = KeyStore.getInstance("PKCS12", provider.getName());

            store.load(key, password.toCharArray());

            Enumeration<String> aliases = store.aliases();
            String alias = null;

            while (aliases.hasMoreElements()) {
                alias = aliases.nextElement();
            }

            PrivateKey privateKey = (PrivateKey) store.getKey(alias, password.toCharArray());

            String payload = new String(data);

            X509Certificate x509Certificate = (X509Certificate) store.getCertificate(alias);

            String sigAlg = x509Certificate.getSigAlgOID();
            System.out.println("sigAlg: " + sigAlg);
            Signature sgn = Signature.getInstance(sigAlg, provider.getName());
            sgn.initSign(privateKey);
            sgn.update(payload.getBytes());
            System.out.println("Привет, мир!!");
            System.out.println("payload: " + payload);
            System.out.println("payload: " + Base64.getEncoder().encodeToString(payload.getBytes()));

            byte[] signature = sgn.sign();
            System.out.println("signature: " + Base64.getEncoder().encodeToString(signature));


        List<String> certs = Collections.singletonList(
                    convertToBase64PEMString(x509Certificate).
                            replaceAll("[\r\n]+", "").
                            replace("-----BEGIN CERTIFICATE-----", "").
                            replace("-----END CERTIFICATE-----", ""));

            System.out.println("certs: " + certs);
        }

    public static String convertToBase64PEMString(X509Certificate x509Cert) throws IOException {
        StringWriter sw = new StringWriter();

        try (PEMWriter pw = new PEMWriter(sw)) {
            pw.writeObject(x509Cert);
        }

        return sw.toString();
    }
}
