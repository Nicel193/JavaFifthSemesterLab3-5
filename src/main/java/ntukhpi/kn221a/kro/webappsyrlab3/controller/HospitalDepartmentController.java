package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IHospitalDepartmentService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IPatientDepartmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@EnableMethodSecurity
@Controller
@RequestMapping("hospitalDepartments")
public class HospitalDepartmentController {
    private final IHospitalDepartmentService hospitalDepartmentService;
    private final IPatientDepartmentService patientDepartmentService;

    public HospitalDepartmentController(IPatientDepartmentService patientDepartmentService, IHospitalDepartmentService hospitalDepartmentService) {
        this.patientDepartmentService = patientDepartmentService;
        this.hospitalDepartmentService = hospitalDepartmentService;
    }

    @GetMapping("")
    public String showHospitalDepartmentsPage(Model model) {
        model.addAttribute("hospitalDepartments", hospitalDepartmentService.getAllHospitalDepartments());
        return "/hospitalDepartments/hospitalDepartments";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public String saveHospitalDepartment(@ModelAttribute("hospitalDepartment") HospitalDepartment hospitalDepartment) {
        return "redirect:/hospitalDepartments";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String createHospitalDepartmentForm(Model model) {
        HospitalDepartment newHospitalDepartment = new HospitalDepartment("Cardiology", "CAR", "B1", 2, 20);
        model.addAttribute("hospitalDepartment", newHospitalDepartment);
        model.addAttribute("titleHospitalDepartment", "Add HospitalDepartment (WEB LAB#3)");
        model.addAttribute("errorString", null);
        return "/hospitalDepartments/hospitalDepartment";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{idEdit}")
    public String editHospitalDepartmentForm(@PathVariable Long idEdit, Model model) {
        HospitalDepartment hpForUpdateInDB = hospitalDepartmentService.getHospitalDepartmentById(idEdit);
        model.addAttribute("hospitalDepartment", hpForUpdateInDB);
        System.out.println(hpForUpdateInDB);
        model.addAttribute("titleHospitalDepartment", "Edit HospitalDepartment (WEB LAB#3)");
        model.addAttribute("errorString", null);
        return "/hospitalDepartments/hospitalDepartment";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{id}")
    public String saveOrUpdateHospitalDepartment(@PathVariable Long id, @ModelAttribute("hospitalDepartment") HospitalDepartment hospitalDepartmentToSave, Model model) {
        HospitalDepartment hpToSaveInDB = hospitalDepartmentService.getHospitalDepartmentByName(hospitalDepartmentToSave.getNameDep());
        if (id.equals(0L)) {
            if (hpToSaveInDB == null) {
                hospitalDepartmentService.saveHospitalDepartment(hospitalDepartmentToSave);
                return "redirect:/hospitalDepartments";
            } else {
                model.addAttribute("hospitalDepartment", hospitalDepartmentToSave);
                model.addAttribute("titleHospitalDepartment", "Add HospitalDepartment (LAB WEB#3)");
                model.addAttribute("errorString", "HospitalDepartment with such key was finded in DB!");
                return "/hospitalDepartments/hospitalDepartment";
            }
        } else {
            if ((hpToSaveInDB != null && hpToSaveInDB.getId() == hospitalDepartmentToSave.getId()) || hpToSaveInDB == null) {
                HospitalDepartment existingHospitalDepartment = hospitalDepartmentService.getHospitalDepartmentById(id);
                if (existingHospitalDepartment == null) {
                    model.addAttribute("hospitalDepartment", hospitalDepartmentToSave);
                    model.addAttribute("titleHospitalDepartment", "Edit HospitalDepartment (WEB LAB#3)");
                    model.addAttribute("errorString", "HospitalDepartment for update was not found in DB!");
                    return "/hospitalDepartments/hospitalDepartment";
                } else {
                    hospitalDepartmentService.updateHospitalDepartment(existingHospitalDepartment.getId(), hospitalDepartmentToSave);
                    return "redirect:/hospitalDepartments";
                }
            } else {
                model.addAttribute("hospitalDepartment", hospitalDepartmentToSave);
                model.addAttribute("titleHospitalDepartment", "Edit HospitalDepartment (WEB LAB#3)");
                model.addAttribute("errorString", "HospitalDepartment with such key was found in DB!");
                return "/hospitalDepartments/hospitalDepartment";
            }
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/del/{idHospitalDepartmentForDelete}")
    public String deleteHospitalDepartment(@PathVariable Long idHospitalDepartmentForDelete, Model model) {
        HospitalDepartment delHospitalDepartmentInDB = hospitalDepartmentService.getHospitalDepartmentById(idHospitalDepartmentForDelete);

        if(!patientDepartmentService.getAllDepartmentPatients(idHospitalDepartmentForDelete).isEmpty()){
            String messageDeleteError = "All patients must be removed!";
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "/hospitalDepartments");
            return "delete_error";
        }

        if (delHospitalDepartmentInDB != null) {
            hospitalDepartmentService.deleteHospitalDepartmentById(idHospitalDepartmentForDelete);
            return "redirect:/hospitalDepartments";
        } else {
            String messageDeleteError = "There is no such thing in the database!\n" +
                    "Object: HOSPITAL_DEPARTMENT, id=" + idHospitalDepartmentForDelete;
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "/hospitalDepartments");
            return "delete_error";
        }
    }
}
