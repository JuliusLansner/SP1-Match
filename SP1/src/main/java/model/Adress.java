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
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id")
    private int id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private int streetNumber;

    @OneToOne
    private Person person;

    @OneToOne(mappedBy = "adress", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ZipCode zipCode;


}
