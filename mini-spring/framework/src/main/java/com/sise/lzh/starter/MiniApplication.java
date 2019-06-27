package com.sise.lzh.starter;

import com.sise.lzh.beans.BeanFactory;
import com.sise.lzh.core.ClassScanner;
import com.sise.lzh.web.handler.HandlerManager;
import com.sise.lzh.web.server.TomcatServer;

import java.util.List;

import static com.sise.lzh.beans.BeanFactory.initBean;

/**
 * <p>Title: MiniApplication</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 11:05
 */
public class MiniApplication {
    public static void run(Class<?> cls,String[] args){
        System.out.println("Hello Mini-spring");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach( it -> System.out.println(it.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
