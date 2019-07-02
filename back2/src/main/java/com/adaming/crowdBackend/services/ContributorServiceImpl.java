package com.adaming.crowdBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adaming.crowdBackend.models.Category;
import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.repositories.CategoryRepository;
import com.adaming.crowdBackend.repositories.ContributorRepository;
import com.adaming.crowdBackend.repositories.ProjectRepository;

@Service
public class ContributorServiceImpl implements ContributorService{

	@Autowired
	private ContributorRepository cr;
	@Autowired
	private CategoryRepository catr;
	@Autowired
	private ProjectRepository pr;
	
	@Override
	public Contributor findById(Long id) {
		return cr.findById(id).get();
	}

	@Override
	public List<Contributor> findAll() {
		return cr.findAll();
	}

	@Override
	public void deleteById(Long id) {
		cr.deleteById(id);
	}

	@Override
	public Contributor save(Contributor c) {
		return cr.save(c);
	}

	@Override
	public List<Contributor> findAllByDonations() {
		return cr.findAllByOrderByTotalDesc();
	}
	
	@Override
	public List<Contributor> findTop5ByDonations() {
		return cr.findTop5ByTotalNotOrderByTotalDesc(0.0);
	}

	@Override
	public Contributor maxDonation() {
		return cr.findFirstByOrderByTotalDesc();
	}
	
	@Override
	public List<Contributor> findAllByNickname() {
		return cr.findAllByOrderByNicknameAsc();
	}

	@Override
	public List<Contributor> findAllByDonationsInCategory(Long category_id) {
		List<Contributor> contributors = cr.findAll();
		Category cat = catr.findById(category_id).get();
		for (Contributor c : contributors) {
			double val = c.amountDonatedForCategory(cat);
			c.setTempAmount(val);
			save(c); // <-- comme on vient de changer la valeur d'un champ du Contributeur, celui-ci DOIT être mis à jour sur la DB pour que la requête finale fonctionne
			System.out.println("tempAmount devient "+val+" pour le contributueur "+c.getNickname());
		}
		return cr.findTop5ByTempAmountNotOrderByTempAmountDesc(0.0);
	}
	
	@Override
	public List<Contributor> findAllByDonationsInProject(Long project_id) {
		List<Contributor> contributors = cr.findAll();
		Project p = pr.findById(project_id).get();
		for (Contributor c : contributors) {
			double val = c.amountDonatedForProject(p);
			c.setTempAmount(val);
			save(c); // <-- comme on vient de changer la valeur d'un champ du Contributeur, celui-ci DOIT être mis à jour sur la DB pour que la requête finale fonctionne
		}
		return cr.findAllByTempAmountNotOrderByTempAmountDesc(0.0);
	}

	@Override
	public Contributor findByNickname(String name) {
		System.out.println("on recherche le contributeur "+name);
		return cr.findByNickname(name);
	}

	/*@Override
	public Double AmountByContributorForCategory(int contributor_id, int category_id) {
		return cr.AmountByContributorForCategory(contributor_id, category_id);
	}*/

}
