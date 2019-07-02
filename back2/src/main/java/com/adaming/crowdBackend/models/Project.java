package com.adaming.crowdBackend.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Comparable<Project>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String pitch;
	private String description;
	private String thumbnailPath;
	private double donationGoal;
	private double donationCurrent;
	private LocalDateTime submissionDate;
	private LocalDate endDate;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy="project")
	private Set<Donation> donations = new HashSet<Donation>();
	
	@OneToMany(mappedBy="project")
	private List<ProjectUpdate> updates = new ArrayList<ProjectUpdate>();
	
	@ManyToOne//(cascade = CascadeType.ALL)
	private Contributor creator;
	
	public Project() {}
	
	// TODO : RENAME this method, or it will be considered as a getter and the value is added in the JSON
	// (...maybe it can actually be a good thing ???)
	/*public double getPercentage() {
		return (donationCurrent / donationGoal * 100.0);
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPitch() {
		return pitch;
	}

	public void setPitch(String pitch) {
		this.pitch = pitch;
	}

	public double getDonationGoal() {
		return donationGoal;
	}

	public void setDonationGoal(double donationGoal) {
		this.donationGoal = donationGoal;
	}

	public double getDonationCurrent() {
		return donationCurrent;
	}

	public void setDonationCurrent(double donationCurrent) {
		this.donationCurrent = donationCurrent;
	}

	public LocalDateTime getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDateTime submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public List<ProjectUpdate> getUpdates() {
		return updates;
	}

	public void setUpdates(List<ProjectUpdate> updates) {
		this.updates = updates;
	}

	public Contributor getCreator() {
		return creator;
	}

	public void setCreator(Contributor creator) {
		this.creator = creator;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int compareTo(Project o) {
		int ret = this.getTitle().compareTo(o.getTitle());
		System.out.println("projet "+this.title+" comparé au projet "+o.title+" : le resultat est "+ret);
		return ret;
	}

	
	
	
	
	
}
