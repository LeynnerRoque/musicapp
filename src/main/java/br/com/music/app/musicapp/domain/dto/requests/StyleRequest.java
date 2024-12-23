package br.com.music.app.musicapp.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StyleRequest(
       @NotEmpty
               @NotBlank(message = "This field cannot be empty or null")
               @NotNull
        String nameStyle
) {
}
