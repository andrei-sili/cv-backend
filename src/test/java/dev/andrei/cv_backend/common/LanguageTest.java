package dev.andrei.cv_backend.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LanguageTest {

    @Test
    void resolvesKnownCodesIgnoringCase() {
        assertThat(Language.fromCode("de")).isEqualTo(Language.DE);
        assertThat(Language.fromCode("EN")).isEqualTo(Language.EN);
    }

    @Test
    void trimsSurroundingWhitespace() {
        assertThat(Language.fromCode("  de  ")).isEqualTo(Language.DE);
    }

    @Test
    void fallsBackToGermanForNullOrUnknownCode() {
        assertThat(Language.fromCode(null)).isEqualTo(Language.DE);
        assertThat(Language.fromCode("fr")).isEqualTo(Language.DE);
    }
}