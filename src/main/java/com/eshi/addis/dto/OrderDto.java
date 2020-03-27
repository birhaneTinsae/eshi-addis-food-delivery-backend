package com.eshi.addis.dto;

import lombok.Data;
import org.springframework.data.geo.Point;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private List<OrderMenuDto> menus = new ArrayList<>();
    private String specialNote;
    private LocalDateTime deliveryAt=LocalDateTime.now();
    private Point deliveryLocation;

}
