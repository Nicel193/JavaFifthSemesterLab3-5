package ntukhpi.kn221a.kro.webappsyrlab3.service;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.repository.HospitalDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalDepartmentService implements IHospitalDepartmentService{
    private final HospitalDepartmentRepository hospitalDepartmentRepository;

    public HospitalDepartmentService(HospitalDepartmentRepository hospitalDepartmentRepository) {
        this.hospitalDepartmentRepository = hospitalDepartmentRepository;
    }

    @Override
    public List<HospitalDepartment> getAllHospitalDepartments() {
        return hospitalDepartmentRepository.findAll();
    }

    @Override
    public HospitalDepartment getHospitalDepartmentByName(String name) {
        return hospitalDepartmentRepository.findHospitalDepartmentByNameDep(name);
    }

    @Override
    public HospitalDepartment getHospitalDepartmentById(Long id) {
        return hospitalDepartmentRepository.findById(id).orElse(null);
    }

    @Override
    public HospitalDepartment saveHospitalDepartment(HospitalDepartment hospitalDepartment) {
        return hospitalDepartmentRepository.save(hospitalDepartment);
    }

    @Override
    public HospitalDepartment updateHospitalDepartment(Long idEd, HospitalDepartment hospitalDepartment) {
        hospitalDepartment.setId(idEd);
        return hospitalDepartmentRepository.save(hospitalDepartment);
    }

    @Override
    public void deleteHospitalDepartmentById(Long idDel) {
        hospitalDepartmentRepository.deleteById(idDel);
    }
}
