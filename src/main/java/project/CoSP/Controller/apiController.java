package project.CoSP.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.CoSP.Exception.generalErrorMessageException;
import project.CoSP.Model.Code;
import project.CoSP.codeService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class apiController {
    @Autowired
    private codeService service;

    private static final Logger logger = LoggerFactory.getLogger(apiController.class);

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public String getApiCode(@PathVariable UUID id){
        logger.info("Get at /api/code");

        Code codeSnipet = service.getCode(id);
        if(codeSnipet==null){
            throw new generalErrorMessageException("");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("code", codeSnipet.getCode());
        jsonObject.addProperty("date", codeSnipet.getTime());
        jsonObject.addProperty("time", codeSnipet.getTimeRestriction());
        jsonObject.addProperty("views", codeSnipet.getViewRestriction());

        return gson.toJson(jsonObject);
    }

    @PostMapping("/api/code/new")
    public String postCode(@RequestBody Code newCode){
        logger.info("Post at /api/code/new");

        newCode.setTime(LocalDateTime.now());
        newCode.setLastAccessedTime(null);
        service.firstSave(newCode);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", String.valueOf(newCode.getId()));
        return gson.toJson(jsonObject);
    }

    @GetMapping("/api/code/latest")
    public String getLatestCode(){
        logger.info("Get at /api/code/latest");

        List<Code> get10Code = service.getLatestCode();
        List<JsonObject> get10CodeAsJson = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for(Code eachCode : get10Code){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", eachCode.getCode());
            jsonObject.addProperty("date", eachCode.getTime());
            jsonObject.addProperty("time", eachCode.getTimeRestriction());
            jsonObject.addProperty("views", eachCode.getViewRestriction());
            get10CodeAsJson.add(jsonObject);
        }

        return gson.toJson(get10CodeAsJson);

    }
}

