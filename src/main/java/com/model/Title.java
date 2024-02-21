package com.model;

import java.util.List;
import com.model.dao.TitleDao;

public class Title {
    
    Integer _id;
    String name;
    Integer days;
    String isbn;
    Integer edition;
    Integer year;

    public Title(String name, Integer days, String isbn, Integer edition, Integer year) {
        this.name = name;
        this.days = days;
        this.isbn = isbn;
        this.edition = edition;
        this.year = year;
    }

    public Title(Integer _id, String name, Integer days, String isbn, Integer edition, Integer year) {
        this._id = _id;
        this.name = name;
        this.days = days;
        this.isbn = isbn;
        this.edition = edition;
        this.year = year;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    // Getter and Setter methods for isbn
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter and Setter methods for edition
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    // Getter and Setter methods for year
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString () {
        return "\n Nome : " + this.name + ", Prazo: " + this.days + ", ISBN: " + this.isbn + ", Edição: " + this.edition + ", Ano: " + this.year + ", ID: " + this._id;
    }

    private static TitleDao dao = new TitleDao();
    
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

    public static List<Title> all () {
        return dao.allTitles();
    }

    public static Title find (int pk) {
        return dao.find(pk);
    }
}
