package ru.wtrn.smsgw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectSecretException extends ResponseStatusException {
    public IncorrectSecretException() {
        super(HttpStatus.BAD_REQUEST, "Incorrect secret token provided");
    }
}
