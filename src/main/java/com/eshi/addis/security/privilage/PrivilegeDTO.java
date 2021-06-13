package com.eshi.addis.security.privilage;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrivilegeDTO implements Serializable {
    private long id;
    private String name;
}
