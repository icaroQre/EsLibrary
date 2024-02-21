package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Book;
import com.model.Title;

public class BookDao extends SQLiteBase {
    
    public BookDao() {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "available INTEGER," +
                "title_id INTEGER," +
                "FOREIGN KEY (title_id) REFERENCES titles(id)" +
                ");");

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create (Book book) {
        open();

            List<Title> myTitles = Title.all();

            for(Title title : myTitles) {
                if(title.get_id() == book.getTitleId()){
                    try {
                        PreparedStatement stm = conn.prepareStatement("INSERT INTO books VALUES(?,?,?);");
                        
                        stm.setInt(2,book.getAvailable());
                        stm.setInt(3,book.getTitleId());
                        
                        stm.executeUpdate();
                        System.out.println("Livro criado com sucesso");
            
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        close();
                    }
                    return;
                }
            }
        return;
    }

    public void uptade (Book book) {   
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE books SET" +
                                                                "available = ?, " +
                                                                "title_id = ?, " +
                                                                "WHERE id = ?;");

            stm.setInt(1,book.getAvailable());
            stm.setInt(2,book.getTitleId());
            stm.setInt(3,book.get_id());
            
            stm.executeUpdate();
            System.out.println("Livro atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Book book) {
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM books WHERE id = ?;");

            stm.setInt(1,book.get_id());

            stm.executeUpdate();
            System.out.println("Livro deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Book find (int pk) {
        Book result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM books WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            Title title = Title.find(rs.getInt(3));

            if(rs.next()){
                Book book = new Book (
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    title
                );
            
                result = book;
                System.out.println("Livro encontrado com sucesso");
        } else {
            System.out.println("Livro não encontrado");
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public List<Book> allBooks () {
        ArrayList<Book> result = new ArrayList<>();
        
        open();

        try {

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM books ORDER BY id ASC");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                
                Title title = Title.find(rs.getInt(3));

                Book book = new Book(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    title
                    );
                result.add(book);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
