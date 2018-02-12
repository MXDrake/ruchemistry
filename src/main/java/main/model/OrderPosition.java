package main.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
@Entity
@Table(name = "orderPosition")
public class OrderPosition {

	@Transient
	static private BigDecimal TAX = new BigDecimal(1.18);

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order orderId;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "package")
	private String pack;

	@Column(name = "count")
	private Integer count;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "priceWithTax")
	private BigDecimal priceWithTax;

	@Column(name = "deleted")
	private boolean deleted;

	public OrderPosition(){

	}

	public OrderPosition( Order order, Goods goods, Integer count){
		this.name = goods.getName();
		this.count = count;
		this.type = goods.getType();
		this.pack = goods.getPackage();
		this.price = goods.getPrice().multiply(new BigDecimal(count));
		this.priceWithTax = this.price.multiply(TAX);
		this.deleted = false;
		this.orderId = order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OrderPosition that = (OrderPosition) o;

		if (deleted != that.deleted)
			return false;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (type != null ? !type.equals(that.type) : that.type != null)
			return false;
		if (pack != null ? !pack.equals(that.pack) : that.pack != null)
			return false;
		if (count != null ? !count.equals(that.count) : that.count != null)
			return false;
		if (price != null ? !price.equals(that.price) : that.price != null)
			return false;
		return priceWithTax != null ? priceWithTax.equals(that.priceWithTax) : that.priceWithTax == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (pack != null ? pack.hashCode() : 0);
		result = 31 * result + (count != null ? count.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (priceWithTax != null ? priceWithTax.hashCode() : 0);
		result = 31 * result + (deleted ? 1 : 0);
		return result;
	}
}
