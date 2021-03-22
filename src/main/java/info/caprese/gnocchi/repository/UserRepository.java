package info.caprese.gnocchi.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import info.caprese.gnocchi.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(@Param("username") String username);
	@Query("SELECT u FROM User u WHERE u.userId = :userId")
	Optional<User> findOne(String userId);
}