package database;

import java.sql.Connection;

// An Interface to distribute the shared Connection with the oracle database
public interface ConnectionDistributor {
    // Connection Established
    Connection con = Oracle.initDb();
}
