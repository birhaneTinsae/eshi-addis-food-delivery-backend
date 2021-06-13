package com.eshi.addis.restaurant;

import com.eshi.addis.model.WeekDay;
import lombok.Data;

import java.time.LocalTime;

@Data
public class WorkingHourDTO {
    private WeekDay weekDay;
    private LocalTime openAt;
    private LocalTime closeAt;
    private boolean closed;
}
