package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    //주문
    @Transactional
    public Long order(Long memberId,Long itemId,int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
        //주문 생성
        Order order = Order.createOrder(member,delivery,orderItem);
        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }
    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
        //Entity내부 데이터만 바꾸면 변경감지해줌 => update sql문을 사용할 필요가 없어짐
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return
//    }
}