package org.junstin.sjorders.order.service;

import org.junstin.sjorders.member.domain.Member;
import org.junstin.sjorders.member.repository.MemberRepository;
import org.junstin.sjorders.order.domain.Item;
import org.junstin.sjorders.order.domain.OrderItem;
import org.junstin.sjorders.order.domain.OrderStatus;
import org.junstin.sjorders.order.domain.Ordering;
import org.junstin.sjorders.order.dto.OrderReqDto;
import org.junstin.sjorders.order.repository.ItemRepository;
import org.junstin.sjorders.order.repository.OrderRepository;
import org.junstin.sjorders.order.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderingRepository orderingRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, MemberRepository memberRepository, OrderingRepository orderingRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.orderingRepository = orderingRepository;
    }

    public List<OrderItem> orderAll() {
        List<OrderItem> all = orderRepository.findAll();
        return all;
    }

    public List<Ordering> orderingListAll(Long memberId) {
        List<Ordering> list = orderingRepository.findAll().stream()
                .filter(ordering -> ordering.getMember().getId() == memberId)
                .toList();

        return list;
    }

    public void orderCancel(Long orderingId) {
        List<OrderItem> findOrderingList = orderRepository.findAll().stream()
                .filter(orderItem -> orderItem.getOrdering().getId() == orderingId)
                .toList();

        for (var orderItem : findOrderingList) {
            Item item = itemRepository.findById(orderItem.getItem().getId()).orElseThrow(() -> new RuntimeException("주문취소오류입니다"));
            orderItem.orderCancelQuantity(item, orderItem.getQuantity());
        }

        orderRepository.saveAll(findOrderingList);
    }

    public Ordering orderingNew(OrderReqDto orderReqDto) {

        Member findMember = memberRepository.findById(orderReqDto.memberId()).orElseThrow(() -> new RuntimeException("주문시 맴버가 없습니다"));
        findMember.setOrderings(orderingListAll(findMember.getId()));
        Ordering ordering = Ordering.builder()
                .member(findMember)
                .orderStatus(OrderStatus.ORDERED)
                .build();

        //orderingRepository.save(ordering);

        orderItemNew(orderReqDto, ordering);

        return ordering;
    }

    public void orderItemNew(OrderReqDto orderReqDto, Ordering ordering) {
        List<OrderItem> orderItems = new ArrayList<>();

        Long memberId = orderReqDto.memberId();
        long[] itemsId = orderReqDto.itemId().stream().mapToLong(Long::longValue).toArray();
        int[] counts = orderReqDto.count().stream().mapToInt(Long::intValue).toArray();

        for (int i = 0; i < itemsId.length; i++) {
            Item findItem = itemRepository.findById(itemsId[i]).orElseThrow(() -> new RuntimeException("없는 아이템 id 입니다."));

            OrderItem orderItem = OrderItem.builder()
                    .quantity(counts[i])
                    .item(findItem)
                    .ordering(ordering)
                    .build();

            orderItem.orderNewQuantity(findItem, counts[i]);
            //itemRepository.save(findItem);
            orderItems.add(orderItem);
        }

        //itemRepository.saveAll(findItemsAll);
        orderRepository.saveAll(orderItems);
    }


    public void itemCreate() {
        List<Item> sampleItem = List.of(
                new Item(1L, "소고기짬뽕", 11000, 100, "도야짬뽕", LocalDateTime.now(), LocalDateTime.now()),
                new Item(2L, "통영굴짬뽕", 10000, 70, "도야짬뽕", LocalDateTime.now(), LocalDateTime.now()),
                new Item(3L, "탕수육", 26000, 30, "도야짬뽕", LocalDateTime.now(), LocalDateTime.now())
        );

        itemRepository.saveAll(sampleItem);
    }






}
