package lv.vaits.services.impl;

import lv.vaits.dto.OtherApplicationsDTO;
import lv.vaits.models.OtherApplications;
import lv.vaits.models.Thesis;
import lv.vaits.repos.IOtherApplicationsRepo;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.services.IOtherApplicationsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OtherApplicationsImplementation implements IOtherApplicationsServices {


    @Autowired
    private IOtherApplicationsRepo otherApplicationRepo;

    @Autowired
    private IThesisRepo thesisRepo;

    @Override
    public ArrayList<OtherApplicationsDTO> retrieveAllOtherApplications() {
        ArrayList<OtherApplicationsDTO> result = new ArrayList<>();
        ArrayList<OtherApplications> allOtherApplications = new ArrayList<>();

        for (OtherApplications temp : allOtherApplications){
            result.add(new OtherApplicationsDTO(temp.getApplicationType(), temp.getActivity(), temp.getThesis().getTitleLv()));
        }
        return result;
    }

    @Override
    public OtherApplicationsDTO insertOtherApplications(OtherApplicationsDTO otherAppDTO) throws Exception {
        Thesis thesis = thesisRepo.findByTitleLv(otherAppDTO.getThesisTitle());

        if (thesis != null){
            OtherApplications otherApplication = new OtherApplications(otherAppDTO.getApplicationType(), otherAppDTO.getActivity(), thesis);
            otherApplicationRepo.save(otherApplication);
            return new OtherApplicationsDTO(otherAppDTO.getApplicationType(), otherAppDTO.getActivity(), otherAppDTO.getThesisTitle());
        } else {
            throw new Exception("Thesis does not exist");
        }

    }
}
