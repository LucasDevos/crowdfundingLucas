package com.adaming.crowdBackend.services;

import java.util.List;

import com.adaming.crowdBackend.models.Truc;

public interface TrucService {
	public Truc findById(Long id);
	public List<Truc> findAll();
	public void deleteById(Long id);
	public Truc save(Truc d);
}
