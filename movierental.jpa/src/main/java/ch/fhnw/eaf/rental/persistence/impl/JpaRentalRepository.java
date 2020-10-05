package ch.fhnw.eaf.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.RentalRepository;

@Repository
public class JpaRentalRepository implements RentalRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Rental> findById(Long id) {
		return Optional.ofNullable(em.find(Rental.class, id));
	}

	@Override
	public List<Rental> findAll() {
		TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r", Rental.class);
		return query.getResultList();
	}

	@Override
	public Rental save(Rental rental) {
		return em.merge(rental);
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.getReference(Rental.class, id));
	}

	@Override
	public void delete(Rental rental) {
		em.remove(rental);
	}

	@Override
	public boolean existsById(Long id) {
//		return findById(id).isPresent();
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(r) FROM Rental r WHERE r.id = :id",
				Long.class);
		query.setParameter("id", id);
		return query.getSingleResult() > 0;
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(r) FROM Rental r", Long.class).getSingleResult();
	}

	@Override
	public List<Rental> findByUser(User user) {
		return user.getRentals();
	}

}
