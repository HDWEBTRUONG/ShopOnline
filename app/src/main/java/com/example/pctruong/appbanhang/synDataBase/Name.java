package com.example.pctruong.appbanhang.synDataBase;

/**
 * Created by t420 on 25-Jan-18.
 */

public class Name {
    private String name;
    private int status;

    public Name(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
