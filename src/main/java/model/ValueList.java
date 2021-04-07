package model;

import java.io.Serializable;

public class ValueList implements Serializable {
    private String value;

    private String note;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return this.note;
    }
}
