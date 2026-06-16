package dev.andrei.cv_backend.profile;

import dev.andrei.cv_backend.common.Language;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileServiceTest {

    private final ProfileService profileService = new ProfileService();

    @Test
    void returnsGermanProfile() {
        Profile profile = profileService.getProfile(Language.DE);

        assertThat(profile.name()).isEqualTo("Andrei Sili");
        assertThat(profile.title()).isEqualTo("Softwareentwickler");
        assertThat(profile.bio()).isNotBlank();
    }

    @Test
    void returnsEnglishProfile() {
        Profile profile = profileService.getProfile(Language.EN);

        assertThat(profile.name()).isEqualTo("Andrei Sili");
        assertThat(profile.title()).isEqualTo("Software Developer");
        assertThat(profile.bio()).isNotBlank();
    }
}