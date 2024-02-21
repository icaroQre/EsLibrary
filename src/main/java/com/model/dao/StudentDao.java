package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Student;

public class StudentDao extends SQLiteBase {
    
    public StudentDao() {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "registration INTEGER," +
                "cpf TEXT," +
                "address TEXT);");

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create (Student student) {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO students VALUES(?,?,?,?,?);");
            
            stm.setString(2,student.getName());
            stm.setString(3,student.getRegistration());
            stm.setString(4,student.getCpf());
            stm.setString(5,student.getAdress());
            
            stm.executeUpdate();
            System.out.println("Aluno criado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void uptade (Student student) {
        
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE students SET" +
                                                                "name = ?, " +
                                                                "registration = ?, " +
                                                                "cpf = ?, " +
                                                                "adress = ?, " +
                                                                "WHERE id = ?;");

            stm.setString(1,student.getName());
            stm.setString(2,student.getRegistration());
            stm.setString(3,student.getCpf());
            stm.setString(4,student.getAdress());
            stm.setInt(5,student.get_id());
            
            stm.executeUpdate();
            System.out.println("Aluno atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Student student) {
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM students WHERE id = ?;");

            stm.setInt(1,student.get_id());

            stm.executeUpdate();
            System.out.println("Aluno deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Student find (int pk) {
        Student result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM students WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                Student student = new Student (
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                );

            result = student;
            } else {
                System.out.println("Aluno não encontrado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public List<Student> allStudents () {
        ArrayList<Student> result = new ArrayList<>();
        
        open();

        try {

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM students ORDER BY id ASC");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                );

                result.add(student);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

}
