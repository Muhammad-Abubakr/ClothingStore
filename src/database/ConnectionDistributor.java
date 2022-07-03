package database;

import java.sql.Connection;

public interface ConnectionDistributor {
    // Connection Established
    Connection con = Oracle.initDb();
}
