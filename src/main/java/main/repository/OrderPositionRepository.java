package main.repository;

import main.model.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {

}
