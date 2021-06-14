package com.submitproject.POW_backend_springboot.controller;

import com.submitproject.POW_backend_springboot.dto.ClubDto.*;
import com.submitproject.POW_backend_springboot.dto.ClubPageDto.*;
import com.submitproject.POW_backend_springboot.service.clubPage.ClubPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//공통 url을 설정해 주는 것이 아니라면 @RequestMapping을 사용하지 않아도 괜찮다.
@RequiredArgsConstructor
@RestController
public class ClubPageController {

    private final ClubPageService clubPageService;

    @GetMapping("/clubpage/{id}")
    public ClubPageResponse viewClubPage(@PathVariable Integer id) {
        return clubPageService.viewClubPage(id);
    }

    @GetMapping("/club")
    public ClubsResponse getClubs() {
        return clubPageService.getClubs();
    }

    @GetMapping("/club/search")
    public ClubsResponse searchClub(@RequestParam("name") String keyword) {
        return clubPageService.searchClub(keyword);
    }

}
