package com.submitproject.POW_backend_springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

        @JsonProperty("profile_path")
        private String profilePath;

        @JsonProperty("banner_path")
        private String bannerPath;

        private String contents;

        private List<Introduction> introduction;
    }

    @Getter
    @Builder
    public static class Introduction {
        private Integer id;

        private String title;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;
    }

}
