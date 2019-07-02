package com.adaming.crowdBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adaming.crowdBackend.models.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long>{

	public List<Donation> findAllByOrderByIdAsc();
	public List<Donation> findAllByOrderByIdDesc();
	public List<Donation> findAllByOrderByDateTimeAsc();
	public List<Donation> findAllByOrderByDateTimeDesc();
	public List<Donation> findAllByOrderByAmountAsc();
	public List<Donation> findAllByOrderByAmountDesc();
	public List<Donation> findAllByOrderByDonationTypeAsc();
	public List<Donation> findAllByOrderByDonationTypeDesc();
	
	// Récupère les donations classées par contributeur (ordre alphabétique)
	@Query("select d from Donation d inner join d.contributor c order by c.nickname")
	public List<Donation> findAllByOrderByContributorAsc();
	@Query("select d from Donation d inner join d.contributor c order by c.nickname desc")
	public List<Donation> findAllByOrderByContributorDesc();
	
	// Récupère les donations classées par projet (ordre alphabétique)
	@Query("select d from Donation d inner join d.project p order by p.title")
	public List<Donation> findAllByOrderByProjectAsc();
	@Query("select d from Donation d inner join d.project p order by p.title desc")
	public List<Donation> findAllByOrderByProjectDesc();
}
