package com.sagar.sprfullstack.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class SpringRedditException extends Throwable {

    public SpringRedditException(String exMessage) {
        super(exMessage);
    }
}
