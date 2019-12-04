package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, String> {

    Page<Ingredient> findAllBySpicy(Boolean spicy, Pageable pageable);

    List<Ingredient> findAllBySpicy(Boolean spicy);

    Optional<Ingredient> findByName(String name);

    @Modifying
    void deleteByName(String name);
}
