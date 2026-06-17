package dev.andrei.cv_backend.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Data accepted from the client when submitting the contact form. */
public record ContactRequest(

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(max = 2000)
        String message
) {
}