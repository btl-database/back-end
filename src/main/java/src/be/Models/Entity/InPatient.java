package src.be.Models.Entity;


import jakarta.persistence.*;
import src.be.Models.Patient;

import java.util.Date;

@Entity
@Table(name = "inpatient", schema = "public")
public class InPatient extends Patient {
    @OneToOne
    @JoinColumn(name = "NurseCode")
    private Nurse nurse;

    @Column(name = "sick_room")
    private String SickRoom;

    @Column(name = "fee")
    private int Fee;

    @Column(name = "discharge_date")
    private Date DischargeDate;

    @Column(name = "admission_date")
    private Date AdmissionDate;

    @Column(name = "diagnosis")
    private String Diagnosis;
}
