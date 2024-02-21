package com.model;

import java.util.List;
import com.model.dao.BookDao;

public class Book {

    Integer _id;
    Integer available;
    Integer titleId;
    Title title;

    public Book(Integer available, Integer title) {
        this.available = available;
        this.titleId = title;
    }

    public Book(Integer _id, Integer available, Integer titleId) {
        this._id = _id;
        this.available = available;
        this.titleId = titleId;
    }

    public Book(Integer _id, Integer available, Integer titleId, Title title) {
        this._id = _id;
        this.available = available;
        this.titleId = titleId;
        this.title = title;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    // Getter for 'available'
    public Integer getAvailable() {
        return available;
    }

    // Setter for 'available'
    public void setAvailable(Integer available) {
        this.available = available;
    }

    // Getter for 'title'
    public Integer getTitleId() {
        return titleId;
    }

    // Setter for 'title'
    public void setTitleId(Integer title) {
        this.titleId = title;
    }

     // Getter e Setter para title
     public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString () {
        return "\n Livro : " + this._id + ", disponível: " + this.available + ", título: " + this.titleId + " " + this.title;
    } 

    private static BookDao dao = new BookDao();
    
    public void save () {
        if (_id != null && dao.find(_id) != null) {
            dao.uptade(this);
        } else {
            dao.create(this);
        }
    }

    public void delete () {
        dao.delete(this);
    }

    public static List<Book> all () {
        return dao.allBooks();
    }

    public static Book find (int pk) {
        return dao.find(pk);
    }
}
