package ru.itis.mq.app.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDto {

    private String name;
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    private String message;
}
