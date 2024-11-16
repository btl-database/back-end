package src.be.Models;

import jakarta.persistence.*;

@MappedSuperclass
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;

    @Column(name = "PNumber")
    private String PNumber ;

    @Column(name = "PFirstName")
    private String PFirstName;

    @Column(name = "PLastName")
    private String PLastName;

    @Column(name = "PGender")
    private String PGender;

    @Column(name = "PDoB")
    private String PDoB;

    @Column(name = "PAddress")
    private String PAddress;

    @Column(name = "PPhoneNumber")
    private String PPhoneNumber;
}
