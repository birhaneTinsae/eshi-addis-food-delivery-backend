package com.eshi.addis.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
public class Contact {
    @NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number shouldn't be blank")
    private String phoneNo;
    private String email;
    private String phoneNoTwo;
}
