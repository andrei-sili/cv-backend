package dev.andrei.cv_backend.contact;

import org.springframework.stereotype.Service;

/**
 * Stores contact messages submitted through the public CV site.
 */
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactMessage save(ContactRequest request) {
        ContactMessage message = new ContactMessage(
                request.name(),
                request.email(),
                request.message()
        );
        return contactRepository.save(message);
    }
}
