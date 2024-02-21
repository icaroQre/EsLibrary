package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Debit;
import com.model.Student;

public class DebitDao extends SQLiteBase {
    
    public DebitDao() {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS debits (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date DATE," +
                "value INTEGER," +
                "student_id INTEGER," +
                "FOREIGN KEY (student_id) REFERENCES stdents(id)" +
                ");");

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create (Debit debit) {
        open();
        List<Student> myStudents = Student.all();

        for(Student student: myStudents) {
            if(student.get_id() == debit.getStudentId()){
                try {
                    PreparedStatement stm = conn.prepareStatement("INSERT INTO debits VALUES(?,?,?,?);");
                    
                    stm.setDate(2,debit.getDate());
                    stm.setFloat(3,debit.getValue());
                    stm.setInt(4,debit.getStudentId());
                    
                    stm.executeUpdate();
                    System.out.println("Débito criado com sucesso");
        
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }
        }
    return;
    }

    public void uptade (Debit debit) {   
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE books SET" +
                                                                "date = ?, " +
                                                                "value = ?, " +
                                                                "student_id = ?, " +
                                                                "WHERE id = ?;");

            stm.setDate(1,debit.getDate());
            stm.setFloat(2,debit.getValue());
            stm.setInt(2,debit.getStudentId());
            stm.setInt(3,debit.get_id());
            
            stm.executeUpdate();
            System.out.println("Débito atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Debit debit) {
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM books WHERE id = ?;");

            stm.setInt(1,debit.get_id());

            stm.executeUpdate();
            System.out.println("Débito deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Debit find (int pk) {
        Debit result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM debits WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            Student student = Student.find(rs.getInt(4));

            if(rs.next()){
                Debit debit = new Debit (
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getFloat(3),
                    rs.getInt(4),
                    student
                );
            
                result = debit;
        } else {
            System.out.println("Débito não encontrado");
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public Debit findByStudent (int pk) {
        Debit result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM debits WHERE student_id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            Student student = Student.find(rs.getInt(4));

            if(rs.next()){
                Debit debit = new Debit (
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getFloat(3),
                    rs.getInt(4),
                    student
                );
            
                result = debit;
        } else {
            System.out.println("Débito não encontrado");
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public List<Debit> allDebits () {
        ArrayList<Debit> result = new ArrayList<>();
        
        open();

        try {

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM debits ORDER BY id ASC");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Student student = Student.find(rs.getInt(3));

                Debit debit = new Debit(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getFloat(3),
                    rs.getInt(4),
                    student
                    );
                result.add(debit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
