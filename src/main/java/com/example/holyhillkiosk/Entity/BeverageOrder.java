package com.example.holyhillkiosk.Entity;

import jakarta.persistence.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "orderTime", column = @Column(name = "ordertime"))
@AttributeOverride(name = "orderId", column = @Column(name = "orderid"))
@AttributeOverride(name = "detailOrderId", column = @Column(name = "beverageorderid"))
@AttributeOverride(name = "orderComplete", column = @Column(name = "beverageordercomplete"))
@Table(name="beverageorder")
public class BeverageOrder extends DetailedOrder{
    //
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ordertime", referencedColumnName = "ordertime", insertable = false, updatable = false),
            @JoinColumn(name = "orderid", referencedColumnName = "orderid", insertable = false, updatable = false)
    })
    private Orders order;


    @OneToMany(mappedBy = "beverageOrder")
    private List<OrderedBeverage> orderedBeverages = new ArrayList<>();

    //생성자
    public BeverageOrder(){

    }

    public BeverageOrder(OrdersId id){
        super(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        detailOrderId = orderTime.format(formatter) + "B" + orderId;
    }
}
