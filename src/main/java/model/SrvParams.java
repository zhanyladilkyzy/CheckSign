package model;

import java.io.Serializable;
import java.util.List;

public class SrvParams implements Serializable
{
    private String code;

    private String name;

    private String note;

    private String min;

    private String max;

    private String regexpMask;

    private String regexpChk;

    private String prmType;

    private String chkCode;

    private String value;

    private List<ValueList> valueList;

    public SrvParams(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setNote(String note){
        this.note = note;
    }
    public String getNote(){
        return this.note;
    }
    public void setMin(String min){
        this.min = min;
    }

    public String getMin(){
        return this.min;
    }
    public void setMax(String max){
        this.max = max;
    }
    public String getMax(){
        return this.max;
    }
    public void setRegexpMask(String regexpMask){
        this.regexpMask = regexpMask;
    }
    public String getRegexpMask(){
        return this.regexpMask;
    }
    public void setRegexpChk(String regexpChk){
        this.regexpChk = regexpChk;
    }
    public String getRegexpChk(){
        return this.regexpChk;
    }
    public void setPrmType(String prmType){
        this.prmType = prmType;
    }
    public String getPrmType(){
        return this.prmType;
    }
    public void setChkCode(String chkCode){
        this.chkCode = chkCode;
    }
    public String getChkCode(){
        return this.chkCode;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setValueList(List<ValueList> valueList){
        this.valueList = valueList;
    }
    public List<ValueList> getValueList(){
        return this.valueList;
    }
}
