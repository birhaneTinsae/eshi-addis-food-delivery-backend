package com.eshi.addis.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDistance {
    private Metric distance;
    private Metric duration;
}


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
class Metric{
    private String text;
    private long value;
}
