package keene.demo.coursedemo;

import java.io.Serializable;

/**
 * this file belongs to package com.example.coursedemo of My Application
 * created at 14:58,07,2016
 *
 * @author keene
 * @version 1.0
 */
public class DataModel implements Serializable{

    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
