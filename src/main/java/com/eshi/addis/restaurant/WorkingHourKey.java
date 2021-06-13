package com.eshi.addis.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingHourKey implements Serializable {
    @Column(name ="restaurant_id")
    private String restaurantId;
    @Column(name ="week_day_id")
    private long weekDayId;
}
