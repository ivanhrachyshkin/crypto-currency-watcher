package by.hrachyshkin.watcher.repository;

import by.hrachyshkin.watcher.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName (final String name);
}
