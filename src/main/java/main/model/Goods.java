package main.model;
import javax.persistence.Entity;
import java.math.BigDecimal;

public interface Goods {

	String getName();
	BigDecimal getPrice();
	String getType();
	String getPackage();
	Long getId();
	Integer inStock();

	
}
