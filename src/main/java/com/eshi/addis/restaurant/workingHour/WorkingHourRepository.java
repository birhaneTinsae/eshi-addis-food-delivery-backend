package com.eshi.addis.restaurant.workingHour;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHourRepository extends PagingAndSortingRepository<WorkingHours, Long> {
}
