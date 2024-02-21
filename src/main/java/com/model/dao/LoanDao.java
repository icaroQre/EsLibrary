package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Loan;
import com.model.Student;

public class LoanDao extends SQLiteBase {
    
    public LoanDao() {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS loans (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "loanDate DATE," +
                "expectedDate DATE," +
                "penalty FLOAT," +
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

    public void create (Loan loan) {
        open();
        List<Student> myStudents = Student.all();

        for(Student student: myStudents) {
            if(student.get_id() == loan.getStudentId()){
                try {
                    PreparedStatement stm = conn.prepareStatement("INSERT INTO loans VALUES(?,?,?,?,?);");
                    
                    stm.setDate(2,loan.getLoanDate());
                    stm.setDate(3,loan.getExpectedDate());
                    stm.setFloat(4,loan.getPenalty());
                    stm.setInt(5,loan.getStudentId());
                    
                    stm.executeUpdate();
                    System.out.println(loan.getStudentId());
                    System.out.println("Empréstimo criado com sucesso");
        
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }
        }
    return;
    }

    public void uptade (Loan loan) {   
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE loans SET" +
                                                                "loanDate = ?, " +
                                                                "expectedDate = ?, " +
                                                                "penalty = ?, " +
                                                                "student_id = ?, " +
                                                                "WHERE id = ?;");

            stm.setDate(1,loan.getLoanDate());
            stm.setDate(2,loan.getExpectedDate());
            stm.setFloat(3,loan.getPenalty());
            stm.setInt(4,loan.getStudentId());
            stm.setInt(5,loan.get_id());
            
            stm.executeUpdate();
            System.out.println("Emprestimo atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Loan loan) {
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM loans WHERE id = ?;");

            stm.setInt(1,loan.get_id());

            stm.executeUpdate();
            System.out.println("Empréstimo deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Loan find (int pk) {
        Loan result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM loans WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            Student student = Student.find(rs.getInt(5));

            if(rs.next()){
                Loan loan = new Loan (
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getFloat(4),
                    rs.getInt(5),
                    student
                );
            
                result = loan;
                System.out.println("Empréstimo encontrado com sucesso");
        } else {
            System.out.println("Empréstimo não encontrado");
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public Loan findByStudent (int pk) {
        Loan result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM loans WHERE student_id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            Student student = Student.find(rs.getInt(5));

            if(rs.next()){
                Loan loan = new Loan (
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getFloat(4),
                    rs.getInt(5),
                    student
                );
            
                result = loan;
                System.out.println("Empréstimo encontrado com sucesso");
        } else {
            System.out.println("Empréstimo não encontrado");
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public List<Loan> allLoans () {
        ArrayList<Loan> result = new ArrayList<>();
        
        open();

        try {

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM loans ORDER BY id ASC");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Student student = Student.find(rs.getInt(5));

                Loan loan = new Loan(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getFloat(4),
                    rs.getInt(5),
                    student
                    );
                result.add(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
