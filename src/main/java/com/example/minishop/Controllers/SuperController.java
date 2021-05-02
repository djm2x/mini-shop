package com.example.minishop.Controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.minishop.Repositories.GenericRepository;
import com.example.minishop.Repositories.GenericRepositoryImp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class SuperController<T extends Serializable, ID> {

    // protected final SuperRepository<T> repository;
    protected final GenericRepositoryImp<T, ID> repository;

    public SuperController(GenericRepositoryImp<T, ID> repository) {
        this.repository = repository;
    }

    // public SuperController(Class<T> domainClass, EntityManager em) {
    //     this.repository = new SuperRepository<T>(domainClass, em);
    // }



    @GetMapping("/getAll/{startIndex}/{pageSize}/{sortBy}/{sortDir}/{email}")
    public ResponseEntity<?> GetAll(@PathVariable int startIndex, @PathVariable int pageSize,
            @PathVariable String sortBy, @PathVariable String sortDir, @PathVariable String email) {

        Page<T> query = repository.findAll(PageRequest.of(startIndex, pageSize));

        List<T> list = query.getContent();

        Long count = query.getTotalElements();

        return ResponseEntity.ok(Map.of("count", count, "list", list));
    }

    @GetMapping("/get")
    public ResponseEntity<List<T>> get(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable ID id){

        Optional<T> model = repository.findById(id);

        if(model.isPresent() == false){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(model.get());
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> put(@PathVariable ID id, @RequestBody T model){

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
    public ResponseEntity<?> delete(@PathVariable ID id){
        repository.deleteById(id);
        
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
