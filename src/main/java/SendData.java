import java.io.IOException;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class SendData implements Serializable{
    private String payload;
    private List<MySignature> signatures;

    public SendData() {
    }

    public SendData(String payload, List<MySignature> signatures) {
        this.payload = payload;
        this.signatures = signatures;
    }

    //public Payload getPayload() {
//        return payload;
//    }

    public void setPayload(String payload) throws IOException {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(bos);
//        oos.writeObject(payload);
//        oos.flush();
//        byte [] data = bos.toByteArray();
//        data = Base64.getEncoder().encode(data);
        this.payload = payload;// new Payload(data);
    }

    public List<MySignature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<MySignature> signatures) {
        this.signatures = signatures;
    }
}
