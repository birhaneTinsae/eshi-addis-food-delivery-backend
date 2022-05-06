package com.eshi.addis.restaurant.workingHour;

import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.utils.Auditable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class WorkingHours extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    private DayOfWeek dayOfWeek;
    private LocalTime openAt;
    private LocalTime closeAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WorkingHours that = (WorkingHours) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Transient
    public boolean isClosed(){
        return LocalTime.now().isAfter(this.closeAt)&&LocalTime.now().isBefore(this.openAt);
    }
}
