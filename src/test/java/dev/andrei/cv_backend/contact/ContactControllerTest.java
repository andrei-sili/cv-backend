package dev.andrei.cv_backend.contact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContactService contactService;

    @Test
    void acceptsValidRequestWith201() throws Exception {
        String body = """
                {"name":"Andrei","email":"a@example.com","message":"Salut!"}
                """;

        mockMvc.perform(post("/api/contact")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isCreated());

        verify(contactService).save(any(ContactRequest.class));
    }

    @Test
    void rejectsInvalidRequestWith400() throws Exception {
        String body = """
                {"name":"","email":"not-an-email","message":"x"}
                """;

        mockMvc.perform(post("/api/contact")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(contactService);
    }
}