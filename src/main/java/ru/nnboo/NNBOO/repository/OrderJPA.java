package ru.nnboo.NNBOO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nnboo.NNBOO.entity.Order;

@Repository
public interface OrderJPA extends JpaRepository<Order, Long> {
}
