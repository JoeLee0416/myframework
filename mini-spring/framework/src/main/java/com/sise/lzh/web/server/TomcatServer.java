package com.sise.lzh.web.server;

import com.sise.lzh.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * <p>Title: TomcatServer</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 11:18
 */
public class TomcatServer {
    private Tomcat tomcat;
    private String[] args;

   public TomcatServer(String[] args){
        this.args = args;
    }

    /**
     * 启动Tomcat
     */
    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        //设置Tomcat监听窗口为6999
        tomcat.setPort(6999);
        tomcat.start();

        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());

        DispatcherServlet servlet = new DispatcherServlet();
        Tomcat.addServlet(context,"dispatcherServlet",
                servlet).setAsyncSupported(true);
        context.addServletMappingDecoded("/","dispatcherServlet");
        tomcat.getHost().addChild(context);

         //防止服务器中途退出,设置常驻线程
        Thread awaitThread = new Thread("tomcat_await_thread"){
            @Override
            public void run() {
                TomcatServer.this.tomcat.getServer().await();
            }
        };
        //设置为非守护线程
        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}
