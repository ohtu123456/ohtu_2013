package com.ohtu123456.ohtu_2013.Storage;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;

/**
 *
 * @author Heikki Kalliokoski
 */
class StorageDatabase {
    
    private EbeanServer server;

    StorageDatabase(String databaseFilePath) {
        ServerConfig config = new ServerConfig();
        config.setName("referenceDatabase");

        DataSourceConfig sqLite = new DataSourceConfig();
        sqLite.setDriver("org.sqlite.JDBC");
        sqLite.setUsername("");
        sqLite.setPassword("");
        sqLite.setUrl("jdbc:sqlite:" + databaseFilePath);
        config.setDataSourceConfig(sqLite);
        config.setDatabasePlatform(new SQLitePlatform());
        config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);

        config.setDefaultServer(false);
        config.setRegister(false);

        config.addClass(Article.class);
        config.addClass(Inproceeding.class);
        config.addClass(Book.class);

        server = EbeanServerFactory.create(config);
    }
    
}
