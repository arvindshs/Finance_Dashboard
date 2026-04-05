package com.zorvyn.assignment.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zorvyn.assignment.models.Transactions;
import com.zorvyn.assignment.repository.Transactionrepo;

@Service
public class DashboardService {
    @Autowired
    private Transactionrepo transactionrepo;

    public Map<String, Double> getamountsummary(){
        Double income = transactionrepo.getTotalIncome();
        Double expense = transactionrepo.getTotalExpense();

        if(income == null) 
            income = 0.0;
        if (expense == null) {
            expense = 0.0;
        }

        Double net = income - expense;

        Map<String,Double> summary = new HashMap<>();
        summary.put("income", income);
        summary.put("expense", expense);
        summary.put("netAmount", net);

        return summary;
    }

    public Map<String, Double> getCategorySummary() {

        List<Object[]> data = transactionrepo.getCategorySummary();

        Map<String, Double> result = new HashMap<>();

        for (Object[] row : data) {
            String category = (String) row[0];
            Double total = (Double) row[1];
            result.put(category, total);
        }

        return result;
    }
    
    public List<Transactions> getRecent(){
        return transactionrepo.findTop5ByOrderByDateDesc();
    }

    public Map<Integer, Double> getMonthlySummary() {

        List<Object[]> data = transactionrepo.getMonthlySummary();

        Map<Integer, Double> result = new HashMap<>();

        for (Object[] row : data) {
            Integer month = (Integer) row[0];
            Double total = (Double) row[1];
            result.put(month, total);
        }

        return result;
    }
}
