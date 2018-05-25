package com.example.template.exception;

import org.springframework.validation.BindingResult;

public class CustomValidException extends Exception {

    private BindingResult bindingResult;

    public CustomValidException() {
        super();
    }

    public CustomValidException(String msg) {
        super(msg);
    }

    public CustomValidException(String msg, BindingResult bindingResult) {
        super(msg);
        this.bindingResult = bindingResult;
    }


    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
