package ch.fhnw.eaf.rental.model;

public class PriceCategoryChildren extends PriceCategory {

	@Override
	public double getCharge(int daysRented) {
		double result = 1.5;
		if (daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}
		return result;
	}

	@Override
	public String toString() {
		return "Children";
	}
}
