package com.submitproject.POW_backend_springboot;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PowBackendSpringbootApplication.class)
@ActiveProfiles("test")
public class ClubControllerTest {

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
                        .name("서브밋")
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
        clubRepository.deleteAll();
        tagRepository.deleteAll();
        clubTagRepository.deleteAll();
    }

    @Test
    public void searchClub() throws Exception {
        mvc.perform(get("/club/search?name="))
                .andExpect(status().isOk()); //ok
    }

    @Test
    public void searchClub_1() throws Exception {
        mvc.perform(get("/club/search?name=서"))
                .andExpect(status().isOk()); //ok
    }

    @Test
    public void searchClub_full() throws Exception {
        mvc.perform(get("/club/search?name=서브밋"))
                .andExpect(status().isOk()); //ok
    }

    @Test
    public void main() throws Exception {
        createdAccount("SIN");
        createdAccount("GRA");
        createdAccount("RAD");
        mvc.perform(get("/club"))
                .andExpect(status().isOk()); //ok
    }

    @Test
    public void clubpage() throws Exception {
        createdAccount("SIN");
        createdAccount("GRA");
        createdAccount("RAD");
        mvc.perform(get("/clubpage/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    private void createdAccount(String name) {
        Club club = clubRepository.save(
                Club.builder()
                        .code(name+"111")
                        .password("hi")
                        .name(name)
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
                        .club(club)
                        .tag(tagMajor)
                        .build()
        );
    }

}
