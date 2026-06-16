package dev.andrei.cv_backend.project;

import dev.andrei.cv_backend.common.Language;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inserts sample projects on startup (only when the table is empty),
 * so the API has data during development.
 */
@Component
public class ProjectDataSeeder implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    public ProjectDataSeeder(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) {
        if (projectRepository.count() > 0) {
            return; // Already seeded; don't duplicate on every restart.
        }

        Project cvBackend = new Project(
                "Java, Spring Boot, PostgreSQL",
                "https://github.com/andrei-sili/cv-backend"
        );
        cvBackend.addTranslation(new ProjectTranslation(
                Language.DE, "CV-Backend",
                "Eine REST-API mit Spring Boot und PostgreSQL für meine persönliche Website."
        ));
        cvBackend.addTranslation(new ProjectTranslation(
                Language.EN, "CV Backend",
                "A REST API built with Spring Boot and PostgreSQL for my personal website."
        ));

        Project portfolio = new Project(
                "Next.js, TypeScript, Tailwind",
                "https://github.com/andrei-sili/cv-frontend"
        );
        portfolio.addTranslation(new ProjectTranslation(
                Language.DE, "Portfolio-Website",
                "Das Frontend meiner persönlichen Website, gebaut mit Next.js."
        ));
        portfolio.addTranslation(new ProjectTranslation(
                Language.EN, "Portfolio Website",
                "The frontend of my personal website, built with Next.js."
        ));

        projectRepository.save(cvBackend);
        projectRepository.save(portfolio);
    }
}
