package main.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private User customerId;

	@Column(name = "number")
	private String orderNumber;

	@Column(name = "deleted")
	private boolean deleted;

	@Column(name = "dateCreated")
	private Date dateCreated;

	@Column(name = "status")
	private String status;

	@Column(name = "dateDelivery")
	private Date dateDelivery;

	@Column(name = "payment")
	private boolean payment;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "amountWithTax")
	private BigDecimal amountWithTax;

	@OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderPosition> positions;

	public Order() {
	}

	public Order(User user, Cart cart) {
		this.customerId = user;
		this.dateCreated = new Date();
		this.deleted = false;
		this.payment = false;
		this.amount = cart.getAmount();
		this.amountWithTax = cart.getAmountAndTax();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCustomerId() {
		return customerId;
	}

	public void setCustomerId(User customerId) {
		this.customerId = customerId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountWithTax() {
		return amountWithTax;
	}

	public void setAmountWithTax(BigDecimal amountWithTax) {
		this.amountWithTax = amountWithTax;
	}

	public List<OrderPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<OrderPosition> positions) {
		this.positions = positions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Order order = (Order) o;

		if (deleted != order.deleted)
			return false;
		if (payment != order.payment)
			return false;
		if (id != null ? !id.equals(order.id) : order.id != null)
			return false;
		if (customerId != null ? !customerId.equals(order.customerId) : order.customerId != null)
			return false;
		if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null)
			return false;
		if (dateCreated != null ? !dateCreated.equals(order.dateCreated) : order.dateCreated != null)
			return false;
		if (status != null ? !status.equals(order.status) : order.status != null)
			return false;
		if (dateDelivery != null ? !dateDelivery.equals(order.dateDelivery) : order.dateDelivery != null)
			return false;
		if (amount != null ? !amount.equals(order.amount) : order.amount != null)
			return false;
		if (amountWithTax != null ? !amountWithTax.equals(order.amountWithTax) : order.amountWithTax != null)
			return false;
		return positions != null ? positions.equals(order.positions) : order.positions == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
		result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (dateDelivery != null ? dateDelivery.hashCode() : 0);
		result = 31 * result + (payment ? 1 : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (amountWithTax != null ? amountWithTax.hashCode() : 0);
		result = 31 * result + (positions != null ? positions.hashCode() : 0);
		return result;
	}
}
