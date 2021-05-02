package com.example.minishop.Repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericRepositoryImp<T extends Serializable , ID>  {

    @Autowired
    protected GenericRepository<T, ID> repository;
    
    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Specification<T> s) {
        return repository.findAll(s);
    }

    public Page<T> findAll(Pageable p) {
        return repository.findAll(p);
    }

    public Page<T> findAll(Specification<T> s, Pageable p) {
        return repository.findAll(s, p);
    }

    public Optional<T> findOne(Specification<T> s) {
        return repository.findOne(s);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public T save(T s) {
        return repository.save(s);
    }

    public List<T> saveAll(List<T> s) {
        return repository.saveAll(s);
    }

    public boolean deleteById(ID id) {
        Optional<T> optional = repository.findById(id);

        if(optional.isPresent() == false){
            return false;
        }

        repository.deleteById(id);

        return true;
    }
    
}
