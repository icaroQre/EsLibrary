package com.controller;

import java.sql.Date;

public class LoanController {
    
    Date loanDate;
    Date expectedDate;
    Date returnDate;
    Float penalty;
    Boolean delay;
    Float value;

    @SuppressWarnings("exports")
    public LoanController (Date loanDate, Date expectedDate, Date returnDate, Float penalty, Boolean delay, Float value) {
        this.loanDate = loanDate;
        this.expectedDate = expectedDate;
        this.returnDate = returnDate;
        this.penalty = penalty;
        this.delay = delay;
        this.value = value;
    }

    
}
