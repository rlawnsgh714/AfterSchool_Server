package kr.pe.afterschool.domain.auth.error;

import kr.pe.afterschool.global.error.ErrorProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthExceptionCode implements ErrorProperty {
    ALREADY_JOIN(403, "이미 가입된 아이디입니다"),
    DO_NOT_JOIN(404, "가입하지 않았습니다"),
    ;

    private final int status;
    private final String message;
}