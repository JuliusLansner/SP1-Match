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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Hobby> hobbies = new HashSet<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ContactInfo contactInfo;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Adress adress;

}
