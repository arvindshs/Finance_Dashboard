package com.zorvyn.assignment.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zorvyn.assignment.models.Transactions;
import com.zorvyn.assignment.models.enummodels.RecordType;
import com.zorvyn.assignment.services.TransactionsService;

// import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/create/{id}")
    public Transactions creTransactions(@RequestBody Transactions transactions, @PathVariable Long id){
        return transactionsService.createTransactions(transactions,id);
    }

     @GetMapping("/all")
    public List<Transactions> getAll() {
        return transactionsService.getAllTransactions();
    }

    @PutMapping("/update/{id}")
    public Transactions updaTransactions(@PathVariable Long id ,@RequestBody Transactions newTransactions){
        return transactionsService.updateTransaction(id, newTransactions);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        transactionsService.deleteTransaction(id);
        return "Deleted successfully";
    }

    // @GetMapping("/category/{category}")
    // public List<Transactions> getByCategory(@PathVariable String category) {
    //     return transactionsService.getByCategory(category);
    // }

    //  @GetMapping("/type/{type}")
    // public List<Transactions> getByType(@PathVariable RecordType type) {
    //     return transactionsService.getByType(type);
    // }

    // @GetMapping("/date/{date}")
    // public List<Transactions> getByDate(@PathVariable LocalDate date){
    //     return transactionsService.ge(date);
    // }
    @GetMapping("/transactions/filter")
public List<Transactions> filterTransactions(
        @RequestParam(required = false) RecordType type,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String date) {

    return transactionsService.filterTransactions(type, category, date);
}

}
