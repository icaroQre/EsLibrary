package com.model;

import java.sql.Date;
import java.util.List;

import com.model.dao.StudentDao;

public class Student {

    private Integer _id;

    private String name;
    private String registration;
    private String cpf;
    private String adress;

    public Student(String name, String registration, String cpf, String adress) {
        this.name = name;
        this.registration = registration;
        this.cpf = cpf;
        this.adress = adress;
    }
    public Student(Integer _id, String name, String registration, String cpf, String adress) {
        this._id = _id;
        this.name = name;
        this.registration = registration;
        this.cpf = cpf;
        this.adress = adress;
    }

    /**                                                                                           
     * @return Integer return the _id
     */
    public Integer get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(Integer _id) {
        this._id = _id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Integer return the registration
     */
    public String getRegistration() {
        return registration;
    }

    /**
     * @param registration the registration to set
     */
    public void setRegistration(String registration) {
        this.registration = registration;
    }

    /**
     * @return String return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return String return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString () {
        return "\n Aluno : " + this.name + ", Matrícula: " + this.registration + ", CPF: " + this.cpf + ", Endereço: " + this.adress + ", ID: " + this._id;
    } 

    private static StudentDao dao = new StudentDao();
    
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

    public static List<Student> all () {
        return dao.allStudents();
    }

    public static Student find (int pk) {
        return dao.find(pk);
    }

    public void verifyDebit () {

        Debit debit = new Debit();
        Debit result = debit.verifyDebit(_id); 
        if (result == null){
            System.out.println("Aluno sem débitos");
        } else {
            System.out.println("Valor do débito: " + result.getValue() + ", Data: " + result.getDate());
        }

    }

    public void loanBook (Book book) {
        // public Loan (Date loanDate, Date expectedDate, Float penalty, Integer studentId) {
        Date date1 = new Date(0);
        Date date2 = new Date(0);
        
        Loan loan = new Loan(date1, date2, 0f, _id);
        loan.save();

        loan.loanBook(this._id, book);
    }
}
