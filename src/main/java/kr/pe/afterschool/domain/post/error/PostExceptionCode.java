package kr.pe.afterschool.domain.post.error;

import kr.pe.afterschool.global.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostExceptionCode implements ErrorProperty {

    POST_NOT_FOUND(404, "해당 게시물이 존재하지 않음");

    private final int status;
    private final String message;
}
