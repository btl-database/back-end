package src.be.Models.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import src.be.Models.Employee;

@Entity
@Table(name = "doctor", schema = "public")
public class Doctor extends Employee {

}
