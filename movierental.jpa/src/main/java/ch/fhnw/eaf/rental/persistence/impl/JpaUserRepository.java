package ch.fhnw.eaf.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<User> findById(Long id) {
		return Optional.ofNullable(em.find(User.class, id));
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public User save(User user) {
		return  em.merge(user);
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.getReference(User.class, id));
	}

	@Override
	public void delete(User user) {
		em.remove(user);
	}

	@Override
	public boolean existsById(Long id) {
//		return findById(id).isPresent();
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(u) FROM User u WHERE u.id = :id",
				Long.class);
		query.setParameter("id", id);
		return query.getSingleResult() > 0;
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
	}

	@Override
	public List<User> findByLastName(String lastName) {
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.lastName = :lastName",
				User.class);
		query.setParameter("lastName", lastName);
		return query.getResultList();
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.firstName = :firstName",
				User.class);
		query.setParameter("firstName", firstName);
		return query.getResultList();
	}

	@Override
	public List<User> findByEmail(String email) {
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.email = :email",
				User.class);
		query.setParameter("email", email);
		return query.getResultList();
	}

}
