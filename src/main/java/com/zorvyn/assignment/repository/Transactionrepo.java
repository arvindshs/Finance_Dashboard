package com.zorvyn.assignment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zorvyn.assignment.models.Transactions;
import com.zorvyn.assignment.models.enummodels.RecordType;
@Repository
public interface Transactionrepo extends JpaRepository<Transactions, Long> {
    List<Transactions> findByUser(Long Userid);
    List<Transactions> findByCatagory(String catagory);
    List<Transactions> findByType(RecordType type);
    List<Transactions> findByDate(LocalDate date);
    
    List<Transactions> findTop5ByOrderByDateDesc();
    
    @Query("SELECT SUM(f.amount) FROM Transactions f WHERE f.type = 'INCOME'")
    Double getTotalIncome();

    @Query("SELECT SUM(f.amount) FROM Transactions f WHERE f.type = 'EXPENSE'")
    Double getTotalExpense();

    @Query("SELECT f.catagory, SUM(f.amount) FROM Transactions f GROUP BY f.catagory")
    List<Object[]> getCategorySummary();

    @Query("SELECT MONTH(f.date), SUM(f.amount) FROM Transactions f GROUP BY MONTH(f.date)")
    List<Object[]> getMonthlySummary();
}
