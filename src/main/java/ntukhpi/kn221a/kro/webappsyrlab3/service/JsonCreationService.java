package ntukhpi.kn221a.kro.webappsyrlab3.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;

public class JsonCreationService {
    private final IHospitalDepartmentService hospitalDepartmentService;

    public JsonCreationService(IHospitalDepartmentService hospitalDepartmentService) {
        this.hospitalDepartmentService = hospitalDepartmentService;
    }

    public File createJsonFileFromDatabase(String fileName) {
        ObjectMapper objectMapper = JsonMapper.builder()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .build();

        fileName += ".json";
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            objectMapper.writeValue(fileWriter, hospitalDepartmentService.getAllHospitalDepartments());
            fileWriter.close();
            System.out.println("JSON file created successfully: " + fileName);

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
