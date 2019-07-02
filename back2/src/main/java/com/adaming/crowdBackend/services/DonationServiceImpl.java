package com.adaming.crowdBackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.adaming.crowdBackend.enums.DonationTypeEnum;
import com.adaming.crowdBackend.models.Donation;
import com.adaming.crowdBackend.repositories.DonationRepository;

@Service
public class DonationServiceImpl implements DonationService{

	@Autowired
	DonationRepository dr;
	
	@Override
	public Donation findById(Long id) {
		return dr.findById(id).get();
	}

	@Override
	public List<Donation> findAll() {
		return dr.findAll();
	}

	@Override
	public void deleteById(Long id) {
		dr.deleteById(id);
	}

	@Override
	public Donation save(Donation d) {
		return dr.save(d);
	}

	@Override
	public List<Donation> findAllByOrderByIdAsc() {
		return dr.findAllByOrderByIdAsc();
	}

	@Override
	public List<Donation> findAllByOrderByIdDesc() {
		return dr.findAllByOrderByIdDesc();
	}

	@Override
	public List<Donation> findAllByOrderByDateTimeAsc() {
		return dr.findAllByOrderByDateTimeAsc();
	}

	@Override
	public List<Donation> findAllByOrderByDateTimeDesc() {
		return dr.findAllByOrderByDateTimeDesc();
	}

	@Override
	public List<Donation> findAllByOrderByAmountAsc() {
		return dr.findAllByOrderByAmountAsc();
	}

	@Override
	public List<Donation> findAllByOrderByAmountDesc() {
		return dr.findAllByOrderByAmountDesc();
	}

	@Override
	public List<Donation> findAllByOrderByContributorAsc() {
		return dr.findAllByOrderByContributorAsc();
	}

	@Override
	public List<Donation> findAllByOrderByContributorDesc() {
		return dr.findAllByOrderByContributorDesc();
	}

	@Override
	public List<Donation> findAllByOrderByProjectAsc() {
		return dr.findAllByOrderByProjectAsc();
		
	}

	@Override
	public List<Donation> findAllByOrderByProjectDesc() {
		return dr.findAllByOrderByProjectDesc();
	}
	
	@Override
	public List<Donation> findAllByOrderByDonationTypeAsc() {
		return dr.findAllByOrderByDonationTypeAsc();
	}

	@Override
	public List<Donation> findAllByOrderByDonationTypeDesc() {
		return dr.findAllByOrderByDonationTypeDesc();
	}

	@Override
	public List<String> getDonationTypes() {
		List<String> donationTypes = new ArrayList<String>();
		for (DonationTypeEnum d : DonationTypeEnum.values()) {
			donationTypes.add(d.toString());
		}
		return donationTypes;
	}

	
	
	

}
