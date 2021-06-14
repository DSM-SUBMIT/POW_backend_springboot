package com.submitproject.POW_backend_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ClubPageDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubPageResponse {
        private String name;

        private String profilePath;

        private String bannerPath;

        private String contents;

        private List<Introduction> introduction;
    }

    @Getter
    @Builder
    public static class Introduction {
        private Integer id;

        private String title;

        private LocalDateTime createdAt;
    }

}
