package model;

public class JwsSignature
{
    private JwsHeader header;

    private String signature;

    public JwsSignature(JwsHeader header, String signature) {
        this.header = header;
        this.signature = signature;
    }

    public void setHeader(JwsHeader header){
        this.header = header;
    }
    public JwsHeader getHeader(){
        return this.header;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public String getSignature(){
        return this.signature;
    }
}
