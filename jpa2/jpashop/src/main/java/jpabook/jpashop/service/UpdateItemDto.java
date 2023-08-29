package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;
}
