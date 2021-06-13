package com.eshi.addis.restaurant;

import com.eshi.addis.model.WeekDay;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data
public class WorkingHours implements Serializable {
    @EmbeddedId
    private WorkingHourKey id;

    @ManyToOne
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @MapsId("weekDayId")
    @JoinColumn(name = "week_day_id")
    private WeekDay weekDay;

    private LocalTime openAt;
    private LocalTime closeAt;
    private boolean closed;
}
