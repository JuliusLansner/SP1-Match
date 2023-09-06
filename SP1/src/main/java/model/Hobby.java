package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "wikilink")
    private String wikilink;

    @Column(name = "category")
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private HobbyType type;

    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Person> persons = new HashSet<>();

    public void removePerson(Person person){
        this.persons.remove(person);
        person.getHobbies().remove(this);
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "name='" + name + '\'' +
                ", wikilink='" + wikilink + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
