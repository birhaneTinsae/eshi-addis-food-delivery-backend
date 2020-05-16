package com.eshi.addis.promotion;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "promotions")
@Data
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int views;
    @Lob
    private byte[] detail;
}
