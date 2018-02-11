package main.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "reagentsGoods")
public class GoodsReagent implements Goods {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "reagent_id")
	private Reagent reagentId;

	@Column(name = "type")
	private String type;

	@Column(name = "package")
	private String pack;

	@Column(name = "price")
	private Double price;

	@Column(name = "stock")
	private Integer stock;

	@Override
	public String getName() {
		return reagentId.getName();
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getPackage() {
		return pack;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Integer inStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Reagent getReagentId() {
		return reagentId;
	}

	public void setReagentId(Reagent reagentId) {
		this.reagentId = reagentId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GoodsReagent that = (GoodsReagent) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (type != null ? !type.equals(that.type) : that.type != null)
			return false;
		if (pack != null ? !pack.equals(that.pack) : that.pack != null)
			return false;
		if (price != null ? !price.equals(that.price) : that.price != null)
			return false;
		return stock != null ? stock.equals(that.stock) : that.stock == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (pack != null ? pack.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (stock != null ? stock.hashCode() : 0);
		return result;
	}
}
