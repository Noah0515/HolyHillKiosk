package com.example.holyhillkiosk.Entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@AttributeOverride(name = "menuCode", column = @Column(name = "beveragecode"))
@AttributeOverride(name = "menuName", column = @Column(name = "beveragename"))
@Table(name="beverage")
public class Beverage extends Menu{


    //생성자
    public Beverage(){

    }

    public Beverage(int code, String name){
        super(code, name);
    }
}
