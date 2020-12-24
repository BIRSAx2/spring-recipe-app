package dev.mouhieddine.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Mouhieddine.dev
 * @since : 12/24/2020, Thursday
 **/
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handleNumberFormatException(Exception exception) {
    log.error("Handling not number error exception");
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("400error");
    modelAndView.addObject("exception", exception);
    modelAndView.setStatus(HttpStatus.BAD_REQUEST);
    return modelAndView;
  }
}
