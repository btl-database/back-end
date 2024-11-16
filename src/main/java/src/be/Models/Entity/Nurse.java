package src.be.Models.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import src.be.Models.Employee;

@Entity
@Table(name = "nurse", schema = "public")
public class Nurse extends Employee {

}
