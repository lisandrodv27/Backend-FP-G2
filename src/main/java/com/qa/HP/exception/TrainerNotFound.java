package com.qa.HP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task doesn't exist with that ID")
public class TrainerNotFound extends RuntimeException {

}
