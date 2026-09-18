package de.tudresden.socialrbscdeterministic.mas.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Motivation {
    Class<?> type();
    String target() default "";
    double value() default 0.0;
}

