package com.submitproject.POW_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submitproject.POW_backend_springboot.dto.AccountDto.*;
import com.submitproject.POW_backend_springboot.entity.club.Club;
import com.submitproject.POW_backend_springboot.entity.club.ClubRepository;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTag;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTagRepository;
import com.submitproject.POW_backend_springboot.entity.tag.Tag;
import com.submitproject.POW_backend_springboot.entity.tag.TagRepository;
import com.submitproject.POW_backend_springboot.entity.tag.TagType;
import com.submitproject.POW_backend_springboot.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PowBackendSpringbootApplication.class)
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ClubTagRepository clubTagRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    String accessToken;
    String refreshToken;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        Club clubSub = clubRepository.save(
                Club.builder()
                        .code("SUB112")
                        .password("hi")
                        .name("SUBMIT")
                        .clubTag(null)
                        .bannerPath(null)
                        .contents(null)
                        .build()
        );

        Tag tagMajor = tagRepository.save(
                Tag.builder()
                        .tagType(TagType.MAJOR)
                        .build()
        );

        Tag tagAutonomous = tagRepository.save(
                Tag.builder()
                        .tagType(TagType.AUTONOMOUS)
                        .build()
        );

        clubTagRepository.save(
                ClubTag.builder()
                        .club(clubSub)
                        .tag(tagMajor)
                        .build()
        );

        Club clubJCH = clubRepository.save(
                Club.builder()
                        .code("JCH113")
                        .password("hi")
                        .name("자치회")
                        .clubTag(null)
                        .bannerPath(null)
                        .contents(null)
                        .build()
        );

        clubTagRepository.save(
                ClubTag.builder()
                        .club(clubJCH)
                        .tag(tagAutonomous)
                        .build()
        );

        //이것만 저장한다고 db에 저장되는 건 아님. repository 사용해서 redis에 저장하기
        accessToken = jwtTokenProvider.generateAccessToken(clubSub.getId());

    }

    @AfterEach
    public void deleteAll() {
        clubTagRepository.deleteAll();
        clubRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    public void login() throws Exception {
        SignInRequest signInRequest = new SignInRequest("SUB112", "hi");

        mvc.perform(post("/auth")
                .content(new ObjectMapper().writeValueAsString(signInRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()); //ok
    }

    @Test
    public void login_exception() throws Exception {
        SignInRequest signInRequest = new SignInRequest("SUB112", "hello");

        mvc.perform(post("/auth")
                .content(new ObjectMapper().writeValueAsString(signInRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound()); //ok
    }

}
