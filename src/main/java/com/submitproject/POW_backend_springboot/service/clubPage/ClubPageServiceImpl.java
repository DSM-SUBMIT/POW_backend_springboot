package com.submitproject.POW_backend_springboot.service.clubPage;


import com.submitproject.POW_backend_springboot.dto.ClubDto.*;
import com.submitproject.POW_backend_springboot.dto.ClubPageDto.*;
import com.submitproject.POW_backend_springboot.entity.club.Club;
import com.submitproject.POW_backend_springboot.entity.club.ClubRepository;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTag;
import com.submitproject.POW_backend_springboot.entity.projectIntroduction.ProjectIntroduction;
import com.submitproject.POW_backend_springboot.entity.projectIntroduction.ProjectIntroductionRepository;
import com.submitproject.POW_backend_springboot.exception.ClubNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubPageServiceImpl implements ClubPageService {

    private final ClubRepository clubRepository;
    private final ProjectIntroductionRepository introductionRepository;

    @Override
    public ClubPageResponse viewClubPage(Integer id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(ClubNotFoundException::new);

        List<ProjectIntroduction> projectIntroductions = introductionRepository.findAllByClubId(id);
        List<Introduction> introductions = new ArrayList<>();

        for(ProjectIntroduction introduction : projectIntroductions) {
            introductions.add(
                    Introduction.builder()
                            .id(introduction.getId())
                            .title(introduction.getTitle())
                            .createdAt(introduction.getCreatedAt())
                            .build()
            );
        }

        return ClubPageResponse.builder()
                .name(club.getName())
                .profilePath(club.getProfilePath())
                .bannerPath(club.getBannerPath())
                .contents(club.getContents())
                .introduction(introductions)
                .build();
    }

    @Override
    public ClubsResponse getClubs() {
        List<Club> clubList = clubRepository.findAllByOrderByName();
        return getClubList(clubList);
    }

    @Override
    public ClubsResponse searchClub(String keyword) {
        List<Club> clubList = clubRepository.findAllByNameContainingOrderByName(keyword); // findBy랑 findAllBy는 동일한 쿼리를 실행..한대
        return getClubList(clubList);
    }

    private List<String> getTags(List<ClubTag> clubTags) {
        List<String> result = new ArrayList<>();
        for(ClubTag clubTag : clubTags) {
            result.add(clubTag.getTag().getTagType().toString());
        }
        return result;
    }

    private ClubsResponse getClubList(List<Club> clubList) {
        List<Clubs> clubs = new ArrayList<>();

        for(Club club : clubList) {
            clubs.add(
                    Clubs.builder()
                            .id(club.getId())
                            .name(club.getName())
                            .tags(getTags(club.getClubTag()))
                            .profilePath(club.getProfilePath())
                            .build()
            );
        }

        return ClubsResponse.builder()
                .clubs(clubs)
                .build();
    }

}
