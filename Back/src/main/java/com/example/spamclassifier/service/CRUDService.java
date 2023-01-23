package com.example.spamclassifier.service;

public interface CRUDService<T> {
    T find(Long id);

    T save(T t);

    void delete(Long id);
}
