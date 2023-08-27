package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")//연관관계 주인
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간

    @Enumerated(EnumType.STRING) // 주문상태 [ORDER, CANCEL]
    private OrderStatus status;

    //연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        System.out.println("this = " + this);
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}


/*
@Builder
private Order(Member member, Delivery delivery, List<OrderItem> orderItems,LocalDateTime orderDate,OrderStatus status){
    this.member = member;
    this.delivery = delivery;
    this.orderItems = orderItems;
    this.orderDate = orderDate;
    this.status = status;
    if (member != null) {
        member.getOrders().add(this);
   }
    }
 */