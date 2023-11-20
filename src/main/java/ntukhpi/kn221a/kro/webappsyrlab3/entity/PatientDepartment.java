package ntukhpi.kn221a.kro.webappsyrlab3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient_department")
public class PatientDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pname", nullable = false)
    private String pname;

    @Column(name = "full_years", nullable = false)
    private int fullYears;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private HospitalDepartment department;

    @Column(name = "arrival", nullable = false, unique = true)
    private LocalDateTime arrival;

    @Column(name = "number", nullable = false)
    private int number;

    public PatientDepartment(String surname, String name, String pname,
                             int fullYears, HospitalDepartment department,
                             LocalDateTime arrival, int number) {
        this.id = 0L;
        this.surname = surname;
        this.name = name;
        this.pname = pname;
        this.fullYears = fullYears;
        this.department = department;
        this.arrival = arrival;
        this.number = number;
    }

    public String getHospitalDepartmentName(){
        return department.getNameDep();
    }

    public String getArrivalTime(){
        return arrival.toString();
    }
}

