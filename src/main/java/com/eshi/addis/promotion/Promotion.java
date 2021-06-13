package com.eshi.addis.promotion;

import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "promotions")
@Data
public class Promotion extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int views;
    @Lob
    private byte[] detail;
}
