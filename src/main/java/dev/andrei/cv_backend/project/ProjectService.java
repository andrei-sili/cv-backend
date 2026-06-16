package dev.andrei.cv_backend.project;

import dev.andrei.cv_backend.common.Language;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Reads projects from the database and exposes them flattened for one language.
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> getProjects(Language language) {
        return projectRepository.findAllWithTranslations().stream()
                .map(project -> toDto(project, language))
                .toList();
    }

    private ProjectDto toDto(Project project, Language language) {
        ProjectTranslation translation = project.getTranslations().stream()
                .filter(t -> t.getLanguage() == language)
                .findFirst()
                .orElseThrow(); // every seeded project has both DE and EN
        return new ProjectDto(
                project.getId(),
                translation.getTitle(),
                translation.getDescription(),
                project.getTechStack(),
                project.getRepoUrl()
        );
    }
}