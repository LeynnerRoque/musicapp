package br.com.music.app.musicapp.domain.dto.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StyleResponse {
    private Long id;
    @NotNull
    @NotBlank(message = "This field cannot be empty or null")
    @NotEmpty
    private String nameStyle;
}
