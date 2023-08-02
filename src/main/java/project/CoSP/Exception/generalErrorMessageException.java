package project.CoSP.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class generalErrorMessageException extends RuntimeException{
    public generalErrorMessageException(String message){
        super(message);
    }
}