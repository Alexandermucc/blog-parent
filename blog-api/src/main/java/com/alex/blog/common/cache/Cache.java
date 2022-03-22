package com.alex.blog.common.cache;
import java.lang.annotation.*;

/**
 * @author Alexandermucc
 * @date 2022/3/22 - 20:00 - 周二
 **/


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 1 * 60 * 1000;

    String name() default "";

}

