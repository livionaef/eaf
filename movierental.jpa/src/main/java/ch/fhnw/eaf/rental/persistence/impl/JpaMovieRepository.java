package ch.fhnw.eaf.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;

@Repository
public class JpaMovieRepository implements MovieRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Movie> findById(Long id) {
		return Optional.ofNullable(em.find(Movie.class, id));
	}

	@Override
	public List<Movie> findAll() {
		TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
		return query.getResultList();
	}

	@Override
	public Movie save(Movie movie) {
		return em.merge(movie);
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.getReference(Movie.class, id));
	}

	@Override
	public void delete(Movie movie) {
		em.remove(movie);		
	}

	@Override
	public boolean existsById(Long id) {
//		return findById(id).isPresent(); -> Entität wird in Persistenzkontext geladen!
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(m) FROM Movie m WHERE m.id = :id",
				Long.class);
		query.setParameter("id", id);
		return query.getSingleResult() > 0; // -> Entität wird nicht in Persistenzkontext geladen!
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(m) FROM Movie m", Long.class).getSingleResult();
	}

	@Override
	public List<Movie> findByTitle(String title) {
		TypedQuery<Movie> query = em.createQuery(
				"SELECT m FROM Movie m WHERE m.title = :title",
				Movie.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

}
