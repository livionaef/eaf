package ch.fhnw.eaf.rental.persistence;

import java.util.List;

import ch.fhnw.eaf.rental.model.User;

public interface UserRepository extends Repository<User, Long> {
	List<User> findByLastName(String lastName);
	List<User> findByFirstName(String firstName);
	List<User> findByEmail(String email);
}
