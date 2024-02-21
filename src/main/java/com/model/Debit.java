package com.model;

import java.sql.Date;
import java.util.List;

import com.model.dao.DebitDao;

public class Debit {
    
    Integer _id;
    Date date;
    Float value;
    Integer studentId;
    Student student;

    public Debit(){

    }

    public Debit(Date date, Float value, Integer studentId) {
        this.date = date;
        this.value = value;
        this.studentId = studentId;
    }
    public Debit(Integer _id, Date date, Float value, Integer studentId) {
        this._id = _id;
        this.date = date;
        this.value = value;
        this.studentId = studentId;
    }
    public Debit(Integer _id, Date date, Float value, Integer studentId, Student student) {
        this._id = _id;
        this.date = date;
        this.value = value;
        this.studentId = studentId;
        this.student = student;
    }

    // Getter e Setter para _id
    public Integer get_id() {
        return _id;
    }
    
    public void set_id(Integer _id) {
        this._id = _id;
    }
    
    // Getter e Setter para date
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    // Getter e Setter para value
    public Float getValue() {
        return value;
    }
    
    public void setValue(Float value) {
        this.value = value;
    }
    
    // Getter e Setter para studentId
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    // Getter e Setter para student
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

    public static DebitDao dao = new DebitDao();
    
    public Debit verifyDebit(Integer id){
        System.out.println("Verificando débito do aluno: " + Student.find(id).getName());
        return dao.findByStudent(id);
    }
    
    @Override
    public String toString () {
        return "\n Data : " + this.date + ", Valor: " + this.value + ", Estudante ID: " + this.studentId + ", Estudante: " + this.student;
    }
    
    
    public void save () {
        if (_id != null && dao.find(_id) != null) {
            dao.uptade(this);
        } else {
            dao.create(this);
        }
    }

    public static Debit find (int pk) {
        return dao.find(pk);
    }

    public void delete () {
        dao.delete(this);
    }

    public static List<Debit> all () {
        return dao.allDebits();
    }
}