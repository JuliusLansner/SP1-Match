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
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "wikilink")
    private String wikilink;

    @Column(name = "category")
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private HobbyType type;

    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> persons = new HashSet<>();
}
