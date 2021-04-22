package com.example.minishop.Controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.example.minishop.Services.GenericRepository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.NotFound;

public class GenericController<T extends Serializable> {

    protected final GenericRepository<T> repository;

    public GenericController(GenericRepository<T> repository) {
        this.repository = repository;
    }

    // @GetMapping("")
    // public ResponseEntity<Page<T>> getPage(Pageable pageable){
    //     return ResponseEntity.ok(repository.findAll(pageable));
    // }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<T>> getAll(){

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id){

        Optional<T> m = repository.findById(id);

        return ResponseEntity.ok(m.get());
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody T model){

        Optional<T> optional = repository.findById(id);

        if(optional.isPresent() == false){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        T o = repository.save(model);

        return ResponseEntity.ok(o);
    }

    @PostMapping("/post")
    public ResponseEntity<T> post(@RequestBody T model){

        T o = repository.save(model);

        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        repository.deleteById(id);
        
        return ResponseEntity.ok("Ok");
    }
}
