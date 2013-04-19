package com.ohtu123456.ohtu_2013.Storage;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StorageDatabase implements StorageInterface {
    
    private EbeanServer server;

    public StorageDatabase(String databaseFilePath) {
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
        
        File dbFile = new File(databaseFilePath);
        if(!dbFile.exists()){
            config.setDdlGenerate(true);
            config.setDdlRun(true);
        } else {
            config.setDdlGenerate(false);
            config.setDdlRun(false);
        }
        dbFile = null;

        server = EbeanServerFactory.create(config);
    }

    @Override
    public void addReference(Map<String, String> reference) throws Exception {
        if(reference.get("type").equals("article"))
            addArticleReference(reference);
         else if(reference.get("type").equals("book"))
            addBookReference(reference);
         else if(reference.get("type").equals("inproceeding"))
            addInproceedingReference(reference);
         else
            throw new Exception("Unknown reference type.");
    }
    
    public void addReference(String type, Map<String, String> reference) throws Exception{
        if(type.equals("article"))
            addArticleReference(reference);
        else if(type.equals("book"))
            addBookReference(reference);
        else if(type.equals("inproceedings"))
            addInproceedingReference(reference);
        else
            throw new Exception("Unknown reference type.");
    }

    public List<Map<String, String>> getReferences() {
        List<Map<String, String>> references = new ArrayList<Map<String, String>>();
        
        List<Article> articles = server.find(Article.class).findList();
        for(Article article: articles)
            references.add(article.getReference());
        
        List<Book> books = server.find(Book.class).findList();
        for(Book book: books)
            references.add(book.getReference());
        
        List<Inproceeding> inproceedings = server.find(Inproceeding.class).findList();
        for(Inproceeding inproceeding: inproceedings)
            references.add(inproceeding.getReference());
        
        return references;
    }

    private void addArticleReference(Map<String, String> reference) throws OptimisticLockException, Exception {
        Article exists = server.find(Article.class).where().like("shortId", reference.get("id")).findUnique();
        if(exists != null)
            throw new Exception("Reference exists already.");
        
        server.save(new Article(reference));
    }

    private void addBookReference(Map<String, String> reference) throws Exception, OptimisticLockException {
        Book exists = server.find(Book.class).where().like("shortId", reference.get("id")).findUnique();
        if(exists != null)
            throw new Exception("Reference exists already.");
        
        server.save(new Book(reference));
    }

    private void addInproceedingReference(Map<String, String> reference) throws OptimisticLockException, Exception {
        Inproceeding exists = server.find(Inproceeding.class).where().like("shortId", reference.get("id")).findUnique();
        if(exists != null)
            throw new Exception("Reference exists already.");
        
        server.save(new Inproceeding(reference));
    }   
}
