package tiy.sneakers;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sulton on 5/22/2016.
 */
public interface SneakerRepository extends CrudRepository<Sneaker, Integer> {
    List<Sneaker> findByModel(String model);
    List<Sneaker> findByModelNo(int modelNo);
    List<Sneaker> findByColor(String color);
    List<Sneaker> findByReleaseYear(int ReleaseYear);
    List<Sneaker> findByPrice(double price);

    @Query("SELECT g FROM Sneaker g WHERE g.text LIKE ?1%")
    List<Sneaker> findByNameStartsWith(String text);
}
