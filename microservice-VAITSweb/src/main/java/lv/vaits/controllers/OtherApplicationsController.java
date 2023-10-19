package lv.vaits.controllers;

import jakarta.validation.Valid;
import lv.vaits.dto.OtherApplicationsDTO;
import lv.vaits.services.IOtherApplicationsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OtherApplicationsController {

    @Autowired
    private IOtherApplicationsServices otherApplicationsServices;

    @GetMapping("/otherApps/showAll")
    public ResponseEntity<ArrayList<OtherApplicationsDTO>> getAllOtherApplications(){
        return new ResponseEntity<>(otherApplicationsServices.retrieveAllOtherApplications(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/otherApps/addNew")
    public ResponseEntity addNewOtherApplication(@RequestBody @Valid OtherApplicationsDTO otherApplicationsDTO){
        try{
            otherApplicationsServices.insertOtherApplications(otherApplicationsDTO);
            return new ResponseEntity<>(otherApplicationsServices.retrieveAllOtherApplications(), HttpStatusCode.valueOf(200));

        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }
}
