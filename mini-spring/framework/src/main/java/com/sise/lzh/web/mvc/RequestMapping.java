package com.sise.lzh.web.mvc;

import java.lang.annotation.*;

/**
 * <p>Title: RequestMapping</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 13:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value();
}
