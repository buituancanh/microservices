package vn.elca.tech.microservices.order.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import vn.elca.tech.microservices.order.impl.model.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {
}
