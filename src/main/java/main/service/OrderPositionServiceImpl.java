package main.service;

import main.model.OrderPosition;
import main.repository.OrderPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPositionServiceImpl implements OrderPositionService {

	private OrderPositionRepository orderPositionRepository;

	@Autowired
	public OrderPositionServiceImpl(OrderPositionRepository orderPositionRepository) {
		this.orderPositionRepository = orderPositionRepository;
	}

	@Override
	public void save(OrderPosition position) {
		orderPositionRepository.saveAndFlush(position);
	}
}
