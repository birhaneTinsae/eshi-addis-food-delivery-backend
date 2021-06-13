package com.eshi.addis.restaurant;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkingHourRepository extends PagingAndSortingRepository<WorkingHours, WorkingHourKey> {

}
