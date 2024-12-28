package com.example.holyhillkiosk.Entity;

import jakarta.persistence.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "orderTime", column = @Column(name = "ordertime"))
@AttributeOverride(name = "orderId", column = @Column(name = "orderid"))
@AttributeOverride(name = "detailOrderId", column = @Column(name = "foodorderid"))
@AttributeOverride(name = "orderComplete", column = @Column(name = "foodordercomplete"))
@Table(name="foodorder")
public class FoodOrder extends DetailedOrder{
    //
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ordertime", referencedColumnName = "ordertime", insertable = false, updatable = false),
            @JoinColumn(name = "orderid", referencedColumnName = "orderid", insertable = false, updatable = false)
    })
    private Orders order;

    @OneToMany(mappedBy = "foodOrder")
    private List<OrderedFood> orderedFood = new ArrayList<>();

    //생성자
    public FoodOrder(){

    }

    public FoodOrder(OrdersId id){
        super(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        detailOrderId = orderTime.format(formatter) + "F" + orderId;
    }


}
