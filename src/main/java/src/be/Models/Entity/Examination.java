package src.be.Models.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="examination", schema = "public")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private int fee;

    private Date date;

    private Date SecondExaminationDate;

    private String Diagnosis;


}
