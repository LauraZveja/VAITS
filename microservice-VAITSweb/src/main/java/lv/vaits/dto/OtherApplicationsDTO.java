package lv.vaits.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lv.vaits.models.ApplicationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OtherApplicationsDTO {

    @NotNull(message = "APPLICATION REQUIRES A TYPE!")
    private ApplicationType applicationType;

    @NotNull
    @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "APPLICATION TEXT MUST START WITH CAPITAL LETTER.")
    private String activity;

    @Size(min = 3, max = 25)
    @NotNull
    @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "TEXT MUST START WITH CAPITAL LETTER.")
    private String thesisTitle;
}