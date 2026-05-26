package com.RestaurantBillingManagementSystem.bdd;

import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCleanupHook {

    @Autowired
    private DataSource dataSource;

    @Before(order = 0)
    public void cleanupDatabase() throws Exception {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("SET REFERENTIAL_INTEGRITY FALSE");
            ResultSet rs = statement.executeQuery(
                    "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
            List<String> tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            rs.close();
            for (String table : tables) {
                statement.execute("TRUNCATE TABLE " + table);
            }
            statement.execute("SET REFERENTIAL_INTEGRITY TRUE");
        }
    }
}
