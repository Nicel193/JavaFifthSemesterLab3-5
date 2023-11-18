//import ntukhpi.kn221a.kro.webappsyrlab2.entity.HospitalDepartment;
//import ntukhpi.kn221a.kro.webappsyrlab2.hibernate.EntityDAO;
//import ntukhpi.kn221a.kro.webappsyrlab2.hibernate.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.*;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class TestDB {
//    private static SessionFactory sf = null;
//    private static Session session;
//    private Transaction transaction;
//    static EntityDAO<HospitalDepartment> daoHospitalDepartment = new EntityDAO<HospitalDepartment>(HospitalDepartment.class);
//
//    @BeforeAll
//    static void beforeAll() {
//        sf = HibernateUtil.getSessionFactory();
//        session = sf.openSession();
//        System.out.println("\n\nHIBERNATE підключивася до БД!!!");
//
//        for (HospitalDepartment dep : DepartmentFactory.createDepartments()) {
//            daoHospitalDepartment.delete(dep);
//        }
//    }
//
//    @Test
//    void testCreate() {
//        System.out.println("Має бути створена БД");
//    }
//
//    @Order(4)
//    @Test
//    void testUpdateHospitalDepartment() {
//        System.out.println("\nHospitalDepartment Update ");
//        Long idEmplForUpdate;
//        idEmplForUpdate = 3L;
//        HospitalDepartment hdMustUpdate = DepartmentFactory.createDepartments().get(0);
//        HospitalDepartment hdMustUpdateInDB = daoHospitalDepartment.findByKey(hdMustUpdate);
//
//        if (hdMustUpdateInDB != null) {
//            idEmplForUpdate = hdMustUpdateInDB.getId();
//            HospitalDepartment hdForUpdate = DepartmentFactory.createDepartments().get(1);
//
//            HospitalDepartment hdUpdateInDB = daoHospitalDepartment.findByKey(hdForUpdate);
//            if (hdUpdateInDB == null) {
//                if (daoHospitalDepartment.update(idEmplForUpdate, hdForUpdate)) {
//                    System.out.println("\nоновлене!!!");
//                    System.out.println("\nHospitalDepartment after update");
//                    List<HospitalDepartment> listEmploee = daoHospitalDepartment.getAllList();
//                    listEmploee.stream().forEach(System.out::println);
//                } else {
//                    System.err.println("\n NO додане!!!");
//                }
//            } else {
//                System.err.println("\n Такий об\"єкт є у БД!!!");
//            }
//        } else {
//            System.err.println("\n Об\"єкт для оновлення відсутній у БД!!!");
//        }
//    }
//
//    @Test
//    @Order(5)
//    void testDeleteHospitalDepartment() {
//        System.out.println("\nHospitalDepartment Delete from DB ");
//        //Треба id запису у таблиці БД
//        Long idEmplForDelete;
//        // 1. Можна призначити
//        idEmplForDelete = 3L;
//        // 2. Більш правильний - знайти за ключовими полями, тому що користувач не може знати id
//        // Ввели - Шевченка, її змінили на Реброва, тому і вилучати треба Реброва
//        HospitalDepartment hdMustDelete = DepartmentFactory.createDepartments().get(1);
//        //При повторному запуску оцього:
//        HospitalDepartment hdMustDeleteInDB = daoHospitalDepartment.findByKey(hdMustDelete);
//
//        if (hdMustDeleteInDB != null) {
//            if (daoHospitalDepartment.delete(hdMustDeleteInDB)) {
//                System.out.println("\nвилучене!!!");
//                System.out.println("\nHospitalDepartment after delete:");
//                List<HospitalDepartment> listEmploee = daoHospitalDepartment.getAllList();
//                listEmploee.stream().forEach(System.out::println);
//            } else {
//                System.err.println("\n NO вилучене!!!");
//            }
//        } else {
//            System.err.println("\n Об\"єкт для вилучення відсутній у БД!!!");
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testGetAllHospitalDepartment() {
//        System.out.println("HospitalDepartment");
//        List<HospitalDepartment> listEmploee = daoHospitalDepartment.getAllList();
//        listEmploee.stream().forEach(System.out::println);
//    }
//
//    @Test
//    @Order(2)
//    void testFindByIDHospitalDepartment() {
//        System.out.println("\nHospitalDepartment byID");
//        HospitalDepartment hdInDB1 = daoHospitalDepartment.findById(1L);
//        System.out.println(hdInDB1);
//        assertEquals(DepartmentFactory.createDepartments().get(0).getNameDep(), hdInDB1.getNameDep());
//        hdInDB1 = daoHospitalDepartment.findById(1L);
//        System.out.println(hdInDB1);
//        assertNotEquals("False", hdInDB1.getNameDep());
//    }
//
//    @Test
//    @Order(3)
//    void testFindByKeyFieldsHospitalDepartment() {
//        System.out.println("\nHospitalDepartment by Key (Name)");
//        HospitalDepartment hdForFind = DepartmentFactory.createDepartments().get(0);
//        HospitalDepartment hdInDB1 = daoHospitalDepartment.findByKey(hdForFind);
//        System.out.println(hdInDB1);
//        assertEquals(1L, hdInDB1.getId());
//        hdInDB1 = daoHospitalDepartment.findById(1L);
//        System.out.println(hdInDB1);
//        assertNotEquals(2L, hdInDB1.getId());
//    }
//
//    @Test
//    @Order(1)
//    void testSaveHospitalDepartment() {
//        System.out.println("\nHospitalDepartment Save to DB");
//
//        HospitalDepartment hdForSave = DepartmentFactory.createDepartments().get(0);
//        HospitalDepartment hdInDB = daoHospitalDepartment.findByKey(hdForSave);
//
//        if (hdInDB == null) {
//            if (daoHospitalDepartment.insert(hdForSave)) {
//                System.out.println("\nдодане!!!");
//                System.out.println("\nHospitalDepartment after save");
//                List<HospitalDepartment> listEmploee = daoHospitalDepartment.getAllList();
//                listEmploee.stream().forEach(System.out::println);
//            } else {
//                System.err.println("\n NO додане!!!");
//            }
//        } else {
//            System.err.println("\n Такий об\"єкт є у БД!!!");
//        }
//    }
//
//    @AfterAll
//    static void afterAll() {
//        for (HospitalDepartment dep : DepartmentFactory.createDepartments()) {
//            daoHospitalDepartment.insert(dep);
//        }
//
//        System.out.println("\n\nHIBERNATE завершив роботу!!!");
//        HibernateUtil.shutdown();
//    }
//
//}
