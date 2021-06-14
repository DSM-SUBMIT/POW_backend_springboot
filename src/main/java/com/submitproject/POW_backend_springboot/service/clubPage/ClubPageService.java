package com.submitproject.POW_backend_springboot.service.clubPage;

import com.submitproject.POW_backend_springboot.dto.ClubDto.*;
import com.submitproject.POW_backend_springboot.dto.ClubPageDto.*;

public interface ClubPageService {
    ClubPageResponse viewClubPage(Integer id);
    ClubsResponse getClubs();
    ClubsResponse searchClub(String keyword);
}
