package kr.pe.afterschool.domain.survey.error;

import kr.pe.afterschool.global.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SurveyExceptionCode implements ErrorProperty {

    QUESTION_CANNOT_MANAGE(403, "해당 설문 조사의 대답을 관리할 수 없음"),
    ANSWER_CANNOT_MANAGE(403, "해당 설문 조사의 대답을 관리할 수 없음"),
    QUESTION_NOT_FOUND(404, "해당 설문 조사의 질문이 존재하지 않음"),
    ANSWER_NOT_FOUND(404, "해당 설문 조사의 대답이 존재하지 않음");

    private final int status;
    private final String message;
}
