package com.adaming.crowdBackend.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.adaming.crowdBackend.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	// Récupère les derniers 8 projets soumis. Utilisé notamment sur la page d'accueil
	public List<Project> findTop8ByOrderBySubmissionDateDesc();
	
	// Récupère les projets dont le titre contient {str}. Un sort permet de classer les résultats
	@Query("select p from Project p where p.title like concat('%',:str,'%')")
	public List<Project> findAllWithSearch(@Param("str") String str, Sort sort);
	
	// Idem que ci-dessus, mais pour une catégorie spécifique
	@Query("select p from Project p where p.category.id = :catId and p.title like concat('%',:str,'%')")
	public List<Project> findAllWithSearchForCategory(@Param("str") String str, @Param("catId") Long catId, Sort sort);
	
	// Récupère tous les projets créés par le Contributor voulu
	@Query("select p from Project p where p.creator.id = :id")
	public List<Project> projectsWithUser(@Param("id") Long id);
	
	// Récupère les projets backés par le Contributor (càd ceux pour lesquels il a fait une donation)
	@Query("select distinct p from Donation d inner join d.contributor c join d.project p where d.contributor.id = :id")
	public List<Project> projectsBackedByUser(@Param("id") Long id);
	
	
	
	// ---------------------- OLD -------------------------------------------
	
	/*public List<Project> findAllByTitleContaining(String str);
	public List<Project> findAllByOrderByTitleAsc();
	public List<Project> findAllByTitleContainingOrderByTitleAsc(String str);
	public List<Project> findAllByOrderByTitleDesc();
	public List<Project> findAllByTitleContainingOrderByTitleDesc(String str);
	public List<Project> findAllByTitleOrderByIdAsc();
	public List<Project> findAllByTitleContainingOrderByIdAsc(String str);
	public List<Project> findAllByTitleOrderByIdDesc();
	public List<Project> findAllByTitleContainingOrderByIdDesc(String str);*/
	
}
