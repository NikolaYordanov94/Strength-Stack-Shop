package bg.softuni.strengthstackshop.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalErrorHandlingController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleNotFound(ObjectNotFoundException exception) {


        return new ModelAndView("error/404");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerErrorException.class)
    public ModelAndView handleServerError(ServerErrorException serverErrorException) {


        return new ModelAndView("error/500");
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedError(AccessDeniedException accessDeniedException) {


        return new ModelAndView("error/405");
    }

}
