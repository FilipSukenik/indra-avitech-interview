package com.indra.avitech.interview;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfig {

  public HikariDataSource database;

  public DatabaseConfig() {

  }

  public void setUp() throws IOException, SQLException {

    var config = new HikariConfig();
    config.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;");
    config.setDriverClassName("org.h2.Driver");

    this.database = new HikariDataSource(config);

    runDDL();
  }

  private void runDDL() throws IOException, SQLException {

    var inputStream = this.getClass().getClassLoader().getResourceAsStream("db-dll.sql");
    String ddl = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    try (var connection = database.getConnection();
         PreparedStatement statement = connection.prepareStatement(ddl)
    ) {
      statement.execute();
    }
  }

  public void teardown() {

    database.close();
  }

  public HikariDataSource getDatabase() {

    return database;
  }
}
