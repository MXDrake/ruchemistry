package main.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public class Cart implements Serializable{

	private long id;

	static private BigDecimal TAX = new BigDecimal(1.18);

	private HashMap<Goods, Integer> basket;

	private BigDecimal amount = new BigDecimal(0);

	private BigDecimal amountAndTax = new BigDecimal(0);

	public HashMap<Goods, Integer> getBasket() {
		return basket;
	}

	public Cart() {
		this.basket = new HashMap<>();
	}

	public void setBasket(Goods goods, Integer count) {
		this.basket.put(goods, count);
		//this.amount = this.amount.add(new BigDecimal(count).multiply(goods.getPrice()));
		this.amount = new BigDecimal(count).multiply(goods.getPrice());
		this.amountAndTax = this.amount.multiply(TAX);
	}

	public BigDecimal getAmount() {
		return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountAndTax() {
		return this.amountAndTax.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setAmountAndTax(BigDecimal amountAndTax) {
		this.amountAndTax = amountAndTax;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void addPosition(Goods goods, Integer count) {
		if (count < 1) {
			return;
		}

		if (this.basket.containsKey(goods)) {
			this.amount = this.amount.subtract(goods.getPrice().multiply(new BigDecimal(this.basket.get(goods))));
			this.amountAndTax = this.amount.multiply(TAX);
		}
		this.amount = this.amount.add(new BigDecimal(count).multiply(goods.getPrice()));
		this.amountAndTax = this.amount.multiply(TAX);
		this.basket.put(goods, count);
	}

	public void deletePosition(Goods goods) {
		this.amount = this.amount.subtract(goods.getPrice().multiply(new BigDecimal(this.basket.get(goods))));
		this.amountAndTax = this.amount.multiply(TAX);
		this.basket.remove(goods);
	}

	public int getSize(){
		if (basket == null){
			return 0;
		}
		return basket.size();
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
