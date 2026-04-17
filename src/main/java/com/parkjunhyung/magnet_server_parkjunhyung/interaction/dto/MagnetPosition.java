package com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MagnetPosition(
        @NotBlank @Size(min = 1, max = 1) String letter,
        @NotNull Double x,
        @NotNull Double y
) {
}
