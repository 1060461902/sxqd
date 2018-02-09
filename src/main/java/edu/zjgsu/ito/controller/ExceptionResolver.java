package edu.zjgsu.ito.controller;

import edu.zjgsu.ito.pojo.Message;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理器
 */

@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message fail(Exception ex) {
        ex.printStackTrace();
        System.out.println("抛异常了！"+ex.getLocalizedMessage());
        return Message.createErr(ex.getMessage());
    }
}

