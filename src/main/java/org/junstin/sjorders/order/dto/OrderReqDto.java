package org.junstin.sjorders.order.dto;

import java.util.List;

public record OrderReqDto (
        Long memberId,
        List<Long> itemId,
        List<Long> count

) {
}
