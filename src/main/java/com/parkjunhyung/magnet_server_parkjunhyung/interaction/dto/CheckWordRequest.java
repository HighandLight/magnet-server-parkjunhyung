package com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CheckWordRequest (
        @NotBlank @Size(min = 1, max = 30) String word
) {}