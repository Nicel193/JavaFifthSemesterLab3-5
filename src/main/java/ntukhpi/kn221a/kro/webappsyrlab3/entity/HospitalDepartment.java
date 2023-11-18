package ntukhpi.kn221a.kro.webappsyrlab3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class HospitalDepartment implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nameDep;

    @Column(nullable = false, length = 15)
    private String nameSDep;

    @Column(nullable = false, length = 5)
    private String codeBuilding;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private int boxCount;

    public HospitalDepartment(String nameDep, String nameSDep, String codeBuilding, int floor, int boxCount) {
        this.id = 0L;
        this.nameDep = nameDep;
        this.nameSDep = nameSDep;
        this.codeBuilding = codeBuilding;
        this.floor = floor;
        this.boxCount = boxCount;
    }

    public HospitalDepartment(String nameDep) {
        this.id = 0L;
        this.nameDep = nameDep;
        this.nameSDep = "ORTHO";
        this.codeBuilding = "B1";
        this.floor = 1;
        this.boxCount = 1;
    }

    @Override
    public String getKey() {
        return nameDep;
    }
}
