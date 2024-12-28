package com.example.holyhillkiosk.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "menuCode", column = @Column(name = "foodcode"))
@AttributeOverride(name = "menuName", column = @Column(name = "foodname"))
@Table(name="food")
public class Food extends Menu{
    //
    @Column(name="foodoption")
    private boolean foodOption;

    @OneToMany(mappedBy = "food")
    private List<OrderedFood> orderedFood = new ArrayList<>();

    //생성자
    public Food(){

    }

    public Food(int code, String name){
        super(code, name);
    }
}
