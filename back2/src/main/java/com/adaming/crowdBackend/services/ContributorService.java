package com.adaming.crowdBackend.services;

import java.util.List;

import com.adaming.crowdBackend.models.Contributor;

public interface ContributorService {
	public Contributor findById(Long id);
	public List<Contributor> findAll();
	public void deleteById(Long id);
	public Contributor save(Contributor c);
	public Contributor findByNickname(String name);
	
	// Méthodes plus spécifiques
	public List<Contributor> findAllByDonations(); // renvoie tous les contributeurs ordonnés par leur total de donations
	public Contributor maxDonation(); // renvoie le total max de donation (càd le total de donations du contributeur qui a le plus donné)
	public List<Contributor> findAllByNickname(); // renvoie tous les contributeurs dans l'ordre alphabétique
	public List<Contributor> findTop5ByDonations();
	
	public List<Contributor> findAllByDonationsInCategory(Long category_id);
	public List<Contributor> findAllByDonationsInProject(Long project_id);
	
	//public Double AmountByContributorForCategory(int contributor_id, int category_id);
	
}
