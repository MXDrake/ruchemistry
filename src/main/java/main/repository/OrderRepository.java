package main.repository;

import main.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order getByIdAndDeleted(Long id, boolean deleted);
}