package main.service;

import main.model.Cart;
import main.model.Order;
import main.model.User;
public interface OrderService {

	void addPositions(Order order, Cart cart);
	void save(Order order);
	void saveAndSetNumber(Order order);
	Order createOrder(Cart cart, User user);
}
