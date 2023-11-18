import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import java.util.List;
import java.util.ArrayList;

public class DepartmentFactory {
    public static List<HospitalDepartment> createDepartments() {
        List<HospitalDepartment> departments = new ArrayList<>();

        departments.add(new HospitalDepartment("Cardiology", "CAR", "B1", 2, 20));
        departments.add(new HospitalDepartment("Orthopedics", "ORTHO", "B2", 1, 15));
        departments.add(new HospitalDepartment("Pediatrics", "PED", "B2", 3, 10));
        departments.add(new HospitalDepartment("Surgery", "SURG", "B4", 2, 25));
        departments.add(new HospitalDepartment("Obstetrics & Gynecology", "OBGYN", "B1", 2, 15));

        return departments;
    }
}


