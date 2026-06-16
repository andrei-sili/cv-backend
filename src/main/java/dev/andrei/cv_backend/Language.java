package dev.andrei.cv_backend;

/**
 * Languages supported by the public CV API.
 */
public enum Language {

    DE,
    EN;

    /**
     * Resolves a language code (e.g. "de", "EN") to a {@link Language}.
     * Falls back to {@link #DE} when the code is missing or unknown.
     */
    public static Language fromCode(String code) {
        if (code == null) {
            return DE;
        }
        try {
            return Language.valueOf(code.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            return DE;
        }
    }
}