package com.example.doulai.demo;



/**
 * Created by CMCC-ZHENGCHENG on 2017/3/31.
 */


public class PrintProperty {

    private Long _id;

    private String printProperty;
    private boolean propertyEnabe;


    public PrintProperty() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getPrintProperty() {
        return printProperty;
    }

    public void setPrintProperty(String printProperty) {
        this.printProperty = printProperty;
    }

    public boolean isPropertyEnabe() {
        return propertyEnabe;
    }

    public void setPropertyEnabe(boolean propertyEnabe) {
        this.propertyEnabe = propertyEnabe;
    }

    public boolean getPropertyEnabe() {
        return this.propertyEnabe;
    }
}
