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
public class ZipCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zip_id")
    private int id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "municipality_name")
    private String municipality_name;

    @OneToOne
    private Adress adress;

}

