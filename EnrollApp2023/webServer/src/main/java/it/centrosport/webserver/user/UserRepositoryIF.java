package it.centrosport.webserver.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryIF extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
}
