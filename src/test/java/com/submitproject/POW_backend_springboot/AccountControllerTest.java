package com.submitproject.POW_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submitproject.POW_backend_springboot.dto.AccountDto;
import com.submitproject.POW_backend_springboot.entity.club.Club;
import com.submitproject.POW_backend_springboot.entity.club.ClubRepository;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTag;
import com.submitproject.POW_backend_springboot.entity.clubTag.ClubTagRepository;
import com.submitproject.POW_backend_springboot.entity.tag.Tag;
import com.submitproject.POW_backend_springboot.entity.tag.TagRepository;
import com.submitproject.POW_backend_springboot.entity.tag.TagType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PowBackendSpringbootApplication.class)
@ActiveProfiles("test")
public class AccountControllerTest {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ClubTagRepository clubTagRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

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

        clubTagRepository.save(
                ClubTag.builder()
                        .club(clubSub)
                        .tag(tagMajor)
                        .build()
        );
    }

    @AfterEach
    public void deleteAll() {
        clubTagRepository.deleteAll();
        clubRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "SUB112", password = "hi")
    public void deleteAccount() throws Exception {
        mvc.perform(delete("/account"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "SUB113", password = "hi") //UserDetails 생성/ 계정이 없어도 그냥 만들어버림.
    public void deleteAccount_x() throws Exception {
        mvc.perform(delete("/account"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "SUB112", password = "hi")
    public void updatePassword() throws Exception {
        AccountDto.PasswordRequest request = new AccountDto.PasswordRequest("hi","hello");
        mvc.perform(patch("/account")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "SUB112", password = "hi")
    public void updatePassword_x() throws Exception {
        AccountDto.PasswordRequest request = new AccountDto.PasswordRequest("hello","hello");
        mvc.perform(patch("/account")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

}
