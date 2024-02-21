package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Book;
import com.model.Loan;
import com.model.LoanItem;

public class LoanItemDao extends SQLiteBase {
    
    public LoanItemDao() {
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS loanItems (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "expectedDate DATE," +
                "returnDate DATE," +
                "loan_id INTEGER," +
                "book_id INTEGER," +
                "FOREIGN KEY (loan_id) REFERENCES loans(id)" +
                "FOREIGN KEY (book_id) REFERENCES books(id)" +
                ");");

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create (LoanItem loanItem) {
        open();
            try {
                PreparedStatement stm = conn.prepareStatement("INSERT INTO loanItems VALUES(?,?,?,?,?);");
                
                stm.setDate(2,loanItem.getExpectedDate());
                stm.setDate(3,loanItem.getReturnDate());
                stm.setInt(4,loanItem.getLoanId());
                stm.setInt(5,loanItem.getBookId());
                
                stm.executeUpdate();
                System.out.println("Empréstimo Item criado com sucesso");
    
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
    return;
    }

    public void uptade (LoanItem loanItem) {   
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE loanItems SET" +
                                                                "expectedDate = ?, " +
                                                                "returnDate = ?, " +
                                                                "loan_id = ?, " +
                                                                "book_id = ?, " +
                                                                "WHERE id = ?;");

            stm.setDate(1,loanItem.getExpectedDate());
            stm.setDate(2,loanItem.getReturnDate());
            stm.setInt(3,loanItem.getLoanId());
            stm.setInt(4,loanItem.getBookId());
            stm.setInt(5,loanItem.get_id());
            
            stm.executeUpdate();
            System.out.println("Emprestimo Item atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (LoanItem loanItem) {
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM loanItems WHERE id = ?;");

            stm.setInt(1,loanItem.get_id());

            stm.executeUpdate();
            System.out.println("Empréstimo Item deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public LoanItem find (int pk) {
        LoanItem result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM loanItems WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            Loan loan = Loan.find(rs.getInt(4));
            Book book = Book.find(rs.getInt(5));

            if(rs.next()){
                LoanItem loanItem = new LoanItem (
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    loan,
                    book
                );
            
                result = loanItem;
                System.out.println("Empréstimo Item encontrado com sucesso");
        } else {
            System.out.println("Empréstimo Item não encontrado");
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public List<LoanItem> allLoanItems () {
        ArrayList<LoanItem> result = new ArrayList<>();
        
        open();

        try {

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM loanItems ORDER BY id ASC");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Loan loan = Loan.find(4);
                Book book = Book.find(5);

                LoanItem loanItem = new LoanItem(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    loan,
                    book
                    );
                result.add(loanItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
