package com.ohtu123456.ohtu_2013.Storage;

import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Heikki Kalliokoski
 */
@Entity
public class Article {
    
    @Id
    private Integer id;
    
    private String shortId;
    private String author;
    private String title;
    private String journal;
    private String volume;
    private String number;
    private String publishYear;
    private String pages;
    private String publisher;
    private String address;
    
    public Article(){}
    public Article(Map<String, String> reference){
        String[] fields = {
            "id", "author", "title", "journal",
            "volume", "number", "year", "pages",
            "publisher", "address"
        };
        for(String field: fields){
            if(!reference.containsKey(field))
                    reference.put(field, "");
        }
        for (String field: fields){
            if (field.equals("id"))
                shortId = reference.get(field);
            else if(field.equals("author"))
                author = reference.get(field);
            else if(field.equals("title"))
                title = reference.get(field);
            else if(field.equals("journal"))
                journal = reference.get(field);
            else if(field.equals("volume"))
                volume = reference.get(field);
            else if(field.equals("number"))
                number = reference.get(field);
            else if(field.equals("year"))
                publishYear = reference.get(field);
            else if(field.equals("pages"))
                pages = reference.get(field);
            else if(field.equals("publisher"))
                publisher = reference.get(field);
            else if(field.equals("address"))
                address = reference.get(field);
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

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
