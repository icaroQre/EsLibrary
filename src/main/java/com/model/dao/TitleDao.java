package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Title;

public class TitleDao extends SQLiteBase {
    
    public TitleDao() {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS titles (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "days INTEGER," +
                "isbn TEXT," +
                "edition INTEGER," +
                "year INTEGER" +
                ");");

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create (Title title) {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO titles VALUES(?,?,?,?,?,?);");
            
            stm.setString(2,title.getName());
            stm.setInt(3,title.getDays());
            stm.setString(4,title.getIsbn());
            stm.setInt(5,title.getEdition());
            stm.setInt(6,title.getYear());
            
            stm.executeUpdate();
            System.out.println("Título criado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void uptade (Title title) {
        
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE titles SET" +
                                                                "name = ?, " +
                                                                "days = ?, " +
                                                                "isbn = ?, " +
                                                                "edition = ?, " +
                                                                "year = ?, " +
                                                                "WHERE id = ?;");

            stm.setString(1,title.getName());
            stm.setInt(2,title.getDays());
            stm.setString(3,title.getIsbn());
            stm.setInt(4,title.getEdition());
            stm.setInt(5,title.getYear());
            stm.setInt(6,title.get_id());
            
            stm.executeUpdate();
            System.out.println("Aluno atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Title title) {
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM titles WHERE id = ?;");

            stm.setInt(1,title.get_id());

            stm.executeUpdate();
            System.out.println("Título deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Title find (int pk) {
        Title result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM titles WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                Title title = new Title (
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6)
                );

            result = title;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public List<Title> allTitles () {
        ArrayList<Title> result = new ArrayList<>();
        
        open();

        try {

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM titles ORDER BY id ASC");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Title title = new Title(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6)
                );
                result.add(title);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

}
