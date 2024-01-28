package org.junstin.sjorders.order.repository;

import org.junstin.sjorders.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
}
