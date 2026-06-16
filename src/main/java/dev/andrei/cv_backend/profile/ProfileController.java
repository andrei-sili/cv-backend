package dev.andrei.cv_backend.profile;

import dev.andrei.cv_backend.common.Language;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint that exposes the personal profile in the requested language.
 */
@RestController
public class ProfileController {

    private final ProfileService profileService;

    // Spring injects the ProfileService bean here (constructor injection).
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/api/profile")
    public Profile getProfile(@RequestParam(defaultValue = "de") String lang) {
        Language language = Language.fromCode(lang);
        return profileService.getProfile(language);
    }
}
