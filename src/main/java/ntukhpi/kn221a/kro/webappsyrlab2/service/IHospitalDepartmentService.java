package ntukhpi.kn221a.kro.webappsyrlab2.service;
import ntukhpi.kn221a.kro.webappsyrlab2.entity.HospitalDepartment;

import java.util.List;

public interface IHospitalDepartmentService {
    List<HospitalDepartment> getAllHospitalDepartments();
    HospitalDepartment getHospitalDepartmentByName(String name);
    HospitalDepartment getHospitalDepartmentById(Long id);
    HospitalDepartment saveHospitalDepartment(HospitalDepartment hospitalDepartment);
    HospitalDepartment updateHospitalDepartment(Long id, HospitalDepartment hospitalDepartment);
    void deleteHospitalDepartmentById(Long id);
}
