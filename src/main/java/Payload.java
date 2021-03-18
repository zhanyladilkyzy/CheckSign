import java.util.List;

public class Payload {
    private String extRefNo;
    private int srvId;
    private double amount;
    private String currency;
    private double fee;
    private String point;
    private List<ServiceParameters> srvParams;


    public Payload(String extRefNo, int srvId, double amount, String currency, double fee, String point, List<ServiceParameters> srvParams) {
        this.extRefNo = extRefNo;
        this.srvId = srvId;
        this.amount = amount;
        this.currency = currency;
        this.fee = fee;
        this.point = point;
        this.srvParams = srvParams;
    }

    public String getExtRefNo() {
        return extRefNo;
    }

    public void setExtRefNo(String extRefNo) {
        this.extRefNo = extRefNo;
    }

    public int getSrvId() {
        return srvId;
    }

    public void setSrvId(int srvId) {
        this.srvId = srvId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public List<ServiceParameters> getSrvParams() {
        return srvParams;
    }

    public void setSrvParams(List<ServiceParameters> srvParams) {
        this.srvParams = srvParams;
    }
}
