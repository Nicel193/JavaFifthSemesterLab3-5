package ntukhpi.kn221a.kro.webappsyrlab3.repository;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.PatientDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientDepartmentRepository extends JpaRepository<PatientDepartment, Long> {
    List<PatientDepartment> findByDepartmentId(Long departmentId);
    List<PatientDepartment> findBySurnameAndNameAndPnameAndDepartment(String surname, String name, String pname, HospitalDepartment department);
}
