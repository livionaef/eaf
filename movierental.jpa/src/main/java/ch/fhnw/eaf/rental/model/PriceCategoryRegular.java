package ch.fhnw.eaf.rental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Regular")
public class PriceCategoryRegular extends PriceCategory {

	@Override
	public double getCharge(int daysRented) {
		double result = 2;
		if (daysRented > 2)
			result += (daysRented - 2) * 1.5;
		return result;
	}

	@Override
	public String toString() {
		return "Regular";
	}
}
