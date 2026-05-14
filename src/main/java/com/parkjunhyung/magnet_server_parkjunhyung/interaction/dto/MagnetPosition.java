package com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MagnetPosition(
        @NotBlank @Size(min = 1, max = 10) String letter, //자석 "COF", "FEE"식으로 오는 경우 고려
        @NotNull Double x,
        @NotNull Double y
) {
}
