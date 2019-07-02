package com.adaming.crowdBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adaming.crowdBackend.models.Contributor;

public interface ContributorRepository extends JpaRepository<Contributor, Long>{
	
	public Contributor findByNickname(String name);
	
	// Renvoie le Contributor ayant le plus donné
	public Contributor findFirstByOrderByTotalDesc();
	
	// Renvoie les Contributor du plus donateur au moins donateur
	public List<Contributor> findAllByOrderByTotalDesc();
	
	// Renvoie le top 5 des contributeurs ayant le plus donné
	public List<Contributor> findTop5ByTotalNotOrderByTotalDesc(double val);
	
	// Renvoie les Contributor (ordre alphabetique)
	//TODO : on devrait créer une fonction similaire, mais ne prenant pas en compte les contributeurs n'ayant rien donné
	public List<Contributor> findAllByOrderByNicknameAsc();
	
	// Renvoie les Contributors classés par tempAmount
	// TempAmount est une variable temporaire qui prend par exemple le total des donations dans une catégorie spécifique
	public List<Contributor> findAllByOrderByTempAmountDesc();
	
	// Utilisé notamment pour évincer les contributeurs ayant donné 0 dans une catégorie
	public List<Contributor> findAllByTempAmountNot(double val);
	
	// Renvoie les contributeurs ayant un tempAmount != val, ordonnés du plus grand tempAmount au plus petit
	public List<Contributor> findAllByTempAmountNotOrderByTempAmountDesc(double val);
	// Idem que le précédent, mais renvoie juste le top 5
	public List<Contributor> findTop5ByTempAmountNotOrderByTempAmountDesc(double val);

}
