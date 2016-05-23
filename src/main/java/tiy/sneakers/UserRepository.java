package tiy.sneakers;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by willi on 5/23/2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
}