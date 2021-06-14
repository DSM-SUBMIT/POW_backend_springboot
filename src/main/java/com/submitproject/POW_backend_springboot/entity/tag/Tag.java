package com.submitproject.POW_backend_springboot.entity.tag;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTag;
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
@Entity(name = "tbl_tag")
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private TagType tagType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    @JsonManagedReference
    private List<ClubTag> clubTag;

}
