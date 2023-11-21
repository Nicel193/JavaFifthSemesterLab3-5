package ntukhpi.semit.kro.webjavaspringfirsttry2023a;

import ntukhpi.kn221a.kro.webappsyrlab3.WebJavaSpringApplication;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.repository.HospitalDepartmentRepository;
import ntukhpi.kn221a.kro.webappsyrlab3.service.HospitalDepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = WebJavaSpringApplication.class)
class WebJavaSpringFirstTry2023AApplicationTests {
    @Autowired
    private HospitalDepartmentRepository hospitalDepartmentRepository;

    @Autowired
    private HospitalDepartmentService hospitalDepartmentService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAll() {
        List<HospitalDepartment> list = hospitalDepartmentRepository
.findAll();
        list.stream().forEach(System.out::println);
        System.out.println("===================");
        list = hospitalDepartmentService
.getAllHospitalDepartments();
        list.stream().forEach(System.out::println);
    }

    @Test
    void testFindByID() {
        assertEquals(null, hospitalDepartmentService
.getHospitalDepartmentById(100L));
    }

    @Test
    void testFindByName() {
        assertEquals(null, hospitalDepartmentService
.getHospitalDepartmentByName("None"));
    }

    void testInsertHospitalDepartmentModified() {
        System.out.println("\nДодавання нового Департамента ... ");
        try {
            HospitalDepartment hdToIns = DepartmentFactory.createDepartments().get(1);
            System.out.println("Департамент для додавання: " + hdToIns);
            HospitalDepartment hdToInsInDB = hospitalDepartmentService
.getHospitalDepartmentByName(hdToIns.getNameDep());
            if (hdToInsInDB == null) {
                System.out.println("Департамент із таким ключем відсутній у бд");
                hospitalDepartmentService
.saveHospitalDepartment(hdToIns);
                hdToInsInDB = hospitalDepartmentService
.getHospitalDepartmentByName(hdToIns.getNameDep());
                System.out.println("Департамент успішно доданий: " + hdToInsInDB);
            }
            assertNotEquals(null, hospitalDepartmentService
.getHospitalDepartmentByName(hdToIns.getNameDep()));
        } catch (Exception ex) {
            System.out.println("Помилка роботи із БД === Додавання не виконане");
        }
    }

    void testUpdateHospitalDepartment() {
        System.out.println("\nРедагування Департамента ... ");
        try {
            HospitalDepartment hdForUpdateInDB = hospitalDepartmentService
.getHospitalDepartmentByName(DepartmentFactory.createDepartments().get(1).getNameDep());
            HospitalDepartment hdToUpdateInDB = null;
            if (hdForUpdateInDB != null) {
                System.out.println("Департамент, що оновлюється: id=" + hdForUpdateInDB.getId() + ": " + hdForUpdateInDB.getNameDep());
                HospitalDepartment hdToUpdate = new HospitalDepartment(DepartmentFactory.createDepartments().get(2).getNameDep());
                System.out.println("Департамент для оновлення: " + hdToUpdate);
                hdToUpdateInDB = hospitalDepartmentService
.getHospitalDepartmentByName(DepartmentFactory.createDepartments().get(2).getNameDep());
                if (hdToUpdateInDB == null || (hdToUpdateInDB != null && hdToUpdateInDB.getId() == hdForUpdateInDB.getId())) {
                    System.out.println("Департамент із таким ключем відсутній у бд");
                    hospitalDepartmentService
.updateHospitalDepartment(hdForUpdateInDB.getId(), hdToUpdate);
                    hdToUpdateInDB = hospitalDepartmentService
.getHospitalDepartmentByName(DepartmentFactory.createDepartments().get(2).getNameDep());
                    System.out.println("Департамент успішно оновлений: " + hdToUpdateInDB);
                } else {
                    System.out.println("Оновлення відхілене === Дані по Департаменту із таким ключем є у бд");
                }
            } else {
                System.out.println("Оновлення відхілене === Департамента немає у БД");
            }
        } catch (Exception ex) {
            System.out.println("Помилка роботи із БД === Оновлення не виконане");
        }
        assertEquals(null, hospitalDepartmentService
.getHospitalDepartmentByName(DepartmentFactory.createDepartments().get(2).getNameDep()));

    }

    void testDeleteHospitalDepartment() {
        System.out.println("\nВилучення Департамента ... ");
        try {
            HospitalDepartment hdForDeleteInDB = hospitalDepartmentService
.getHospitalDepartmentByName(DepartmentFactory.createDepartments().get(2).getNameDep());
            if (hdForDeleteInDB != null) {
                System.out.println("Департамент, що вилучається: id=" +
                        hdForDeleteInDB.getId() + ": " + hdForDeleteInDB.getNameDep());
                hospitalDepartmentService
.deleteHospitalDepartmentById(hdForDeleteInDB.getId());
                assertEquals(null, hospitalDepartmentService
.getHospitalDepartmentByName(DepartmentFactory.createDepartments().get(2).getNameDep()));
                System.out.println("Департамент успішно вилучений!");
            } else {
                System.out.println("Вилучення відхілене === Департамента немає у БД");
            }
        } catch (Exception ex) {
            System.out.println("Помилка роботи із БД === Вилучення не виконане");
        }
    }

    @Test
    void insertAndUpdate() {
        testInsertHospitalDepartmentModified();
        testUpdateHospitalDepartment();
    }
    
    @Test
    void insertAndUpdateAndDelete() {
        testInsertHospitalDepartmentModified();
        testUpdateHospitalDepartment();
//        testDeleteHospitalDepartment();
    }
}
