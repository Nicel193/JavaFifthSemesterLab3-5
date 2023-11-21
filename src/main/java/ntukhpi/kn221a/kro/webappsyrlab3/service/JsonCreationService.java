package ntukhpi.kn221a.kro.webappsyrlab3.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.HospitalDepartment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonCreationService implements IFileCreator {
    private final IHospitalDepartmentService hospitalDepartmentService;
private final IPatientDepartmentService patientDepartmentService;

    public JsonCreationService(IHospitalDepartmentService hospitalDepartmentService,
                               IPatientDepartmentService patientDepartmentService) {
        this.hospitalDepartmentService = hospitalDepartmentService;
        this.patientDepartmentService = patientDepartmentService;
    }

    public File createFileFromDatabase(String fileName) {
        ObjectMapper objectMapper = JsonMapper.builder()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .build();
        objectMapper.registerModule(new JavaTimeModule());

        fileName += ".json";
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);

            List<Object> allData = new ArrayList<>();

            List<HospitalDepartment> hospitalDepartments = hospitalDepartmentService.getAllHospitalDepartments();
            for (HospitalDepartment hd : hospitalDepartments) {
                allData.add(hd);
                allData.addAll(patientDepartmentService.getAllDepartmentPatients(hd.getId()));
            }

            objectMapper.writeValue(fileWriter, allData);

            fileWriter.close();
            System.out.println("JSON file created successfully: " + fileName);

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
