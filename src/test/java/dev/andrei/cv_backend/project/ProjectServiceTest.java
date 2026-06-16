package dev.andrei.cv_backend.project;

import dev.andrei.cv_backend.common.Language;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    private final ProjectRepository projectRepository = mock(ProjectRepository.class);
    private final ProjectService projectService = new ProjectService(projectRepository);

    @Test
    void mapsProjectToRequestedLanguage() {
        Project project = new Project("Java, Spring Boot", "https://example.com/repo");
        project.addTranslation(new ProjectTranslation(Language.DE, "Titel DE", "Beschreibung DE"));
        project.addTranslation(new ProjectTranslation(Language.EN, "Title EN", "Description EN"));
        when(projectRepository.findAllWithTranslations()).thenReturn(List.of(project));

        List<ProjectDto> result = projectService.getProjects(Language.EN);

        assertThat(result).hasSize(1);
        ProjectDto dto = result.get(0);
        assertThat(dto.title()).isEqualTo("Title EN");
        assertThat(dto.description()).isEqualTo("Description EN");
        assertThat(dto.techStack()).isEqualTo("Java, Spring Boot");
        assertThat(dto.repoUrl()).isEqualTo("https://example.com/repo");
    }
}
