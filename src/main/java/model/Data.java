package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable
{
    private int id;

    private int srvId;

    private Double amount;

    private String currency;

    private String extRefNo;

    private String point;

    private String status;

    private String statusName;

    private String createDate;

    private String finishDate;

    private List<SrvParams> srvParams;

    private List<Register> register;

    private String accept;

    private String terminal;

    private Double fee;

    public String toJson() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setSrvId(int srvId){
        this.srvId = srvId;
    }
    public int getSrvId(){
        return this.srvId;
    }
    public void setAmount(Double amount){
        this.amount = amount;
    }
    public Double getAmount(){
        return this.amount;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }
    public void setExtRefNo(String extRefNo){
        this.extRefNo = extRefNo;
    }
    public String getExtRefNo(){
        return this.extRefNo;
    }
    public void setPoint(String point){
        this.point = point;
    }
    public String getPoint(){
        return this.point;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatusName(String statusName){
        this.statusName = statusName;
    }
    public String getStatusName(){
        return this.statusName;
    }
    public void setCreateDate(String createDate){
        this.createDate = createDate;
    }
    public String getCreateDate(){
        return this.createDate;
    }
    public void setFinishDate(String finishDate){
        this.finishDate = finishDate;
    }
    public String getFinishDate(){
        return this.finishDate;
    }
    public void setSrvParams(List<SrvParams> srvParams){
        this.srvParams = srvParams;
    }
    public List<SrvParams> getSrvParams(){
        return this.srvParams;
    }
    public void setRegister(List<Register> register){
        this.register = register;
    }
    public List<Register> getRegister(){
        return this.register;
    }
    public void setAccept(String accept){
        this.accept = accept;
    }
    public String getAccept(){
        return this.accept;
    }
    public void setTerminal(String terminal){
        this.terminal = terminal;
    }
    public String getTerminal(){
        return this.terminal;
    }
    public void setFee(Double fee){
        this.fee = fee;
    }
    public Double getFee(){
        return this.fee;
    }
}
