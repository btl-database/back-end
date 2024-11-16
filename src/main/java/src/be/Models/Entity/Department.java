package src.be.Models.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "department", schema = "public")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DName")
    private String DepartmentName;

    @JoinColumn(name = "DeanDoctorCode")
    @OneToOne
    private Doctor DeanDoctor;
}
