package com.eshi.addis.manager;

import com.eshi.addis.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagerService implements Common<Manager, Manager> {
    private final ManagerRepository managerRepository;


    @Override
    public Manager store(@Valid Manager manager) {
        return null;
    }

    @Override
    public Iterable<Manager> store(List<@Valid Manager> t) {
        return null;
    }

    @Override
    public Manager show(long id) {
        return null;
    }

    @Override
    public Manager update(long id, @Valid Manager manager) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<Manager> getAll(Pageable pageable) {
        return null;
    }
}
