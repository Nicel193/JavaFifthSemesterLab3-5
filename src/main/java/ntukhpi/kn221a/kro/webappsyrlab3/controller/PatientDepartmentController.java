package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.PatientDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IHospitalDepartmentService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IPatientDepartmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@EnableMethodSecurity
@Controller
@RequestMapping("patientDepartments")
public class PatientDepartmentController {
    private final IPatientDepartmentService patientDepartmentService;
    private final IHospitalDepartmentService hospitalDepartmentService;
    private HospitalDepartment hospitalDepartment;
    

    public PatientDepartmentController(IPatientDepartmentService patientDepartmentService, IHospitalDepartmentService hospitalDepartmentService) {
        this.patientDepartmentService = patientDepartmentService;
        this.hospitalDepartmentService = hospitalDepartmentService;
    }

    @GetMapping("")
    public String showPatientDepartmentsPage(@RequestParam("id") Long hospitalDepId, Model model) {
        this.hospitalDepartment = hospitalDepartmentService.getHospitalDepartmentById(hospitalDepId);
        model.addAttribute("patientDepartments", patientDepartmentService.getAllPatientDepartments());
        return "/patientDepartments/patientDepartments";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public String savePatientDepartment(@ModelAttribute("patientDepartment") PatientDepartment patientDepartment) {
        return PatientDepartmentsRedirect();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String createPatientDepartmentForm(Model model) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        PatientDepartment newPatientDepartment = new PatientDepartment(
                "Smith",
                "John",
                "Doe",
                35,
                hospitalDepartment,
                currentDateTime,
                "A101"
        );

        model.addAttribute("patientDepartment", newPatientDepartment);
        model.addAttribute("titlePatientDepartment", "Add PatientDepartment (WEB LAB#3)");
        model.addAttribute("errorString", null);
        return "/patientDepartments/patientDepartment";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{idEdit}")
    public String editPatientDepartmentForm(@PathVariable Long idEdit, Model model) {
        PatientDepartment hpForUpdateInDB = patientDepartmentService.getPatientDepartmentById(idEdit);
        model.addAttribute("patientDepartment", hpForUpdateInDB);
        System.out.println(hpForUpdateInDB);
        model.addAttribute("titlePatientDepartment", "Edit PatientDepartment (WEB LAB#3)");
        model.addAttribute("errorString", null);
        return "/patientDepartments/patientDepartment";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{id}")
    public String saveOrUpdatePatientDepartment(@PathVariable Long id, @ModelAttribute("patientDepartment") PatientDepartment patientDepartmentToSave, Model model) {
        PatientDepartment hpToSaveInDB = patientDepartmentService.getPatientDepartmentByName(patientDepartmentToSave.getName());
        patientDepartmentToSave.setDepartment(hospitalDepartment);

        if (id.equals(0L)) {
            if (hpToSaveInDB == null) {
                patientDepartmentService.savePatientDepartment(patientDepartmentToSave);
                return PatientDepartmentsRedirect();
            } else {
                model.addAttribute("patientDepartment", patientDepartmentToSave);
                model.addAttribute("titlePatientDepartment", "Add PatientDepartment (LAB WEB#3)");
                model.addAttribute("errorString", "PatientDepartment with such key was finded in DB!");
                return "/patientDepartments/patientDepartment";
            }
        } else {
            if ((hpToSaveInDB != null && hpToSaveInDB.getId() == patientDepartmentToSave.getId()) || hpToSaveInDB == null) {
                PatientDepartment existingPatientDepartment = patientDepartmentService.getPatientDepartmentById(id);
                if (existingPatientDepartment == null) {
                    model.addAttribute("patientDepartment", patientDepartmentToSave);
                    model.addAttribute("titlePatientDepartment", "Edit PatientDepartment (WEB LAB#3)");
                    model.addAttribute("errorString", "PatientDepartment for update was not found in DB!");
                    return "/patientDepartments/patientDepartment";
                } else {
                    patientDepartmentService.updatePatientDepartment(existingPatientDepartment.getId(), patientDepartmentToSave);
                    return PatientDepartmentsRedirect();
                }
            } else {
                model.addAttribute("patientDepartment", patientDepartmentToSave);
                model.addAttribute("titlePatientDepartment", "Edit PatientDepartment (WEB LAB#3)");
                model.addAttribute("errorString", "PatientDepartment with such key was found in DB!");
                return "/patientDepartments/patientDepartment";
            }
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/del/{idPatientDepartmentForDelete}")
    public String deletePatientDepartment(@PathVariable Long idPatientDepartmentForDelete, Model model) {
        PatientDepartment delPatientDepartmentInDB = patientDepartmentService.getPatientDepartmentById(idPatientDepartmentForDelete);
        if (delPatientDepartmentInDB != null) {
            patientDepartmentService.deletePatientDepartmentById(idPatientDepartmentForDelete);
            return "redirect:/patientDepartments?id=" + hospitalDepartment.getId();
        } else {
            String messageDeleteError = "Такого немає у БД!\n" +
                    "Object: HOSPITAL_DEPARTMENT, id=" + idPatientDepartmentForDelete;
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", PatientDepartmentsRedirect());
            return "delete_error";
        }
    }

    private String PatientDepartmentsRedirect() {
        return "redirect:/patientDepartments?id=" + hospitalDepartment.getId();
    }
}
