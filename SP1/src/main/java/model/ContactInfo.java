package model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class ContactInfo {

    public ContactInfo(String email, int phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactInfo_id")
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private long phoneNumber;

    @OneToOne(mappedBy = "contactInfo")
    private Person person;

    @Override
    public String toString() {
        return "ContactInfo{" +
                "email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @PrePersist
    private void checkNumber() {
        long phoneNumber = this.getPhoneNumber();
        String numberString = String.valueOf(phoneNumber);
        String danishNumb = "45";

        if (numberString.length() == 8) {

            if (!numberString.startsWith(danishNumb)) {
                numberString = danishNumb + numberString;
                this.setPhoneNumber(Long.parseLong(numberString));
            }
        } else {
            System.out.println("Not a valid number");
        }
    }

    @PreUpdate
    private void updateCheckNumber(){
        long phoneNumber = this.getPhoneNumber();
        String numberString = String.valueOf(phoneNumber);
        String danishNumb = "45";

        if (numberString.length() == 8) {

            if (!numberString.startsWith(danishNumb)) {
                numberString = danishNumb + numberString;
                this.setPhoneNumber(Long.parseLong(numberString));
            }
        } else {
            System.out.println("Not a valid number");
        }
    }
}