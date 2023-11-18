package ntukhpi.kn221a.kro.webappsyrlab3.repository;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, Long> {
    HospitalDepartment findHospitalDepartmentByNameDep(String nameDep);
}

