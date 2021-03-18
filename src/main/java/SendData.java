import java.util.List;

public class SendData {
    private Payload payload;
    private List<MySignature> mySignatures;

    public SendData() {
    }

    public SendData(Payload payload, List<MySignature> mySignatures) {
        this.payload = payload;
        this.mySignatures = mySignatures;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public List<MySignature> getSignatures() {
        return mySignatures;
    }

    public void setSignatures(List<MySignature> mySignatures) {
        this.mySignatures = mySignatures;
    }
}
