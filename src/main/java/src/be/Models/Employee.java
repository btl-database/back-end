package src.be.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import src.be.Models.Entity.Department;

@MappedSuperclass
public class Employee {
    @Id
    @Column(name = "Ecode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ECode;

    @Column(name = "EFirstName")
    private String EFirstName;

    @Column(name = "ELastName")
    private String ELastName;

    @Column(name = "EDoB")
    private String EDoB;

    @Column(name = "EGender")
    private String EGender;

    @Column(name = "EAddress")
    private String EAddress;

    @Column(name = "EStartDay")
    private String EStartDay;

    @Column(name = "SName")
    private String SName;

    @Column(name = "SYear")
    private String SYear;

    @ManyToOne
    @JoinColumn(name = "DepartmentCode")
    private Department department;
}
