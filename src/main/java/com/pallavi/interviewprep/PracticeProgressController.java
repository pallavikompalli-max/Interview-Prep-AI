
package com.pallavi.interviewprep;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/progress")
public class PracticeProgressController {

    private final PracticeProgressRepository repository;
    private final QuestionRepository questionRepository;

    public PracticeProgressController(
            PracticeProgressRepository repository,
            QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public List<Long> getPracticedQuestions() {
        return repository.findAll().stream()
                .filter(PracticeProgress::isPracticed)
                .map(PracticeProgress::getQuestionId)
                .toList();
    }

    @PostMapping("/{questionId}")
    public List<Long> markPracticed(@PathVariable Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Question not found");
        }

        repository.save(new PracticeProgress(questionId, true));
        return getPracticedQuestions();
    }

    @DeleteMapping("/{questionId}")
    public List<Long> unmarkPracticed(@PathVariable Long questionId) {
        repository.deleteById(questionId);
        return getPracticedQuestions();
    }
}
