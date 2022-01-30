package av3.compass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import av3.compass.modelo.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

}
