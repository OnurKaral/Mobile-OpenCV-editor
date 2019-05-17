package com.example.opencv_imageprocessing_android;

/**
 * Created by alper_beyler on 5.10.2017.
 */

public class User {
    private String userName;
    private String uId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public User(){

    }

    public User(String userName, String uId) {
        this.userName = userName;
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
