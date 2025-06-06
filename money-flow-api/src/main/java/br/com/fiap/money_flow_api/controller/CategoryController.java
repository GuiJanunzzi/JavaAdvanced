package br.com.fiap.money_flow_api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.model.User;
import br.com.fiap.money_flow_api.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController //component
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired //injeção de dependencia
    private CategoryRepository repository;
    
    //Listar todas as categorias
    //GET : 8080/categories -> 200 ok -> json
    @GetMapping
    @Cacheable("categories")
    public List<Category> index(@AuthenticationPrincipal User user){
        return repository.findByUser(user);
    }

    //Cadastrar categorias
    @PostMapping
    @CacheEvict(value = "categories", allEntries = true)
    @Operation(
        summary = "Cadastrar categoria", 
        description = "Insere uma nova categoria",
        responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400"),
        }
     )

    // @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody @Valid Category category, @AuthenticationPrincipal User user){
        log.info("Cadastrando..."+category.getName());
        category.setUser(user);
        repository.save(category);
        return ResponseEntity.status(201).body(category);
    }

    // Detalhes da categoria
    @GetMapping("{id}")
    public Category get(@PathVariable Long id, @AuthenticationPrincipal User user){
        log.info("Buscando Categoria... ID: "+id);
        var cateory = getCategory(id);
        if (!cateory.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return getCategory(id);
    }

    //  Apagar categorias
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando categoria " + id);
        repository.delete(getCategory(id));
    }

    //  Editar categorias
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody @Valid Category category){
        log.info("Atualizando categoria " + id + " " + category.toString());

        getCategory(id);
        category.setId(id);
        repository.save(category);
        
        return category;
    }

    private Category getCategory(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
    }

}
