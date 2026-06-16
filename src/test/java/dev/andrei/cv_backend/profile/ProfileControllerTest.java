package dev.andrei.cv_backend.profile;

import dev.andrei.cv_backend.common.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProfileService profileService;

    @Test
    void returnsEnglishProfileAsJson() throws Exception {
        when(profileService.getProfile(Language.EN))
                .thenReturn(new Profile("Andrei Sili", "Software Developer", "A short bio."));

        mockMvc.perform(get("/api/profile?lang=en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Andrei Sili"))
                .andExpect(jsonPath("$.title").value("Software Developer"))
                .andExpect(jsonPath("$.bio").value("A short bio."));
    }

    @Test
    void defaultsToGermanWhenNoLangGiven() throws Exception {
        when(profileService.getProfile(Language.DE))
                .thenReturn(new Profile("Andrei Sili", "Softwareentwickler", "Kurzbio."));

        mockMvc.perform(get("/api/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Softwareentwickler"));
    }
}
