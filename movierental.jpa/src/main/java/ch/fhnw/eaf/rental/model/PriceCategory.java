package ch.fhnw.eaf.rental.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.fhnw.eaf.rental.json.PriceCategoryDeserializer;
import ch.fhnw.eaf.rental.json.PriceCategorySerializer;

@JsonDeserialize(using = PriceCategoryDeserializer.class)
@JsonSerialize(using = PriceCategorySerializer.class)
@Entity
@Table(name = "PRICECATEGORIES")
@DiscriminatorColumn(name="PRICECATEGORY_TYPE") // default DTYPE
public abstract class PriceCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRICECATEGORY_ID")
	private Long id;
	
	// nicht-privater no-arg Konstruktor
	protected PriceCategory() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract double getCharge(int daysRented);

	public int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}
