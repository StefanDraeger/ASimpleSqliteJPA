package de.stefandraegersoftware.asimplesqlitejpa;

import de.stefandraegersoftware.asimplesqlitejpa.database.annotations.java.de.stefandraegersoftware.asimplesqlitejpa.database.annotations.Column;
import de.stefandraegersoftware.asimplesqlitejpa.database.annotations.java.de.stefandraegersoftware.asimplesqlitejpa.database.annotations.Columns;
import de.stefandraegersoftware.asimplesqlitejpa.database.annotations.java.de.stefandraegersoftware.asimplesqlitejpa.database.annotations.SQLQuery;
import de.stefandraegersoftware.asimplesqlitejpa.database.annotations.java.de.stefandraegersoftware.asimplesqlitejpa.database.annotations.Table;
import de.stefandraegersoftware.asimplesqlitejpa.database.annotations.java.de.stefandraegersoftware.asimplesqlitejpa.database.datasource.AbstractDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class DataSourceTest {

    private static final String TABLENAME_USER = "user";
    private static final String[] TABLE_USER_COLUMNS = {"id", "username", "firstname", "lastname"};

    private Connection connection;

    @Before
    public void generateDatabase() {
        SQLiteConfig config = new SQLiteConfig();
        config.setSharedCache(true);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:target/test.db", config.toProperties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void removeDatabase() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateTable() {
        UserDataSource dataSource = new UserDataSource(UserEntity.class);

    }

    @Test
    public void testToString() {
        UserDataSource dataSource = new UserDataSource(UserEntity.class);
        System.out.println(dataSource.toString());
    }


    @Test
    public void testDataSource() {
        UserDataSource dataSource = new UserDataSource(UserEntity.class);
        assertTrue("Tablename not equals.", TABLENAME_USER.equals(dataSource.getTablename()));
        assertArrayEquals("Columns not equals.", TABLE_USER_COLUMNS, dataSource.getColumns());
    }

    private class UserDataSource extends AbstractDataSource<UserEntity> {

        public UserDataSource(Class<UserEntity> entity) {
            super(entity);
        }

        @Override
        public void readFromTable() {
            String tablename = "Tablename: " + getTablename();
            Column[] columns = getColumns();
            List<Column> columnList = Arrays.asList(columns);
            System.out.println(tablename);
            System.out.println("");
            System.out.println("Columns: [");
            for (Column column : columnList) {
                System.out.println(column.name());
            }
            System.out.println("]");
            System.out.println("");
        }
    }

    @Table(tablename= "user")
    @Columns({@Column(name = "id", type = "long", maxLength = 10)})
    @SQLQuery(createTable= "",
      dropTable= "")
    protected class UserEntity {

    }
}
