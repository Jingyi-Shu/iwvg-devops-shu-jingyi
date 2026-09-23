package es.upm.miw.devops.rest.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}