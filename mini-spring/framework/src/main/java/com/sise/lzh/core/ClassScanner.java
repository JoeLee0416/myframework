package com.sise.lzh.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <p>Title: ClassScanner</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 14:07
 */
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName)
            throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".","/");
        //获取类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while(resources.hasMoreElements()){
            URL resource = resources.nextElement();
            if(resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection) resource
                        .openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFromJar(jarFilePath,path));
            }else {
                //只处理了资源类型为Jar包的情况，其他情况暂时为空
                //todo
            }
        }
        return classList;
    }

    private static List<Class<?>> getClassesFromJar(String jarFilePath,String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();

        while(jarEntries.hasMoreElements()){
            JarEntry jarEntry = jarEntries.nextElement();
            String entryName = jarEntry.getName();//com/sise/lzh/test.class
            if(entryName.startsWith(path) && entryName.endsWith(".class")){
                String classFullName = entryName.replace("/",".")
                        .substring(0,entryName.length()-6);
                classes.add(Class.forName(classFullName));
            }
        }
        return  classes;
    }
}
