package model;

import java.util.List;

public class Jws
{
    private String payload;

    private List<JwsSignature> signatures;

    public void setPayload(String payload){
        this.payload = payload;
    }
    public String getPayload(){
        return this.payload;
    }
    public void setSignatures(List<JwsSignature> signatures){
        this.signatures = signatures;
    }
    public List<JwsSignature> getSignatures(){
        return this.signatures;
    }
}
