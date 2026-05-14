package com.parkjunhyung.magnet_server_parkjunhyung.interaction.service;

import com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto.InteractionResultResponse;
import com.parkjunhyung.magnet_server_parkjunhyung.interaction.dto.MagnetPosition;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class InteractionService {

    private static final double Y_TOLERANCE = 30.0;
    private static final double MIN_GAP = 8.0;
    private static final double MAX_GAP = 95.0;

    private static final Map<String, String> INTERACTION_MAP = Map.ofEntries(

            Map.entry("hello", "Hi! I'm Junhyung!"),
            Map.entry("github", "https://github.com/HighandLight"),
            Map.entry("email", "junhyunghl126@gmail.com"),
            Map.entry("contact", "junhyunghl126@gmail.com"),
            Map.entry("love", "❤️")

    );

    public InteractionResultResponse checkWord(String word) {
        String normalized = word == null ? "" : word.trim().toLowerCase();
        String message = INTERACTION_MAP.get(word);
        if(message == null) {
            return new InteractionResultResponse(false, "", "");
        }
        return new InteractionResultResponse(true, normalized, message);
    }

    public InteractionResultResponse evaluateLayout(List<MagnetPosition> magnets) {
        List<MagnetNode> nodes = magnets.stream()
                .map(m -> new MagnetNode(
                        m.letter().strip().toLowerCase(),
                        m.x() + 26.0,
                        m.y() + 26.0
                ))
                .filter(node -> node.letter().length() == 1)
                .toList();

        List<String> words = INTERACTION_MAP.keySet().stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toList();

        for(String word : words) {
            if(canBuildWord(word, nodes)) {
                return new InteractionResultResponse(true, word, INTERACTION_MAP.get(word));
            }
        }

        return new InteractionResultResponse(false, "", "");
    }

    private boolean canBuildWord(String word, List<MagnetNode> nodes) {
        char[] letters = word.toCharArray();
        for(int i = 0; i < nodes.size(); ++i) {
            if(nodes.get(i).letter().charAt(0) != letters[0]) {
                continue;
            }

            boolean[] used = new boolean[nodes.size()];
            used[i] = true;
            MagnetNode last = nodes.get(i);
            boolean success = true;

            for(int li = 1; li < letters.length; ++li) {
                int candidateIndex = findNextCandidate(letters[li], last, nodes, used);
                if(candidateIndex < 0) {
                    success = false;
                    break;
                }
                used[candidateIndex] = true;
                last = nodes.get(candidateIndex);
            }

            if(success) return true;
        }
        return false;
    }

    private int findNextCandidate(char target, MagnetNode last, List<MagnetNode> nodes,  boolean[] used) {
        int bestIndex = -1;
        double bestX = Double.MAX_VALUE;

        for(int i = 0; i < nodes.size(); ++i) {
            if(used[i]) continue;
            MagnetNode current = nodes.get(i);
            if(current.letter().charAt(0) != target) continue;

            double dx = current.centerX() - last.centerX();
            double dy = Math.abs(current.centerY() - last.centerY());

            if(dx < MIN_GAP || dx > MAX_GAP || dy > Y_TOLERANCE) continue;

            if(current.centerX() < bestX) {
                bestX = current.centerX();
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    private record MagnetNode(String letter, double centerX, double centerY) {}
}
