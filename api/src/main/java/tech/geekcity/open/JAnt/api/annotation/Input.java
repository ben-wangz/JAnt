package tech.geekcity.open.JAnt.api.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Input {
    Class upstreamClass() default Object.class;

    String upstreamOutputFieldName();
}
