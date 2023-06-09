package kr.pe.afterschool.domain.user.entity;

import kr.pe.afterschool.domain.school.entity.School;
import kr.pe.afterschool.global.enums.JoinMethod;
import kr.pe.afterschool.global.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    private String email;

    @Column()
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column()
    private int grade;

    @Column()
    private int room;

    @Column()
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private String profileImageUrl;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_school_id")
    private School school;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JoinMethod joinMethod;

    public void editSchool(School school) {
        this.school = school;
    }

    public void editUserData(String name, String phone, int grade, int room, int number, String profileImageUrl) {
        this.name = name;
        this.phone = phone;
        this.grade = grade;
        this.room = room;
        this.number = number;
        this.profileImageUrl = profileImageUrl;
    }

    @Builder
    public User(String email, String pw, String name, String phone, int grade, int room, int number, UserRole role, String profileImageUrl, School school, JoinMethod joinMethod) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.grade = grade;
        this.room = room;
        this.number = number;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
        this.joinDate = LocalDate.now();
        this.school = school;
        this.joinMethod = joinMethod;
    }
}
