package com.submitproject.POW_backend_springboot.entity.clubTag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.submitproject.POW_backend_springboot.entity.club.Club;
import com.submitproject.POW_backend_springboot.entity.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor // 기본 생성자를 만들어 준다.
@AllArgsConstructor // 필드 값을 모두 포함한 생성자를 자동으로 생성한다.
@Entity(name = "tbl_club_tag")
public class ClubTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    @JsonBackReference
    private Club club;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonBackReference
    private Tag tag;

}
