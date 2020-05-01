package es.uji.ei1027.majorsacasa.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MajorsacasaControllerAdvice {
    @ExceptionHandler(value = MajorsacasaException.class)
    public ModelAndView handleMajorsException(MajorsacasaException e){
        ModelAndView mav = new ModelAndView("error/exceptionError");
        mav.addObject("message", e.getMessage());
        mav.addObject("errorName", e.getErrorName());
        return mav;
    }
}
