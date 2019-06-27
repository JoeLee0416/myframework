package com.sise.lzh.web.handler;

import com.sise.lzh.beans.BeanFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>Title: MappingHandler</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 14:30
 */
public class MappingHandler {
    private String uri;
    private Method method;
    private Class<?> controller;
    private String[] args;

    public boolean handle(ServletRequest req, ServletResponse res)
            throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest)req).getRequestURI();
        if(!uri.equals(requestUri)){
            return false;
        }
        Object[] parameters = new Object[args.length];
        for(int i = 0;i < args.length;i++){
            parameters[i] = req.getParameter(args[i]);
        }
        Object ctl = BeanFactory.getBean(controller);
        Object reponse = method.invoke(ctl,parameters);
        res.getWriter().println(reponse.toString());
        return true;
    }

    public MappingHandler(String uri,Method method,Class<?> cls,String[] args){
        this.uri = uri;
        this.method = method;
        this.controller = cls;
        this.args = args;
    }
}
