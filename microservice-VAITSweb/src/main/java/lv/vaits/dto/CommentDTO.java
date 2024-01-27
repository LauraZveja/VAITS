package lv.vaits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {

    @NotNull
    @Size(min = 3, message = "Garumam jābūt vismaz 3 rakstzīmju garam!")
    private String description;

    @NotBlank
    @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam!")
    @Size(min = 3, max = 30)
    private String staffName;

    @NotBlank
    @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam!")
    @Size(min = 3, max = 30)
    private String staffSurname;

    @Size(min = 3, max = 25, message = "Garumam jābūt no 3 līdz 25 rakstzīmju garam!")
    @NotNull
    @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam!")
    private String thesisTitle;
}
