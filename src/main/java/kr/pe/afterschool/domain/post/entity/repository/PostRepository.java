package kr.pe.afterschool.domain.post.entity.repository;

import kr.pe.afterschool.domain.post.entity.Post;
import kr.pe.afterschool.domain.school.entity.School;
import kr.pe.afterschool.domain.user.entity.User;
import kr.pe.afterschool.global.enums.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByType(PostType type);
    List<Post> findByUser(User user);
    List<Post> findBySchool(School school);
}
