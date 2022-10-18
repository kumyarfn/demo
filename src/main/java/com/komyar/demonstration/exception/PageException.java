package com.komyar.demonstration.exception;


import com.komyar.demonstration.enums.ResultMessage;

public class PageException extends BaseException{
    public PageException(String message, Integer code) {
        super(message, code);
    }

    public PageException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
