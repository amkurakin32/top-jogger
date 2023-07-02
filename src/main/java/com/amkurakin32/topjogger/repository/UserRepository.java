package com.amkurakin32.topjogger.repository;

import com.amkurakin32.topjogger.model.db.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByLogin(String login);
}
