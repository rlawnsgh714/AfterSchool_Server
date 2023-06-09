package kr.pe.afterschool.domain.school.service;

import kr.pe.afterschool.domain.school.entity.School;
import kr.pe.afterschool.domain.school.entity.repository.SchoolRepository;
import kr.pe.afterschool.domain.school.exception.SchoolAlreadyExistException;
import kr.pe.afterschool.domain.school.presentation.dto.request.SchoolCreateRequest;
import kr.pe.afterschool.domain.user.entity.User;
import kr.pe.afterschool.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolCreateService {

    private final SchoolRepository schoolRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(SchoolCreateRequest request) {
        User user = userFacade.getCurrentUser();
        if (schoolRepository.existsByNameAndAddress(request.getName(), request.getAddress())) {
            throw SchoolAlreadyExistException.EXCEPTION;
        }
        School school = School.builder()
                .name(request.getName())
                .address(request.getAddress())
                .educationOffice(request.getEducationOffice())
                .homePage(request.getHomePage())
                .phone(request.getPhone())
                .teacher(user)
                .build();
        schoolRepository.save(school);
    }
}
