package model;

import java.util.List;

public class JwsHeader
{
    private List<String> x5c;

    private String alg;

    public JwsHeader(List<String> x5c, String alg) {
        this.x5c = x5c;
        this.alg = alg;
    }

    public void setX5c(List<String> x5c){
        this.x5c = x5c;
    }
    public List<String> getX5c(){
        return this.x5c;
    }
    public void setAlg(String alg){
        this.alg = alg;
    }
    public String getAlg(){
        return this.alg;
    }
}
