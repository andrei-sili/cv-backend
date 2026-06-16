package dev.andrei.cv_backend.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    /** Loads all projects together with their translations in a single query. */
    @Query("select distinct p from Project p left join fetch p.translations")
    List<Project> findAllWithTranslations();
}