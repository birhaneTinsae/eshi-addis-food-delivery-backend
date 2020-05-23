package com.eshi.addis.manager;

import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public class ManagerController implements Common<Manager,Manager> {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

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
