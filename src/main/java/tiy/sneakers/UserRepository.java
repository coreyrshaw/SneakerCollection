package tiy.sneakers;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sulton on 5/22/2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}
