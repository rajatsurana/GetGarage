package com.getgarage.cards;
public class DataObject {
    private String mText1;
    private String mText2;
    private String mText3;
    private String mText4;
    private byte[] mImg1;

    public  DataObject (byte[] img1,String text1, String text2,String text3, String text4){
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
        mText4 = text4;
        mImg1=img1;

    }
    public byte[] getmImg1() {
        return mImg1;
    }

    public void setmImg1(byte[] mImg1) {
        this.mImg1 = mImg1;
    }
    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
    public String getmText3() {
        return mText3;
    }

    public void setmText3(String mText3) {
        this.mText3 = mText3;
    }
    public String getmText4() {
        return mText4;
    }

}