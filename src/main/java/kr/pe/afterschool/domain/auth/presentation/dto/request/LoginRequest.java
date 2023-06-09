package kr.pe.afterschool.domain.auth.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotBlank(message = "email must not be blank")
    private String email;
    @NotBlank(message = "pw must not be blank")
    private String pw;
}
