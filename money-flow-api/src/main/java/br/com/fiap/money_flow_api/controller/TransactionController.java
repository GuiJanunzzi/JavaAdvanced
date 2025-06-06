package br.com.fiap.money_flow_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Transaction;
import br.com.fiap.money_flow_api.model.TransactionFilter;
import br.com.fiap.money_flow_api.repository.TransactionRepository;
import br.com.fiap.money_flow_api.service.AiAnalistyService;
import br.com.fiap.money_flow_api.specification.TransactionSpecification;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AiAnalistyService aiService;

    @GetMapping
    public Page<Transaction> index(
        TransactionFilter filter,
        @PageableDefault(size = 10, sort = "date", direction = Direction.DESC) Pageable pageable){

            // if (filter.description() != null) {
            //     return repository.findByDescriptionContainingIgnoringCase(filter.description(), pageable);
            // }

            // ------------------------------------------------------------------------------------------------------------
            // var probe = Transaction.builder()
            //     .description(filter.description())
            //     .date(filter.startDate())
            //     .build();

            // var matcher = ExampleMatcher
            //     .matchingAll()
            //     .withIgnoreCase()
            //     .withStringMatcher(StringMatcher.CONTAINING);

            // var example = Example.of(probe, matcher);

            var specification = TransactionSpecification.withFilter(filter);
            
            return repository.findAll(specification, pageable);
    }

    @GetMapping("/dashboard")
    public String getData(@RequestParam String subject, @RequestParam String language){
        return aiService.getAnalise(subject, language);
    }
    
}
