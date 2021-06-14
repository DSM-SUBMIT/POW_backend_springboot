package com.submitproject.POW_backend_springboot.entity.club;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTag;
import com.submitproject.POW_backend_springboot.entity.projectIntroduction.ProjectIntroduction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_club")
// @Entity - 엔티티(데이터의 집합) 클래스임을 지정하며 테이블과 매핑된다.
// @Table - 엔티티가 매핑될 테이블을 지정하고 생략시 엔티티 클래스 이름과 같은 테이블로 매핑된다.
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 6, nullable = false, unique = true)
    private String code;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 256)
    private String contents;

    private String profilePath;

    private String bannerPath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    @JsonManagedReference
    private List<ClubTag> clubTag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    @JsonManagedReference
    private List<ProjectIntroduction> projectIntroductions;

    public void updatePassword(String password) {
        this.password = password;
    }

}
