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
import javax.naming.directory.AttributeInUseException;
import javax.persistence.OptimisticLockException;

/**
 * Simple storage-class that saves references into a sqlite database
 *
 * @author Heikki Kalliokoski
 */
public class StorageDatabase {
    
    private EbeanServer server;
    private List<String> newFilters;
    private List<String> oldFilters;
    private List<Map<String, String>> filteredResults;

    
    /**
     * Constructor which initializes database connection. 
     *
     * @param databaseFilePath      Path to database file
     */
    public StorageDatabase(String databaseFilePath) {
        ServerConfig config = inintializeDatabaseConfig(databaseFilePath);
        server = EbeanServerFactory.create(config);
        newFilters = new ArrayList<String>();
        oldFilters = new ArrayList<String>();
    }

    /**
     * Simplest method to add a reference to the database. 
     * Requires that reference contains type-key with corresponding, proper
     * reference type.
     *
     * @param reference     Reference data to be added
     * @throws Exception
     */
    public void addReference(Map<String, String> reference) throws AttributeInUseException {
        if(reference.get("type").equals("article"))
            addArticleReference(reference);
         else if(reference.get("type").equals("book"))
            addBookReference(reference);
         else if(reference.get("type").equals("inproceeding"))
            addInproceedingReference(reference);
    }
    
    
    /**
     * Another method to add a reference to the database.
     * 
     *
     * @param type          Type of the reference
     * @param reference     Reference data
     * @throws Exception
     */
    public void addReference(String type, Map<String, String> reference) throws AttributeInUseException{
        if(type.equals("article"))
            addArticleReference(reference);
        else if(type.equals("book"))
            addBookReference(reference);
        else if(type.equals("inproceedings"))
            addInproceedingReference(reference);
    }

    
    /**
     * Method to query all references in the database.
     * 
     * @return              List of all references in the database
     */
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

    private void addArticleReference(Map<String, String> reference) throws OptimisticLockException, AttributeInUseException {
        Article exists = server.find(Article.class).where().like("shortId", reference.get("id")).findUnique();
        if(exists != null)
            throw new AttributeInUseException("Reference exists already.");
        
        server.save(new Article(reference));
    }

    private void addBookReference(Map<String, String> reference) throws AttributeInUseException, OptimisticLockException {
        Book exists = server.find(Book.class).where().like("shortId", reference.get("id")).findUnique();
        if(exists != null)
            throw new AttributeInUseException("Reference exists already.");
        
        server.save(new Book(reference));
    }

    private void addInproceedingReference(Map<String, String> reference) throws OptimisticLockException, AttributeInUseException {
        Inproceeding exists = server.find(Inproceeding.class).where().like("shortId", reference.get("id")).findUnique();
        if(exists != null)
            throw new AttributeInUseException("Reference exists already.");
        
        server.save(new Inproceeding(reference));
    }   

    public List<String> getFilters() {
        ArrayList<String> allFilters = new ArrayList<String>();
        allFilters.addAll(oldFilters);
        allFilters.addAll(newFilters);
        return allFilters;
    }

    private ServerConfig inintializeDatabaseConfig(String databaseFilePath) {
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
        return config;
    }

    public void addFilter(String filter) {
        newFilters.add(filter);
            
    }

    public void clearFilters() {
        newFilters.clear();
        oldFilters.clear();
    }

    public List<Map<String, String>> getFiltered() {
        if(filteredResults == null)
            filteredResults = getReferences();
        applyNewFilters();
        return filteredResults;
    }

    private void applyNewFilters() {
        for(String filter: newFilters){
            applyFilter(filter);
            oldFilters.add(filter);
        }
        newFilters.clear();
    }
    
    private void applyFilter(String filter){
        ArrayList<Map<String, String>> toBeRemoved = new ArrayList<Map<String, String>>();
        for(Map<String, String> row: filteredResults){
            if(!rowMatchesFilter(row, filter))
                toBeRemoved.add(row);
        }
        filteredResults.removeAll(toBeRemoved);
    }
    
    private boolean rowMatchesFilter(Map<String, String> row, String filter){
        for(String value:row.values()){
            if(value.contains(filter))
                return true;
        }
        return false;
    }

}
