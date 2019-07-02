package com.adaming.crowdBackend.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contributor implements Comparable<Contributor>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nickname;
	private String avatar; 		//url de l'avatar

	@OneToMany(mappedBy="contributor")
	@JsonIgnore
	private Set<Donation> donations = new HashSet<Donation>();
	
	@JsonIgnore
	@OneToMany(mappedBy="creator")
	private Set<Project> projects = new HashSet<Project>(); // les projets soumis par l'utilisateur
	
	private Double total; // total des donations. A la base je pensais en faire juste une méthode vu que c'est calculable à partir des donations.
						  // Au final j'en fais une variable car ça permet l'existence de la fonction findByIdOrderByTotalDesc() sur le service
						  // (qui renvoie donc tous les contributeurs classés par total de donations)
						  // il faudra donc veiller à recalculer cette variable à chaque donation
	
	private Double tempAmount;	// variable temporaire utilisée par le repository pour classer les contributeurs. Elle prend parfois la valeur
								// du total de donations dans la catégorie "Tech", ou dans la catégorie "Nature", etc.
	
	
	
	public Contributor() {}
	
	public Contributor(String nickname) {
		this.nickname = nickname;
	}
	
	
	// Renvoie le montant total de donations par ce contributeur pour la catégorie voulue
	public double amountDonatedForCategory(Category c) {
		double amount = 0.0;
		for (Donation d : donations) {
			if (d.getProject().getCategory() == c) {
				amount += d.getAmount();
			}
		}
		return amount;
	}
	
	
	// Renvoie le montant total de donations par ce contributeur pour le projet voulu
	public double amountDonatedForProject(Project p) {
		double amount = 0.0;
		for (Donation d : donations) {
			if (d.getProject() == p) {
				amount += d.getAmount();
			}
		}
		return amount;
	}
	
	// Renvoie le montant total de donations par ce contributeur
	public double amountDonated() {
		double amount = 0.0;
		for (Donation d : donations) {
			amount += d.getAmount();
		}
		return amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTempAmount() {
		return tempAmount;
	}

	public void setTempAmount(Double tempAmount) {
		this.tempAmount = tempAmount;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}


	@Override
	public int compareTo(Contributor c) {
		return this.getNickname().compareTo(c.getNickname());
	}
	
	
}
