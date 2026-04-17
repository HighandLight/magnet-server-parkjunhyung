package com.parkjunhyung.magnet_server_parkjunhyung.interaction.controller;

import com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto.CheckWordRequest;
import com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto.EvaluateLayoutRequest;
import com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto.InteractionResultResponse;
import com.parkjunhyung.magnet_server_parkjunhyung.interaction.service.InteractionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping("/check")
    public ResponseEntity<InteractionResultResponse> check(@Valid @RequestBody CheckWordRequest request) {
        return ResponseEntity.ok(interactionService.checkWord(request.word()));
    }

    @PostMapping("/evaluate-layout")
    public ResponseEntity<InteractionResultResponse> evaluateLayout(@Valid @RequestBody EvaluateLayoutRequest request) {
        return ResponseEntity.ok(interactionService.evaluateLayout(request.magnets()));
    }

}
