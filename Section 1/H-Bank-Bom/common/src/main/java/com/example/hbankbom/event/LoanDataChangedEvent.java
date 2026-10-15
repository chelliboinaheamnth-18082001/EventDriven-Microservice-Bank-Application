package com.example.hbankbom.event;

import lombok.Data;

@Data
public class LoanDataChangedEvent {

    private String mobileNumber;
    private Long loanNumber;

}
