package kr.pe.afterschool.global.error.exception;

import kr.pe.afterschool.global.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GlobalExceptionCode implements ErrorProperty {

    INVALID_TOKEN(401, "Invalid Token"),
    NO_AUTHENTICATION(403, "권한이 없습니다"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error")
    ;

    private final int status;
    private final String message;
}
