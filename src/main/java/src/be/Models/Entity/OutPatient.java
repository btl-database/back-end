package src.be.Models.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import src.be.Models.Patient;

@Entity
@Table(name = "outpatient", schema = "public")
public class OutPatient extends Patient {
    @OneToOne
    @JoinColumn(name = "DoctorCode")
    private Doctor doctor;
}
