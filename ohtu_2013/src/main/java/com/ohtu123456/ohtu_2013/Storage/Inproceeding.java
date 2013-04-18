package com.ohtu123456.ohtu_2013.Storage;

import java.util.Map;

/**
 *
 * @author Heikki Kalliokoski
 */
class Inproceeding {
    
    private Integer id;
    
    private String shortId;
    private String author;
    private String title;
    private String booktitle;
    private String publishYear;
    private String pages;
    private String publisher;
    private String address;

    public Inproceeding() {}
    public Inproceeding(Map<String, String> reference){
        String[] fields = {
            "id", "author", "title", "booktitle",
            "year", "pages", "publisher", "address"
        };
        for(String field: fields){
            if(!reference.containsKey(field))
                reference.put(field, "");
            if(field.equals("id"))
                shortId = reference.get(field);
            else if(field.equals("author"))
                author = reference.get(field);
            else if(field.equals("title"))
                title = reference.get(field);
            else if(field.equals("booktitle"))
                booktitle = reference.get(field);
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

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
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
