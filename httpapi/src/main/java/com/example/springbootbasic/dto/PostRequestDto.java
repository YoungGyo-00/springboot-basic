package com.example.springbootbasic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequestDto {

    private String account;
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber; // if snake-case로 넘어 온다면 Null로 오기에 @JsonProperty를 붙여줌줌

   public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
