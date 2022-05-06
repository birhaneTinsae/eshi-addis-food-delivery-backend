package com.eshi.addis.restaurant.workingHour;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkingHourMapper {

    WorkingHourDto toWorkingHour(WorkingHours updateWorkingHours);

    List<WorkingHourDto> toWorkingHours(List<WorkingHours> workingHours);
}
