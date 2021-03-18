public class Payload {
    public String extRefNo;
    public int srvId;
    public double amount;
    public String currency;
    public double fee;
    public String point;
    ServiceParameters[] srvParams = new ServiceParameters[5];


    public Payload(String extRefNo, int srvId, double amount, String currency, double fee, String point, ServiceParameters[] srvParams) {
        this.extRefNo = extRefNo;
        this.srvId = srvId;
        this.amount = amount;
        this.currency = currency;
        this.fee = fee;
        this.point = point;
        this.srvParams = srvParams;
    }

}
