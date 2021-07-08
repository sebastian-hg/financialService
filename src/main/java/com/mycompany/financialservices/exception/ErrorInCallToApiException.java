package com.mycompany.financialservices.exception;

import com.universe.marvel.core.exception.BusinessApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.universe.marvel.exception.CountryExistException.MESSAGE;

@ResponseStatus(value = HttpStatus.FOUND, reason = MESSAGE)

public class ErrorInCallToApiException extends BusinessApiException {

    public static final transient String MESSAGE = "Country Exist";

    public ErrorInCallToApiException() {
        super();
    }
}
