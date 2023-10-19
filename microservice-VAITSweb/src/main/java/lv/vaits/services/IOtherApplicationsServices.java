package lv.vaits.services;

import lv.vaits.dto.OtherApplicationsDTO;

import java.util.ArrayList;

public interface IOtherApplicationsServices {
    ArrayList<OtherApplicationsDTO> retrieveAllOtherApplications();

    OtherApplicationsDTO insertOtherApplications(OtherApplicationsDTO otherAppDTO) throws Exception;
}