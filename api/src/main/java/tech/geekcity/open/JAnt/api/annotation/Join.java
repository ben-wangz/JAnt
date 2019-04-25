package tech.geekcity.open.JAnt.api.annotation;

import tech.geekcity.open.JAnt.api.Processor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Join {
    Class<? extends Processor> processorClass();

    String fieldName();

    String[] joinFieldNameList();
}
