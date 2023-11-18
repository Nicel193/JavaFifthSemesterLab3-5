package ntukhpi.kn221a.kro.webappsyrlab2.repository;

import ntukhpi.kn221a.kro.webappsyrlab2.entity.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, Long> {
    HospitalDepartment findHospitalDepartmentByNameDep(String nameDep);
}

