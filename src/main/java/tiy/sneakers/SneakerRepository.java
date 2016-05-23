package tiy.sneakers;



import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SneakerRepository extends CrudRepository<Sneaker, Integer> {
    List<Sneaker> findByUser(User user);
}