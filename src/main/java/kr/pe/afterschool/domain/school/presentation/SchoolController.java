package kr.pe.afterschool.domain.school.presentation;

import kr.pe.afterschool.domain.school.presentation.dto.request.SchoolCreateRequest;
import kr.pe.afterschool.domain.school.presentation.dto.response.SchoolResponse;
import kr.pe.afterschool.domain.school.service.SchoolByAddressQueryService;
import kr.pe.afterschool.domain.school.service.SchoolCreateService;
import kr.pe.afterschool.domain.school.service.SchoolQueryService;
import kr.pe.afterschool.global.response.Response;
import kr.pe.afterschool.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolQueryService schoolQueryService;
    private final SchoolByAddressQueryService schoolByAddressQueryService;
    private final SchoolCreateService schoolCreateService;

    @GetMapping("/{schoolId}")
    public ResponseData<SchoolResponse> getSchoolById(
            @PathVariable Long schoolId
    ) {
        SchoolResponse response = schoolQueryService.execute(schoolId);
        return new ResponseData<>(
                HttpStatus.OK,
                "해당 아이디의 학교 조회 성공",
                response
        );
    }

    @GetMapping("/address")
    public ResponseData<List<SchoolResponse>> getSchoolByCity(
            @RequestParam(name = "address") String address
    ) {
        List<SchoolResponse> response = schoolByAddressQueryService.execute(address);
        return new ResponseData<>(
                HttpStatus.OK,
                "해당 도시의 학교 조회 성공",
                response
        );
    }

    @PostMapping
    public Response createSchool(
            @RequestBody @Valid SchoolCreateRequest request
    ) {
        schoolCreateService.execute(request);
        return new Response(
                HttpStatus.OK,
                "학교 생성 성공"
        );
    }
}
