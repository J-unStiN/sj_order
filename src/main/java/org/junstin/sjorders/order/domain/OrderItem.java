package org.junstin.sjorders.order.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item; //(Item과 ManyToOne, NotNull)

    @JoinColumn(name = "ordering_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Ordering ordering; //(Ordering과 ManyToOne, NotNull)

    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private LocalDateTime updatedTime;


    public void orderNewQuantity(Item item, int count) {
        this.item = item;
        this.quantity = count;
        int itemStockQuantity = item.getStockQuantity();

        if(itemStockQuantity - quantity < 0) {
            throw new RuntimeException("주문이 재고보다 많습니다");
        }

        this.item.setStockQuantity(itemStockQuantity - quantity);

    }

    public void orderCancelQuantity(Item item, int count) {
        this.item = item;
        this.quantity = count;
        this.item.setStockQuantity(item.getStockQuantity() + quantity);
        ordering.setOrderStatus(OrderStatus.CANCELED);
    }


}
