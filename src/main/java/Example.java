import com.google.gson.Gson;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.openssl.PEMWriter;
import org.omg.IOP.Encoding;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.*;

public class Example {

    /**
     * ???
     */
    private static InputStream key;

    /**
     * ?????? ?? ?????????? ?????
     */
    private static String password = "D123456d";

    /**
     * ?????? ??? ???????
     */
    private static byte[] data = "{\"extRefNo\":\"2106143110755000031\",\"srvId\":121,\"amount\":44.00,\"currency\":\"KZT\",\"fee\":0.0,\"point\":\"\",\"srvParams\":[{\"code\":\"kbk\",\"value\":\"101201\"},{\"code\":\"knp\",\"value\":\"911\"},{\"code\":\"tax\",\"value\":\"620601\"},{\"code\":\"iin\",\"value\":\"891229302127\"},{\"code\":\"name\",\"value\":\"????????? ???? ??????????\"},{\"code\":\"vin\",\"value\":\"4USBT53544LT26841\"}]}".getBytes();
    // private static byte[] data = "\\u007b\\u0022\\u0065\\u0078\\u0074\\u0052\\u0065\\u0066\\u004e\\u006f\\u0022\\u003a\\u0022\\u0032\\u0031\\u0030\\u0036\\u0031\\u0034\\u0033\\u0031\\u0031\\u0030\\u0037\\u0035\\u0035\\u0030\\u0030\\u0030\\u0030\\u0033\\u0031\\u0022\\u002c\\u0022\\u0073\\u0072\\u0076\\u0049\\u0064\\u0022\\u003a\\u0031\\u0032\\u0031\\u002c\\u0022\\u0061\\u006d\\u006f\\u0075\\u006e\\u0074\\u0022\\u003a\\u0034\\u0034\\u002e\\u0030\\u0030\\u002c\\u0022\\u0063\\u0075\\u0072\\u0072\\u0065\\u006e\\u0063\\u0079\\u0022\\u003a\\u0022\\u004b\\u005a\\u0054\\u0022\\u002c\\u0022\\u0066\\u0065\\u0065\\u0022\\u003a\\u0030\\u002e\\u0030\\u002c\\u0022\\u0070\\u006f\\u0069\\u006e\\u0074\\u0022\\u003a\\u0022\\u0022\\u002c\\u0022\\u0073\\u0072\\u0076\\u0050\\u0061\\u0072\\u0061\\u006d\\u0073\\u0022\\u003a\\u005b\\u007b\\u0022\\u0063\\u006f\\u0064\\u0065\\u0022\\u003a\\u0022\\u006b\\u0062\\u006b\\u0022\\u002c\\u0022\\u0076\\u0061\\u006c\\u0075\\u0065\\u0022\\u003a\\u0022\\u0031\\u0030\\u0031\\u0032\\u0030\\u0031\\u0022\\u007d\\u002c\\u007b\\u0022\\u0063\\u006f\\u0064\\u0065\\u0022\\u003a\\u0022\\u006b\\u006e\\u0070\\u0022\\u002c\\u0022\\u0076\\u0061\\u006c\\u0075\\u0065\\u0022\\u003a\\u0022\\u0039\\u0031\\u0031\\u0022\\u007d\\u002c\\u007b\\u0022\\u0063\\u006f\\u0064\\u0065\\u0022\\u003a\\u0022\\u0074\\u0061\\u0078\\u0022\\u002c\\u0022\\u0076\\u0061\\u006c\\u0075\\u0065\\u0022\\u003a\\u0022\\u0036\\u0032\\u0030\\u0036\\u0030\\u0031\\u0022\\u007d\\u002c\\u007b\\u0022\\u0063\\u006f\\u0064\\u0065\\u0022\\u003a\\u0022\\u0069\\u0069\\u006e\\u0022\\u002c\\u0022\\u0076\\u0061\\u006c\\u0075\\u0065\\u0022\\u003a\\u0022\\u0038\\u0039\\u0031\\u0032\\u0032\\u0039\\u0033\\u0030\\u0032\\u0031\\u0032\\u0037\\u0022\\u007d\\u002c\\u007b\\u0022\\u0063\\u006f\\u0064\\u0065\\u0022\\u003a\\u0022\\u006e\\u0061\\u006d\\u0065\\u0022\\u002c\\u0022\\u0076\\u0061\\u006c\\u0075\\u0065\\u0022\\u003a\\u0022\\u0413\\u041e\\u041b\\u041e\\u0412\\u0410\\u0422\\u041e\\u0412\\u0020\\u0418\\u0412\\u0410\\u041d\\u0020\\u041d\\u0418\\u041a\\u041e\\u041b\\u0410\\u0415\\u0412\\u0418\\u0427\\u0022\\u007d\\u002c\\u007b\\u0022\\u0063\\u006f\\u0064\\u0065\\u0022\\u003a\\u0022\\u0076\\u0069\\u006e\\u0022\\u002c\\u0022\\u0076\\u0061\\u006c\\u0075\\u0065\\u0022\\u003a\\u0022\\u0034\\u0055\\u0053\\u0042\\u0054\\u0035\\u0033\\u0035\\u0034\\u0034\\u004c\\u0054\\u0032\\u0036\\u0038\\u0034\\u0031\\u0022\\u007d\\u005d\\u007d".getBytes();


    public static void main(String[] args) throws Exception {
        String filePath = "src/payload.txt";
        System.out.println("read payload from file");
        String payload2 = readAllBytesJava7(filePath);
       // payload2 = Base64.getEncoder().encodeToString(payload2.getBytes("UTF-8"));

        Provider provider = new KalkanProvider();
        Security.addProvider(provider);
        final File initialFile = new File("/Users/zhanyl/GOST.p12");
        key = new DataInputStream(new FileInputStream(initialFile));

        KeyStore store = KeyStore.getInstance("PKCS12", provider.getName());

        store.load(key, password.toCharArray());

        Enumeration<String> aliases = store.aliases();
        String alias = null;

        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
        }

        PrivateKey privateKey = (PrivateKey) store.getKey(alias, password.toCharArray());


        Gson gson = new Gson();
//        Payload data = gson.fromJson(payload2, Payload.class);
//        System.out.println(data.getExtRefNo());
        SendData result = new SendData();
        result.setPayload(payload2);

        X509Certificate x509Certificate = (X509Certificate) store.getCertificate(alias);
        System.out.println("payload2 >> " + payload2);
        //payload2 = Base64.getEncoder().encodeToString(payload2.getBytes());
        String sigAlg = x509Certificate.getSigAlgOID();
        System.out.println("sigAlg: " + sigAlg);
        Signature sgn = Signature.getInstance(sigAlg, provider.getName());
        sgn.initSign(privateKey);
        sgn.update(payload2.getBytes());
//???????? ????????? ????? ????? ??? ????? ==
        System.out.println("payload: " + payload2);
        //   System.out.println("payload1: " + Base64.getEncoder().encodeToString(payload1.getBytes()));
        byte[] signature = sgn.sign();
        System.out.println("signature: " + Base64.getEncoder().encodeToString(signature));


        List<String> certs = Collections.singletonList(
                convertToBase64PEMString(x509Certificate).
                        replaceAll("[\r\n]+", "").
                        replace("-----BEGIN CERTIFICATE-----", "").
                        replace("-----END CERTIFICATE-----", ""));
        System.out.println("certs: SN" + x509Certificate.getSerialNumber());
        System.out.println("certs: " + certs);
        List<MySignature> mySign = new ArrayList<MySignature>();
        Header header = new Header(certs, "ECGOST34310");

        mySign.add(new MySignature(header, Base64.getEncoder().encodeToString(signature)));
        result.setSignatures(mySign);
        String res = gson.toJson(result);
        System.out.println("res = "+res);
        System.out.println("UTF-8 res.base64 = "+Base64.getEncoder().encodeToString(res.getBytes()));
        System.out.println("res.base64 = "+Base64.getEncoder().encodeToString(res.getBytes()));
//        byte[] bytes = Encoding.UTF8.GetBytes("abcdef==");
//        String base64 = Convert.ToBase64String(bytes);
//        Console.WriteLine(base64);
    }


    public static String convertToBase64PEMString(X509Certificate x509Cert) throws IOException {
        StringWriter sw = new StringWriter();

        try (PEMWriter pw = new PEMWriter(sw)) {
            pw.writeObject(x509Cert);
        }

        return sw.toString();
    }

//    public static String encode(String e){
//        String ready = null;
//        char[] ch = new char[e.length()];
//        for(int i = 0; i  < e.length(); i++){
//            if (ch[i] != '='){
//                ready += Base64.getEncoder().encodeToString(ch[i].getBytes());
//            }else{
//                continue;
//            }
//        }
//        System.out.println("ready = "+ready);
//        return ready;
//    }

    private static String readAllBytesJava7(String filePath) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}

