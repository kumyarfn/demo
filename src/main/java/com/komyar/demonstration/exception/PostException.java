package com.komyar.demonstration.exception;
import com.komyar.demonstration.enums.ResultMessage;

public class PostException extends BaseException {
    public PostException(String message, Integer code) {
        super(message, code);
    }

    public PostException(ResultMessage exceptionConstant) {
        super(exceptionConstant);
    }
}
