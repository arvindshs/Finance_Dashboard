package com.zorvyn.assignment.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zorvyn.assignment.models.Transactions;
import com.zorvyn.assignment.services.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/ammountsummary")
    public Map<String,Double> getamountsummary(){
       return dashboardService.getamountsummary();
    }

    @GetMapping("/categorywise")
    public Map<String,Double> getcatagorywisetotal(){
        return dashboardService.getCategorySummary();
    }

    @GetMapping("/recent")
    public List<Transactions> getrecentactivity(){
        return dashboardService.getRecent();
    }

    @GetMapping("/monthlywise")
    public Map<Integer,Double> getmonthyMap(){
        return dashboardService.getMonthlySummary();
    }
}
