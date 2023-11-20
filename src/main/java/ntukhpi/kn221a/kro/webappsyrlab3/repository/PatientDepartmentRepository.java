package ntukhpi.kn221a.kro.webappsyrlab3.repository;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.PatientDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDepartmentRepository extends JpaRepository<PatientDepartment, Long> {
    PatientDepartment findPatientDepartmentByName(String nameDep);
}
