package ntukhpi.kn221a.kro.webappsyrlab3.service;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.PatientDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.repository.PatientDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDepartmentService implements IPatientDepartmentService {
    private final PatientDepartmentRepository PatientDepartmentRepository;

    public PatientDepartmentService(PatientDepartmentRepository PatientDepartmentRepository) {
        this.PatientDepartmentRepository = PatientDepartmentRepository;
    }

    @Override
    public List<PatientDepartment> getAllDepartmentPatients(Long depId) {
        return PatientDepartmentRepository.findByDepartmentId(depId);
    }

    @Override
    public PatientDepartment getPatientDepartmentByName(String name) {
        return PatientDepartmentRepository.findPatientDepartmentByName(name);
    }

    @Override
    public PatientDepartment getPatientDepartmentById(Long id) {
        return PatientDepartmentRepository.findById(id).orElse(null);
    }

    @Override
    public PatientDepartment savePatientDepartment(PatientDepartment PatientDepartment) {
        return PatientDepartmentRepository.save(PatientDepartment);
    }

    @Override
    public PatientDepartment updatePatientDepartment(Long idEd, PatientDepartment PatientDepartment) {
        PatientDepartment.setId(idEd);
        return PatientDepartmentRepository.save(PatientDepartment);
    }

    @Override
    public void deletePatientDepartmentById(Long idDel) {
        PatientDepartmentRepository.deleteById(idDel);
    }
}
