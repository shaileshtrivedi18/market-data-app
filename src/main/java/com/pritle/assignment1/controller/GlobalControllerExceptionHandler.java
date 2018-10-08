package com.pritle.assignment1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * © 2015 Pritle Holding B.V. - All Rights Reserved
 * <p>
 * See LICENSE file or http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest req, Exception e) {
        log.error("Unhandled exception on '{} {}'",
                req.getMethod(),
                req.getRequestURI() + (req.getQueryString() != null ? req.getQueryString() : ""),
                e
        );
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
