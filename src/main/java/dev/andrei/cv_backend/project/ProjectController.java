package dev.andrei.cv_backend.project;

import dev.andrei.cv_backend.common.Language;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST endpoint that exposes the portfolio projects in the requested language.
 */
@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/api/projects")
    public List<ProjectDto> getProjects(@RequestParam(defaultValue = "de") String lang) {
        Language language = Language.fromCode(lang);
        return projectService.getProjects(language);
    }
}
