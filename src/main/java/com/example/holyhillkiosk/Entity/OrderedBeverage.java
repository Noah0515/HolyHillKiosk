package com.example.holyhillkiosk.Entity;

import jakarta.persistence.*;

@Entity
@AttributeOverride(name = "menuNum", column = @Column(name = "beveragenum"))
@Table(name="orderedbeverage")
public class OrderedBeverage extends OrderedMenu{
    //
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "menuOrderId", column = @Column(name = "beverageorderid")),
            @AttributeOverride(name = "menuCode", column = @Column(name = "beveragecode"))  // 'menuCode'를 'ordered_menu_code'로 변경
    })
    private OrderedMenuId orderedBeverageId;
    @ManyToOne
    @JoinColumn(name = "beverageorderid", insertable = false, updatable = false)
    private BeverageOrder beverageOrder;
    @ManyToOne
    @JoinColumn(name = "beveragecode", insertable = false, updatable = false )
    private Beverage beverage;
    //생성자
    public OrderedBeverage(){

    }

    public OrderedBeverage(String id, int code, int num){
        super(num);
        orderedBeverageId = new OrderedMenuId(id, code);

    }

    @Override
    public String toString(){
        return orderedBeverageId.toString() + ", beverageNum = " + menuNum;
    }
}
