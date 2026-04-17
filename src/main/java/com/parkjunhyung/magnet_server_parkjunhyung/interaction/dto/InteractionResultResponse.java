package com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto;

public record InteractionResultResponse (
        boolean found,
        String matchedWord,
        String message
){

}
