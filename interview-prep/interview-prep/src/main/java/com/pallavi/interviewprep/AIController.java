
package com.pallavi.interviewprep;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Value("${gemini.api.key:}")
    private String apiKey;

    @Value("${gemini.model:gemini-2.5-flash}")
    private String model;

    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> ask(
            @RequestBody Map<String, String> request) {

        String question = request.getOrDefault("question", "").trim();
        String language = request.getOrDefault("language", "Java");

        if (question.isBlank() || question.length() > 4000) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Enter a question containing 1 to 4000 characters."
            ));
        }

        if (apiKey.isBlank()) {
            return ResponseEntity.status(503).body(Map.of(
                "error", "GEMINI_API_KEY is not configured."
            ));
        }

        try {
            String instructions = """
                You are an expert technical interview preparation tutor.
                Answer questions about computer science, programming,
                aptitude, academics, and interview preparation.

                Give clear and accurate explanations.

                For coding questions:
                1. Explain the approach.
                2. Give complete code in the requested language.
                3. Include sample input and output when applicable.
                4. Explain time and space complexity.
                5. Mention important edge cases.

                For theory questions, provide a definition,
                explanation, examples, and interview tips where useful.
                Do not invent facts. Mention uncertainty when necessary.
                """;

            String prompt = instructions
                + "\n\nPreferred programming language: " + language
                + "\n\nUser question:\n" + question;

            Map<String, Object> payload = Map.of(
                "contents", List.of(
                    Map.of(
                        "parts", List.of(
                            Map.of("text", prompt)
                        )
                    )
                ),
                "generationConfig", Map.of(
                    "temperature", 0.4,
                    "maxOutputTokens", 2048
                )
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-goog-api-key", apiKey);

            HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(payload, headers);

            String url =
                "https://generativelanguage.googleapis.com/v1beta/models/"
                + model + ":generateContent";

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
            );

            JsonNode root = mapper.readTree(response.getBody());
            StringBuilder answer = new StringBuilder();

            JsonNode parts = root.path("candidates")
                .path(0)
                .path("content")
                .path("parts");

            if (parts.isArray()) {
                for (JsonNode part : parts) {
                    if (part.hasNonNull("text")) {
                        answer.append(part.get("text").asText());
                    }
                }
            }

            if (answer.length() == 0) {
                return ResponseEntity.status(502).body(Map.of(
                    "error", "Gemini returned no text. Please try again."
                ));
            }

            return ResponseEntity.ok(
                Map.of("answer", answer.toString())
            );

        } catch (RestClientResponseException e) {
            int status = e.getStatusCode().value();

            String message = switch (status) {
                case 400 -> "Gemini rejected the request. Check the model and request.";
                case 401, 403 -> "Gemini API key invalid or API access denied. Check AI Studio.";
                case 404 -> "Gemini model not found. Check the configured model name.";
                case 429 -> "Gemini quota or rate limit reached. Check AI Studio usage limits.";
                default -> "Gemini API error (" + status + ").";
            };

            return ResponseEntity.status(502).body(
                Map.of("error", message)
            );

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "error", "Unable to get an answer. Check server logs."
            ));
        }
    }
}
