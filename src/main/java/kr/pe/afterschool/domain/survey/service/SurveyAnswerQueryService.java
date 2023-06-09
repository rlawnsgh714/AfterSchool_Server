package kr.pe.afterschool.domain.survey.service;

import kr.pe.afterschool.domain.survey.entity.repository.AnswerRepository;
import kr.pe.afterschool.domain.survey.exception.AnswerNotFoundException;
import kr.pe.afterschool.domain.survey.presentation.dto.response.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyAnswerQueryService {

    private final AnswerRepository answerRepository;

    @Transactional(readOnly = true)
    public AnswerResponse execute(Long answerId) {
        return new AnswerResponse(
                answerRepository.findById(answerId)
                .orElseThrow(() -> AnswerNotFoundException.EXCEPTION)
        );
    }
}
