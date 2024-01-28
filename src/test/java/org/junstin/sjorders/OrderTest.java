package org.junstin.sjorders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junstin.sjorders.member.service.MemberService;
import org.junstin.sjorders.order.dto.OrderReqDto;
import org.junstin.sjorders.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("기본아이템 넣기")
    public void 기본아이템넣기() {

    }

    @Test
    @DisplayName("주문하기")
    public void 주문하기() {
        List<Long> items = List.of(1L, 2L, 3L);
        List<Long> counts = List.of(3L, 2L, 1L);
        OrderReqDto orderReqDto = new OrderReqDto(5L, items, counts);

        orderService.orderingNew(orderReqDto);

        Assertions.assertThat(1).isEqualTo(1);
    }

    @Test
    @DisplayName("주문취소하기")
    public void 주문취소() {
        orderService.orderCancel(23L);
    }


}
