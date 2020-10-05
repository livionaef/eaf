package ch.fhnw.eaf.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;

@Repository
public class JpaPriceCategoryRepository implements PriceCategoryRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<PriceCategory> findById(Long id) {
		return Optional.ofNullable(em.find(PriceCategory.class, id));
	}

	@Override
	public List<PriceCategory> findAll() {
		TypedQuery<PriceCategory> query = em.createQuery("SELECT pc FROM PriceCategory pc", PriceCategory.class);
		return query.getResultList();
	}

	@Override
	public PriceCategory save(PriceCategory priceCategory) {
		return em.merge(priceCategory);
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.getReference(PriceCategory.class, id));
	}

	@Override
	public void delete(PriceCategory priceCategory) {
		em.remove(priceCategory);
	}

	@Override
	public boolean existsById(Long id) {
//		return findById(id).isPresent();
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(pc) FROM PriceCategory pc WHERE pc.id = :id",
				Long.class);
		query.setParameter("id", id);
		return query.getSingleResult() > 0;
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(pc) FROM PriceCategory pc", Long.class).getSingleResult();
	}

}
