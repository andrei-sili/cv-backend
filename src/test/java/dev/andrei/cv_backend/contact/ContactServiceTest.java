package dev.andrei.cv_backend.contact;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContactServiceTest {

    private final ContactRepository contactRepository = mock(ContactRepository.class);
    private final ContactService contactService = new ContactService(contactRepository);

    @Test
    void mapsRequestToEntityAndSaves() {
        when(contactRepository.save(any(ContactMessage.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ContactMessage saved = contactService.save(
                new ContactRequest("Andrei", "a@example.com", "Salut!"));

        assertThat(saved.getName()).isEqualTo("Andrei");
        assertThat(saved.getEmail()).isEqualTo("a@example.com");
        assertThat(saved.getMessage()).isEqualTo("Salut!");
        assertThat(saved.getCreatedAt()).isNotNull();
    }
}