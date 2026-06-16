package dev.andrei.cv_backend.project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * A portfolio project. Language-independent data lives here;
 * translatable text lives in {@link ProjectTranslation}.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String techStack;

    private String repoUrl;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectTranslation> translations = new ArrayList<>();

    protected Project() {
        // Required by JPA (Hibernate needs a no-arg constructor).
    }

    public Project(String techStack, String repoUrl) {
        this.techStack = techStack;
        this.repoUrl = repoUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTechStack() {
        return techStack;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public List<ProjectTranslation> getTranslations() {
        return translations;
    }

    /** Adds a translation and keeps both sides of the relationship in sync. */
    public void addTranslation(ProjectTranslation translation) {
        translations.add(translation);
        translation.setProject(this);
    }
}