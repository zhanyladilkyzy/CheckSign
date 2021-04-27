import java.util.List;

public class Header {
    private List<String> x5c;
    private String alg;

    public Header(List<String> x5c, String alg) {
        this.x5c = x5c;
        this.alg = alg;
    }

    public List<String> getX5c() {
        return x5c;
    }

    public void setX5c(List<String> x5c) {
        this.x5c = x5c;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }
}
