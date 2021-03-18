public class MySignature {
    private Header header;
    private String signature;

    public MySignature(Header header, String signature) {
        this.header = header;
        this.signature = signature;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
