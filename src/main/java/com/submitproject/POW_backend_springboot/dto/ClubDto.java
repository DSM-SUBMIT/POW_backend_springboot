package com.submitproject.POW_backend_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ClubDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubsResponse {
        private List<Clubs> clubs;
    }

    @Getter
    @Builder
    public static class Clubs {
        private Integer id;

        private String name;

        @JsonProperty("profile_path")
        private String profilePath;

        private List<String> tags;
    }

}
