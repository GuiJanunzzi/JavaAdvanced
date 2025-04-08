package br.com.fiap.money_flow_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Transaction;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private class TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(){
        return repository.findAll();
    }
    
}
