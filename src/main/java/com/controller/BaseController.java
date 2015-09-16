package com.controller;

import com.exception.AppException;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Created by kumarke on 9/14/15.
 */
public abstract class BaseController {

    private static final Logger logger  = Logger.getLogger(BaseController.class);

    @ExceptionHandler(AppException.class)
    public void handleServerException(HttpServletRequest request, HttpServletResponse response, AppException e){
        logger.warn("Handling exception for:" + request.getRequestURI() + e.getMessage() + e.getStatusCode());
        try{
            String msg = "{ \"error\" : \"" + e.getErrMessage() + "\"}";
            response.getWriter().write(msg);
            response.setStatus(e.getStatusCode());
            response.setContentType(MediaType.APPLICATION_JSON.toString());
        }catch(IOException ioe){
            logger.error("Unable to process error",ioe);
        }
    }

}
