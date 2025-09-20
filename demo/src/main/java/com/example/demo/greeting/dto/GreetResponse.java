package com.example.demo.greeting.dto;

public class GreetResponse {
    private String message;

    public GreetResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
