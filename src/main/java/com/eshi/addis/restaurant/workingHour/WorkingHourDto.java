package com.eshi.addis.restaurant.workingHour;

import lombok.Data;


import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class WorkingHourDto implements Serializable {
    private DayOfWeek weekDay;
  //  @Field(type = FieldType.Date_Nanos)
    private LocalTime openAt;
   // @Field(type = FieldType.Date_Nanos)
    private LocalTime closeAt;
    private boolean closed;
}
