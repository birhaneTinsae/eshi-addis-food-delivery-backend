package com.eshi.addis.model;

import com.eshi.addis.restaurant.WorkingHours;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class WeekDay extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int dayOfWeek;
    @OneToMany(mappedBy = "weekDay")
    private List<WorkingHours> workingHoursList;

}
