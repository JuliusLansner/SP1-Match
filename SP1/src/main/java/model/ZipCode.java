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
    @Column(name = "zip")
    private int zip;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "municipality_name")
    private String municipality_name;

    @OneToMany(mappedBy = "zipCode", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Adress> adress = new HashSet<>();

    @Override
    public String toString() {
        return "ZipCode{" +
                "zip=" + zip +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", municipality_name='" + municipality_name + '\'' +
                '}';
    }
}

