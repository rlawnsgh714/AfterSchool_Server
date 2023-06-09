package kr.pe.afterschool.domain.survey.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerCreateRequest {

    @NotNull(message = "content must not null")
    private List<String> answers;
    @NotNull(message = "questionId must no null")
    private Long questionId;
}
