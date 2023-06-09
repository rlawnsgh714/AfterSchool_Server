package kr.pe.afterschool.domain.classroom.entity.repository;

import kr.pe.afterschool.domain.classroom.entity.Classroom;
import kr.pe.afterschool.domain.school.entity.School;
import kr.pe.afterschool.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findByEndDateBetween(LocalDate startDate, LocalDate endDate);
    List<Classroom> findBySchool(School school);
    List<Classroom> findByTeacher(User teacher);
}
