package org.junstin.sjorders.order.repository;

import org.junstin.sjorders.order.domain.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {
}
