package de.stefandraegersoftware.asimplesqlitejpa.database.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    String tablename() default "null";
}
