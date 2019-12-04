package mk.finki.ukim.mk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Boolean spicy;

    private Float amount;

    private Boolean veggie;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    private List<Pizza> pizzas;

    public Ingredient(Long id, String name, Boolean spicy, Float amount, Boolean veggie) {
        this.id = id;
        this.name = name;
        this.spicy = spicy;
        this.amount = amount;
        this.veggie = veggie;
        this.pizzas = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
