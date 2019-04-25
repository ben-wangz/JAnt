package tech.geekcity.open.JAnt.api.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Output {
    String outputFieldName() default "";
}
