package com.example.holyhillkiosk.Entity;

import jakarta.persistence.*;


@Entity
@AttributeOverride(name = "menuNum", column = @Column(name = "foodnum"))
@Table(name="orderedfood")
public class OrderedFood extends OrderedMenu{
    //db에 매핑될 변수들
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "menuOrderId", column = @Column(name = "foodorderid")),
            @AttributeOverride(name = "menuCode", column = @Column(name = "foodcode"))  // 'menuCode'를 'ordered_menu_code'로 변경
    })
    private OrderedMenuId orderedFoodId;
    @Column(name = "optionchoice")
    private boolean optionChoice;

    //매핑 안될 변수들
    @ManyToOne
    @JoinColumn(name = "foodorderid", insertable = false, updatable = false)
    private FoodOrder foodOrder;
    @ManyToOne
    @JoinColumn(name = "foodcode", insertable = false, updatable = false)
    private Food food;


    //생성자
    public OrderedFood(){

    }

    public OrderedFood(String id, int code, int num, boolean choice){
        super(num);
        orderedFoodId = new OrderedMenuId(id, code);
        optionChoice = choice;
    }

    @Override
    public String toString(){
        return orderedFoodId.toString() + ", foodNum = " + menuNum + ", optionChoice = " + optionChoice;
    }
}
