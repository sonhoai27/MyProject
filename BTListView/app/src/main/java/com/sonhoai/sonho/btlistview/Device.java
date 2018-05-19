package com.sonhoai.sonho.btlistview;

public class Device {
    private String deviceName;
    private int idCategory;

    public Device(String deviceName, int idCategory) {
        this.deviceName = deviceName;
        this.idCategory = idCategory;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
