package main.model;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private long id;

	static private Double TAX = 1.18d;

	private HashMap<Goods, Integer> basket;

	@Min(0)
	private Double amount = 0d;

	private Double amountAndTax = 0d;

	public HashMap<Goods, Integer> getBasket() {
		return basket;
	}

	public Cart() {
		this.basket = new HashMap<>();
	}

	public void setBasket(Goods Goods, Integer count) {
		this.basket.put(Goods, count);
		this.amount += Goods.getPrice() * count;
		this.amountAndTax = this.amount * TAX;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountAndTax() {
		return amountAndTax;
	}

	public void setAmountAndTax(Double amountAndTax) {
		this.amountAndTax = amountAndTax;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void addPosition(Goods goods, Integer count) {
		if (count<1) {
			return;
		}

		if (this.basket.containsKey(goods)) {
			this.amount -= goods.getPrice() * this.basket.get(goods);
			this.amountAndTax -= this.amount * TAX;
		}
		this.amount += goods.getPrice() * count;
		this.amountAndTax = this.amount * TAX;
		this.basket.put(goods, count);
	}

	public void deletePosition(Goods goods){
		this.basket.remove(goods);
		this.amount -= goods.getPrice() * this.basket.get(goods);
		this.amountAndTax -= this.amount * TAX;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Cart cart = (Cart) o;

		if (id != cart.id)
			return false;
		if (basket != null ? !basket.equals(cart.basket) : cart.basket != null)
			return false;
		if (amount != null ? !amount.equals(cart.amount) : cart.amount != null)
			return false;
		return amountAndTax != null ? amountAndTax.equals(cart.amountAndTax) : cart.amountAndTax == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (basket != null ? basket.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (amountAndTax != null ? amountAndTax.hashCode() : 0);
		return result;
	}




}
