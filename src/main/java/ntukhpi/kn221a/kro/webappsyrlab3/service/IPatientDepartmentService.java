package ntukhpi.kn221a.kro.webappsyrlab3.service;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.PatientDepartment;

import java.util.List;

public interface IPatientDepartmentService {
    List<PatientDepartment> getAllPatientDepartments();
    PatientDepartment getPatientDepartmentByName(String name);
    PatientDepartment getPatientDepartmentById(Long id);
    PatientDepartment savePatientDepartment(PatientDepartment PatientDepartment);
    PatientDepartment updatePatientDepartment(Long id, PatientDepartment PatientDepartment);
    void deletePatientDepartmentById(Long id);
}
