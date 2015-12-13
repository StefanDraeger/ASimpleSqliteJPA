package de.stefandraegersoftware.asimplesqlitejpa.database.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Column {
    String name();
    String type();
    boolean isPrimaryKey() default false;
    int maxLength() default 1000;

}
