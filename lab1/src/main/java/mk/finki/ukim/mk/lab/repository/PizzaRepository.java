package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {

    Optional<Pizza> findByName(String name);

    void deleteByName(String name);

    @Query("select pizza from Pizza pizza join pizza.ingredients i group by pizza.id having count(i) < :total")
    List<Pizza> findAllByTotalIngredients(@Param("total") Long total);

    @Query("select ing from Pizza pizza join pizza.ingredients ing where pizza.name = :name1 and ing.id in " +
            "(select i.id from Pizza pizza2 join pizza2.ingredients i where pizza2.name = :name2)")
    List<Ingredient> findAllSameIngredients(@Param("name1") String name1, @Param("name2") String name2);

    List<Pizza> findDistinctByIngredients_SpicyIsTrue();
}
