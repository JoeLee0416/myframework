package com.sise.lzh.beans;

import java.lang.annotation.*;

/**
 * <p>Title: AutoWired</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/6/27 15:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoWired {
}
