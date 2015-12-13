package de.stefandraegersoftware.database.datasource;


import de.stefandraegersoftware.database.annotations.Column;
import de.stefandraegersoftware.database.annotations.Columns;
import de.stefandraegersoftware.database.annotations.Table;
import de.stefandraegersoftware.database.exception.AnnotationNotFoundException;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDataSource<T> {

    private Class<T> entity;

    private final static String UNDEFINED = "-undefined-";

    public AbstractDataSource(Class<T> entity) {
        this.entity = entity;
    }

    public abstract void readFromTable();

    public String toString() {
        final String lineBreak = "\r\n";
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getCanonicalName());
        buffer.append("{");
        buffer.append(lineBreak);
        buffer.append("\tTablename: " + getTablename());
        buffer.append(lineBreak);
        Column[] columns = getColumns();
        List<Column> columnList = Arrays.asList(columns);
        buffer.append("\tColumns: [");
        for (int i = 0; i < columnList.size(); i++) {
            Column value = columnList.get(i);
            buffer.append("Name: " + value.name());
            buffer.append("\tType: " + value.type());
            if (i < (columnList.size() - 1)) {
                buffer.append(",");
            }
        }
        buffer.append("]");
        buffer.append(lineBreak);
        buffer.append("}");
        return buffer.toString();
    }

    public String getTablename() {
        String tablename = UNDEFINED;
        if (isAnnotationPresent(Table.class)) {
            Annotation annotation = entity.getAnnotation(Table.class);
            Table table = (Table) annotation;
            tablename = table.tablename();
        }
        return tablename;
    }

    public Column[] getColumns() {
        Column[] column = null;
        if (isAnnotationPresent(Columns.class)) {
            Annotation annotation = entity.getAnnotation(Columns.class);
            Columns columns = (Columns) annotation;
            column = columns.value();
        }
        return column;
    }

    protected boolean isAnnotationPresent(Class clazz) {
        boolean result = entity.isAnnotationPresent(clazz);
        if (!result) {
            throw new AnnotationNotFoundException(clazz.getCanonicalName(), entity.getCanonicalName());
        }
        return entity.isAnnotationPresent(clazz);
    }
}
