package com.model;

import java.sql.Date;
import java.util.List;

import com.model.dao.LoanItemDao;

public class LoanItem {

    Integer _id;
    Date expectedDate;
    Date returnDate;
    Integer loanId;
    Integer bookId;
    Loan loan;
    Book book;
    
    public LoanItem (Date expectedDate, Date returnDate, Integer loanId, Integer bookId) {
        this.expectedDate = expectedDate;
        this.returnDate = returnDate;
        this.loanId = loanId;
        this.bookId = bookId;
    }
    public LoanItem (Integer id, Date expectedDate, Date returnDate, Integer loanId, Integer bookId) {
        this._id = id;
        this.expectedDate = expectedDate;
        this.returnDate = returnDate;
        this.loanId = loanId;
        this.bookId = bookId;
    }
    public LoanItem (Integer _id, Date expectedDate, Date returnDate, Integer loanId, Integer bookId, Loan loan, Book book) {
        this._id = _id;
        this.expectedDate = expectedDate;
        this.returnDate = returnDate;
        this.loanId = loanId;
        this.bookId = bookId;
        this.loan = loan;
        this.book = book;
    }

    // Getter e Setter para _id
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    // Getter e Setter para expectedDate
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    // Getter e Setter para returnDate
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // Getter e Setter para loanId
    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    // Getter e Setter para bookId
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    // Getter e Setter para loan
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    // Getter e Setter para book
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public static LoanItemDao dao = new LoanItemDao();

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

    public static List<LoanItem> all () {
        return dao.allLoanItems();
    }

    public static LoanItem find (int pk) {
        return dao.find(pk);
    }
}
