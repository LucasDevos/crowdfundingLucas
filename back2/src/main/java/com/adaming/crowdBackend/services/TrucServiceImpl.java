package com.adaming.crowdBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adaming.crowdBackend.models.Truc;
import com.adaming.crowdBackend.repositories.TrucRepository;

@Service
public class TrucServiceImpl implements TrucService {

	@Autowired
	private TrucRepository tr;
	
	@Override
	public Truc findById(Long id) {
		return tr.findById(id).get();
	}

	@Override
	public List<Truc> findAll() {
		return tr.findAll();
	}

	@Override
	public void deleteById(Long id) {
		tr.deleteById(id);
		
	}

	@Override
	public Truc save(Truc d) {
		return tr.save(d);
	}
	
	

}
