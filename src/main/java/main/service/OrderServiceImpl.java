package main.service;

import main.model.*;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public void addPositions(Order order, Cart cart) {

		List<OrderPosition> positions = new ArrayList<>();
		for (HashMap.Entry<Goods, Integer> element : cart.getBasket().entrySet()) {
			positions.add(new OrderPosition(order, element.getKey(), element.getValue()));
		}
		order.setPositions(positions);
	}

	@Override
	@Transactional
	public Order createOrder(Cart cart, User user) {

		Order order = new Order(user, cart);
		saveAndSetNumber(order);

		List<OrderPosition> positions = new ArrayList<>();
		for (HashMap.Entry<Goods, Integer> element : cart.getBasket().entrySet()) {
			positions.add(new OrderPosition(order, element.getKey(), element.getValue()));
		}
		order.setPositions(positions);
		save(order);
		return order;
	}

	@Override
	public void save(Order order) {
		orderRepository.saveAndFlush(order);
	}

	@Override
	public void saveAndSetNumber(Order order) {
		orderRepository.saveAndFlush(order);
		order.setOrderNumber("Заказ №"+order.getId());
	}

}
