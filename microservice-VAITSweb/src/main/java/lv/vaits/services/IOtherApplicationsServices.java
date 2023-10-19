package lv.vaits.services;

import lv.vaits.dto.OtherApplicationsDTO;
import lv.vaits.utils.MyException;

import java.util.ArrayList;

public interface IOtherApplicationsServices {
    ArrayList<OtherApplicationsDTO> retrieveAllOtherApplications();

    OtherApplicationsDTO insertOtherApplications(OtherApplicationsDTO otherAppDTO) throws MyException;
}