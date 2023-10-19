package lv.vaits.controllers;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.vaits.models.Thesis;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.services.IThesisServices;
import lv.vaits.services.users.IAcademicStaffServices;
import lv.vaits.services.users.IStudentServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

@Controller
public class ThesisController {

    @Autowired
    private IStudentServices studentServices;

    @Autowired
    private IAcademicStaffServices academicStaffServices;

    @Autowired
    private IThesisServices thesisServices;

    @Autowired
    private IThesisRepo thesisRepo;

    @GetMapping("/thesis/addNew")
    public String insertThesisGetFunc(Thesis thesis, Model model) {
        model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
        model.addAttribute("allStudents", studentServices.retrieveAllStudents());
        model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaffMembers());
        return "thesis-add-page";
    }

    @PostMapping("/thesis/addNew")
    public String insertThesisPostFunc(@Valid Thesis thesis, BindingResult result) {
        if (!result.hasErrors()) {
            thesisServices.createNewThesis(thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAim(), thesis.getTasks(),
                    thesis.getStudent(), thesis.getSupervisor());
            return "redirect:/thesis/showAll";
        } else {
            return "thesis-add-page";
        }
    }

    @GetMapping("/thesis/showAll")
    public String allThesisGetFunc(Model model) {
        model.addAttribute("activeThesisList", thesisServices.retrieveActiveTheses());
        return "thesis-all-page";
    }

    @GetMapping("/thesis/showAll/{id}")
    public String oneThesisByIdGetFunc(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
            return "thesis-one-page";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping("/thesis/update/{id}")
    public String updateThesisByIdGetFunc(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
            model.addAttribute("allStudents", studentServices.retrieveAllStudents());
            model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaffMembers());
            return "thesis-update-page";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @PostMapping("/thesis/update/{id}")
    public String updateThesisByIdPostFunc(@PathVariable("id") Long id, @Valid Thesis thesis, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                Thesis updatedThesis = thesisServices.updateThesisById(id, thesis.getTitleLv(), thesis.getTitleEn(),
                        thesis.getAim(), thesis.getTasks(), thesis.getStudent(), thesis.getSupervisor());
                return "redirect:/thesis/showAll/" + updatedThesis.getIdt();
            } catch (Exception e) {
                return "redirect:/thesis/error";
            }
        } else {
            return "thesis-update-page";
        }
    }

    @GetMapping("/thesis/remove/{id}")
    public String deleteThesisById(@PathVariable("id") Long id, Model model) {
        try {
            Thesis updateThesis = thesisServices.retrieveThesisById(id);
            updateThesis.setDeleted(true);
            thesisRepo.save(updateThesis);
            model.addAttribute("allThesis", thesisServices.retrieveActiveTheses());
            return "redirect:/thesis/showAll";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping("/thesis/changeSupervisor/{idThesis}/{idSupervisor}")
    public String changeSupervisorByThesisAndSupervisorIdGetFunc(@PathVariable("idThesis") Long id,
                                                                 @PathVariable("idSupervisor") Long idSupervisor, Model model) {
        try {
            model.addAttribute("allThesis", thesisServices.changeSupervisorByThesisAndSupervisorId(id, idSupervisor));
            return "redirect:/thesis/showAll";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping("/thesis/addReviewerByThesisId/{idThesis}/{idReviewer}")
    public String addReviewerByThesisId(@PathVariable("idThesis") Long id, @PathVariable("idReviewer") Long idReviewer,
                                        Model model) {
        try {
            model.addAttribute("allThesis", thesisServices.addReviewerByThesisId(id, idReviewer));
            return "redirect:/thesis/showAll";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping("/thesis/updateStatus/{id}")
    public String updateThesisStatusGetFunc(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
            return "thesis-update-status-page";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @PostMapping("/thesis/updateStatus/{id}")
    public String updateThesisStatusPostFunc(@PathVariable("id") Long id, @Valid Thesis thesis, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                Thesis updatedThesis = thesisServices.updateThesisStatus(id, thesis.getAccStatus());
                return "redirect:/thesis/showAll/" + updatedThesis.getIdt();
            } catch (Exception e) {
                return "redirect:/thesis/error";
            }
        } else {
            return "error-page";
        }
    }

    @GetMapping("/thesis/export")
    public ResponseEntity<InputStreamResource> exportThesesToExcel() throws IOException {
        //tiek iegūti dati no DB par tēzēm
        Workbook workbook = thesisServices.exportThesisToExcel();

        // Tiek izveidots pagaidu fails, kurā saglabāsies Excel dati
        File tempFile = File.createTempFile("theses", ".xlsx");

        //Tiek izveidots fileoutputstream, lai excel datus saglabātu pagaidu failā
        FileOutputStream fos = new FileOutputStream(tempFile);

        //excel dati tiek saglabāti pagaidu failā
        workbook.write(fos);
        fos.close();

        //Tiek izveidotas HTTP galvenes
        HttpHeaders headers = new HttpHeaders();

        //Pievieno "Content-Disposition" galveni, lai norādītu, ka saturs būs kā pielikums un jālejupielādē ar nosaukumu "theses.xlsx".
        headers.add("Content-Disposition", "attachment; filename=theses.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(new FileInputStream(tempFile)));
    }

    @GetMapping("/thesis/export/word")
    public ResponseEntity<InputStreamResource> exportThesesToWord() throws IOException {
        XWPFDocument document = thesisServices.exportThesisToWord();

        File tempFile = File.createTempFile("theses", ".docx");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            document.write(fos);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=theses.docx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(new InputStreamResource(new FileInputStream(tempFile)));
    }

    @PostMapping("/thesis/removeReviewerByThesisId/{idThesis}/{idReviewer}")
    public String removeReviewerByThesisId(@PathVariable("idThesis") Long idThesis,
                                           @PathVariable("idReviewer") Long idReviewer,
                                           Model model) {
        try {
            thesisServices.deleteThesisReviewerById(idThesis, idReviewer);
            model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
            return "redirect:/thesis/showAll";
        } catch (Exception e) {
            return "error-page";
        }
    }

}
