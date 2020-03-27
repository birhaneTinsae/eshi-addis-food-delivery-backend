package com.eshi.addis.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Contact {
    private String phoneNo;
    private String email;
    private String phoneNoTwo;
}
