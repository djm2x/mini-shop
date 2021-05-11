package com.example.minishop.repositories.nouse;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.minishop.repositories.GenericRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


// @Service
// @Repository
// @Transactional
// @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class GenericRepositoryImp<T extends Serializable , ID>  {

    @Autowired
    protected GenericRepository<T, ID> repository;
    // @Autowired
    // public GenericRepositoryImp(GenericRepository<T, ID> repository) {
    //     this.repository = repository;
    // }
    
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
