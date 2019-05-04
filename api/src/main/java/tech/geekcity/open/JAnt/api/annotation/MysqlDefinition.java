package tech.geekcity.open.JAnt.api.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MysqlDefinition {
    String driverClassName() default "com.mysql.jdbc.Driver";

    String url();

    String username();

    String password();

    String sql();
}
