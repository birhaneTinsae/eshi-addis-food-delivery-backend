package com.eshi.addis.utils;

public interface Common<T> {

    T create(T t);

    T update(T t);

    Iterable<T> getAll();

    T getOne(long id);

}
