package com.eshi.addis.rate;

import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name="rates")
public class Rate extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="`from`")
    private double from;
    @Column(name ="`to`")
    private double to;
    private double price;
}
