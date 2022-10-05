
package com.example.retrofit.pojo;


import com.google.gson.annotations.SerializedName;

public class ProductColor {

    @SerializedName("colour_name")
    private String mColourName;
    @SerializedName("hex_value")
    private String mHexValue;

    public String getColourName() {
        return mColourName;
    }

    public void setColourName(String colourName) {
        mColourName = colourName;
    }

    public String getHexValue() {
        return mHexValue;
    }

    public void setHexValue(String hexValue) {
        mHexValue = hexValue;
    }

}
