package com.otoomo.iocaopmvc.test.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {
    String[] userName() default {};
}
