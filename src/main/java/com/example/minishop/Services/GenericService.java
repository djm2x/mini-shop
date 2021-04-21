package com.example.minishop.Services;

import java.io.Serializable;

public abstract class GenericService<T extends Serializable> {

    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    // public Page<T> getPage(Pageable pageable){
    //     return repository.findAll(pageable);
    // }

    public T get(Long id){
        return repository.findById(id).orElseThrow(
        );
    }

    // @Transactional
    // public T update(T updated){
    //     T dbDomain = get(updated.getId());
    //     dbDomain.update(updated);

    //     return repository.save(dbDomain);
    // }

    // @Transactional
    // public T create(T newDomain){
    //     T dbDomain = newDomain.createNewInstance();
    //     return repository.save(dbDomain);
    // }

    // @Transactional
    // public void delete(Long id){
    //     //check if object with this id exists
    //     get(id);
    //     repository.deleteById(id);
    // }
}