package com.example.hbankbom.event;

import lombok.Data;

@Data
public class AccountDataChangedEvent {

    private String mobileNumber;
    private Long accountNumber;

}
