package dev.andrei.cv_backend;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Provides the personal profile data exposed by the public CV API.
 * The data is hard-coded for now; it will later be loaded from the database.
 */
@Service
public class ProfileService {

    // Static content per language for now; we'll move it to the database later.
    private static final Map<Language, Profile> PROFILES = Map.of(
            Language.DE, new Profile(
                    "Andrei Sili",
                    "Softwareentwickler",
                    "Ich kann es kaum erwarten, dass die KI die ganze Arbeit übernimmt. In der Zwischenzeit entwickle ich Anwendungen mit Django, FastAPI und Spring."
            ),
            Language.EN, new Profile(
                    "Andrei Sili",
                    "Software Developer",
                    "Can't wait for AI to do all the work. In the meantime, I build applications with Django, FastAPI, and Spring."
            )
    );

    /** Returns the profile for the given language. */
    public Profile getProfile(Language language) {
        return PROFILES.get(language);
    }
}