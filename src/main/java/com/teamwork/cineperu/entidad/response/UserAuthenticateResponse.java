package com.teamwork.cineperu.entidad.response;

public class UserAuthenticateResponse extends EntityWSBase{

    public String Token;
    public String UserImage;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }
}
