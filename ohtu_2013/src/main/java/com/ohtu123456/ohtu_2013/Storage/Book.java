package com.ohtu123456.ohtu_2013.Storage;

import java.util.Map;
import javax.persistence.Entity;

/**
 *
 * @author Heikki Kalliokoski
 */
@Entity
class Book {
    
    private Integer id;
    
    private String shortId;
    private String author;
    private String title;
    private String publishYear;
    private String publisher;

    public Book() {}
    public Book(Map<String, String> reference){
        String[] fields = {"id", "author", "title", "publisher", "year"};
        for(String field: fields){
            if(!reference.containsKey(field))
                reference.put(field, "");
            if(field.equals("id"))
                shortId = reference.get(field);
            else if(field.equals("author"))
                author = reference.get(field);
            else if(field.equals("title"))
                title = reference.get(field);
            else if(field.equals("year"))
                publishYear = reference.get(field);
            else if(field.equals("publisher"))
                publisher = reference.get(field);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String year) {
        this.publishYear = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
