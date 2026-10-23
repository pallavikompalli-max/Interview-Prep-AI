
package com.pallavi.interviewprep;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/api/questions")
    public List<Question> getQuestions(
            @RequestParam(required = false) String topic) {

        if (topic == null || topic.isBlank()
                || topic.equalsIgnoreCase("All")) {
            return questionRepository.findAll();
        }

        return questionRepository.findByTopicIgnoreCase(topic);
    }
}
