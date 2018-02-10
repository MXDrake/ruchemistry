package main.model;
import javax.persistence.Entity;


public interface Goods {

	String getName();
	Double getPrice();
	String getType();
	String getPackage();
	Long getId();
	Integer inStock();

	
}
