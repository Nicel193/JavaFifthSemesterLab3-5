package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IHospitalDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HospitalDepartmentController {
    private final IHospitalDepartmentService hospitalDepartmentService;
    private HospitalDepartment newHospitalDepartment;

    public HospitalDepartmentController(IHospitalDepartmentService hospitalDepartmentService) {
        this.hospitalDepartmentService = hospitalDepartmentService;
    }

    @GetMapping("/")
    public String showStartPage() {
        return "startpage";
    }

    @GetMapping("/hospitalDepartments")
    public String showHospitalDepartmentsPage(Model model) {
        model.addAttribute("hospitalDepartments", hospitalDepartmentService.getAllHospitalDepartments());
        return "/hospitalDepartments/hospitalDepartments";
    }

    @PostMapping("/hospitalDepartments")
    public String saveHospitalDepartment(@ModelAttribute("hospitalDepartment") HospitalDepartment hospitalDepartment) {
        return "redirect:/hospitalDepartments";
    }

    @GetMapping("/hospitalDepartments/new")
    public String createHospitalDepartmentForm(Model model) {
        HospitalDepartment newHospitalDepartment = new HospitalDepartment("Cardiology", "CAR", "B1", 2, 20);
        model.addAttribute("hospitalDepartment", newHospitalDepartment);
        model.addAttribute("titleHospitalDepartment", "Add HospitalDepartment (WEB LAB#3)");
        model.addAttribute("errorString", null);
        return "/hospitalDepartments/hospitalDepartment";
    }

    @GetMapping("/hospitalDepartments/edit/{idEdit}")
    public String editHospitalDepartmentForm(@PathVariable Long idEdit, Model model) {
        HospitalDepartment hpForUpdateInDB = hospitalDepartmentService.getHospitalDepartmentById(idEdit);
        model.addAttribute("hospitalDepartment", hpForUpdateInDB);
        System.out.println(hpForUpdateInDB);
        model.addAttribute("titleHospitalDepartment", "Edit HospitalDepartment (WEB LAB#3)");
        model.addAttribute("errorString", null);
        return "/hospitalDepartments/hospitalDepartment";
    }

    @PostMapping("/hospitalDepartments/save/{id}")
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

    @GetMapping("/hospitalDepartments/del/{idHospitalDepartmentForDelete}")
    public String deleteHospitalDepartment(@PathVariable Long idHospitalDepartmentForDelete, Model model) {
        HospitalDepartment delHospitalDepartmentInDB = hospitalDepartmentService.getHospitalDepartmentById(idHospitalDepartmentForDelete);
        if (delHospitalDepartmentInDB != null) {
            hospitalDepartmentService.deleteHospitalDepartmentById(idHospitalDepartmentForDelete);
            return "redirect:/hospitalDepartments";
        } else {
            String messageDeleteError = "Такого співробітника немає у БД!\n" +
                    "Object: EMPLOYEE, id=" + idHospitalDepartmentForDelete;
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "redirect:/hospitalDepartments");
            return "delete_error";
        }
    }
}
