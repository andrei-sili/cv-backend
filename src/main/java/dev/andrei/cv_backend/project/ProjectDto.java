package dev.andrei.cv_backend.project;

/** A project flattened for a single language, as returned by the API. */
public record ProjectDto(
        Long id,
        String title,
        String description,
        String techStack,
        String repoUrl
) {
}