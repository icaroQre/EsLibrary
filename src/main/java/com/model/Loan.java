package com.model;

import java.sql.Date;
import java.util.List;

import com.model.dao.LoanDao;

public class Loan {
    
    Integer _id;
    Date loanDate;
    Date expectedDate;
    Float penalty;
    Integer studentId;
    Student student;

    public Loan() {
        
    }

    public Loan (Date loanDate, Date expectedDate, Float penalty, Integer studentId) {
        this.loanDate = loanDate;
        this.expectedDate = expectedDate;
        this.penalty = penalty;
        this.studentId = studentId;
    }
    public Loan (Integer _id, Date loanDate, Date expectedDate, Float penalty, Integer studentId){
        this._id = _id;
        this.loanDate = loanDate;
        this.expectedDate = expectedDate;
        this.penalty = penalty;
        this.studentId = studentId;
    }
    public Loan (Integer _id, Date loanDate, Date expectedDate, Float penalty, Integer studentId, Student student){
        this._id = _id;
        this.loanDate = loanDate;
        this.expectedDate = expectedDate;
        this.penalty = penalty;
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

    // Getter e Setter para loanDate
    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    // Getter e Setter para expectedDate
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    // Getter e Setter para penalty
    public Float getPenalty() {
        return penalty;
    }

    public void setPenalty(Float penalty) {
        this.penalty = penalty;
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

    public static LoanDao dao = new LoanDao();

    public void calculateReturnDate () {
        //METODO PARA CALCULAR DATA DE DEVOLUÇAO
    }

    @Override
    public String toString () {
        return "\n Empréstimo : " + this._id;
    } 

    public void loanBook (Integer id, Book book) {

        Date date1 = new Date(0);
        Date date2 = new Date(0);
        // Date expectedDate, Date returnDate, Integer loanId, Integer bookId
        LoanItem loanItem = new LoanItem(date1, date2, Loan.findByStudent(id).get_id(), book.get_id());
        loanItem.save();

    }

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

    public static List<Loan> all () {
        return dao.allLoans();
    }

    public static Loan find (int pk) {
        return dao.find(pk);
    }

    public static Loan findByStudent (int pk) {
        return dao.findByStudent(pk);
    }
}
