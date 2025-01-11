package mcdodik.animals.domain.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NameController {

    @Value("${spring.application.name}")
    private String databaseUrl;


    @GetMapping(value = {"/", "/test/red"})
    public Map<String, String> getDatabaseInfo() {
        Map<String, String> dbInfo = new HashMap<>();
        dbInfo.put("name", databaseUrl);
        return dbInfo;
    }
}

