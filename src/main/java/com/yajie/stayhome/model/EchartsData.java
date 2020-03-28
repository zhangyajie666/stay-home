package com.yajie.stayhome.model;

import java.util.List;

/**
 * @author Lenovo
 */
public class EchartsData {
    private String name;
    private String type;
    private List<Integer> data;

    public EchartsData() {
    }

    public EchartsData(String name, String type, List<Integer> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EchartsData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
