package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.service.EmailSenderService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IHospitalDepartmentService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.JsonCreationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@RequestMapping("email")
public class EmailSenderController {
    private final IHospitalDepartmentService hospitalDepartmentService;

    public EmailSenderController(IHospitalDepartmentService hospitalDepartmentService) {
        this.hospitalDepartmentService = hospitalDepartmentService;
    }

    @PostMapping("")
    public ResponseEntity<String> saveHospitalDepartment() {
        File file = new JsonCreationService(hospitalDepartmentService)
                .createJsonFileFromDatabase("hospitalDepartments");

        EmailSenderService.sendEmailWithAttachment("sender@gmail.com",
                "nicelgoog@gmail.com",
                "Test Email",
                file,
                true);

        return ResponseEntity.ok("Email sent successfully");
    }
}
