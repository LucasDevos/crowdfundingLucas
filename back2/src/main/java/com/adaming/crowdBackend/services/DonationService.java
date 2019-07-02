package com.adaming.crowdBackend.services;

import java.util.List;

import com.adaming.crowdBackend.models.Donation;

public interface DonationService {
	public Donation findById(Long id);
	public List<Donation> findAll();
	public void deleteById(Long id);
	public Donation save(Donation d);
	
	public List<String> getDonationTypes();
	
	public List<Donation> findAllByOrderByIdAsc();
	public List<Donation> findAllByOrderByIdDesc();
	public List<Donation> findAllByOrderByDateTimeAsc();
	public List<Donation> findAllByOrderByDateTimeDesc();
	public List<Donation> findAllByOrderByAmountAsc();
	public List<Donation> findAllByOrderByAmountDesc();
	public List<Donation> findAllByOrderByContributorAsc();
	public List<Donation> findAllByOrderByContributorDesc();
	public List<Donation> findAllByOrderByProjectAsc();
	public List<Donation> findAllByOrderByProjectDesc();
	public List<Donation> findAllByOrderByDonationTypeAsc();
	public List<Donation> findAllByOrderByDonationTypeDesc();
}
