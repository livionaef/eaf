package ch.fhnw.eaf.rental.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIES")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MOVIE_ID")
	private Long id;

	@Column(name="MOVIE_TITLE")
	private String title;
	
	@Column(name="MOVIE_RELEASEDATE")
	private LocalDate releaseDate;
	
	@Column(name="MOVIE_RENTED")
	private boolean rented;
	
	@ManyToOne // Movie is the owner of the relationship
	@JoinColumn(name="PRICECATEGORY_FK")
	private PriceCategory priceCategory;

	// nicht-privater no-arg Konstruktor (jede @Entity Klasse braucht diesen)
	protected Movie() {	}

	public Movie(Long id, String title, LocalDate releaseDate, boolean rented, PriceCategory priceCategory) {
		this(title, releaseDate, priceCategory);
		setId(id);
		setRented(rented);
	}

	public Movie(String title, LocalDate releaseDate, PriceCategory priceCategory) {
		if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		this.title = title;
		this.releaseDate = releaseDate;
		this.priceCategory = priceCategory;
		this.rented = false;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
