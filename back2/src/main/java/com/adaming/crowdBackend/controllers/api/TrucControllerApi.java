package com.adaming.crowdBackend.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.crowdBackend.models.Truc;
import com.adaming.crowdBackend.services.TrucService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/truc")
public class TrucControllerApi {
	
	@Autowired
	TrucService ts;
	
	@GetMapping(value="/{id}")
	public Truc findById(@PathVariable("id") Long id) {
		return ts.findById(id);
	}
	
	@PostMapping(value="")
	public Truc save(@RequestBody Truc truc) {
		return ts.save(truc);
	}

}
