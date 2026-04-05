package com.zorvyn.assignment.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zorvyn.assignment.models.Transactions;
import com.zorvyn.assignment.models.User;
import com.zorvyn.assignment.models.enummodels.RecordType;
import com.zorvyn.assignment.repository.Transactionrepo;
import com.zorvyn.assignment.repository.UserRepo;

@Service
public class TransactionsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Transactionrepo transactionrepo;

    public Transactions createTransactions(Transactions transactions, Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
       // here role to be added 
       transactions.setDate(LocalDate.now());
        transactions.setUser(user);
        return transactionrepo.save(transactions);
    }
    
    public List<Transactions> getAllTransactions() {
        return transactionrepo.findAll();
    }

  
    public List<Transactions> getByUser(Long userId) {
        return transactionrepo.findByUser(userId);
    }

    public Transactions updateTransaction(Long id, Transactions newData) {

        Transactions t = transactionrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        t.setAmount(newData.getAmount());
        t.setCatagory(newData.getCatagory());
        t.setType(newData.getType());
        t.setDate(newData.getDate());
        t.setDescrption(newData.getDescrption());

        return transactionrepo.save(t);
    }

  
    public void deleteTransaction(Long id) {
        transactionrepo.deleteById(id);
    }

  
   public List<Transactions> filterTransactions(RecordType type, String category, String date) {
    List<Transactions> all = transactionrepo.findAll();

    return all.stream()
            .filter(t -> type == null || t.getType() == type)
            .filter(t -> category == null || t.getCatagory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
}
}

