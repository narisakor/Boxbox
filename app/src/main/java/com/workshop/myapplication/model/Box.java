package com.workshop.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Box {
    @SerializedName("box_id")
    private String boxId;

    @SerializedName("box_name")
    private String boxName;

    @SerializedName("is_available")
    private String available;

    private boolean isAvailable;

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public boolean isAvailable() {
        if(available.equals("1")){
            return true;
        }
        return false;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}
