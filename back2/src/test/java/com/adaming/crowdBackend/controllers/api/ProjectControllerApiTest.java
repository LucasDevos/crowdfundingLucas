package com.adaming.crowdBackend.controllers.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.adaming.crowdBackend.models.Category;
import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.services.ProjectService;



class ProjectControllerApiTest {
	
	@InjectMocks
	ProjectControllerApi api;
	
	@Mock
	ProjectService ps;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGettersSetters() {
		Project proj = new Project();
		proj.setId(Long.valueOf(1));
		proj.setTitle("Mon projet");
		proj.setPitch("un pitch");
		proj.setDescription("une description");
		proj.setCategory(new Category());
		proj.setCreator(new Contributor());
		proj.setDonationCurrent(0.0);
		proj.setDonationGoal(10000.0);
		proj.setEndDate(LocalDate.of(2019, 7, 1));
		proj.setThumbnailPath("urlVersLimage");
		proj.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		proj.getId();
		proj.getTitle();
		proj.getPitch();
		proj.getDescription();
		proj.getCategory();
		proj.getCreator();
		proj.getDonationCurrent();
		proj.getDonationGoal();
		proj.getEndDate();
		proj.getSubmissionDate();
		proj.getThumbnailPath();
	}

	@Test
	void testFindById(){
		Project proj = new Project();
		proj.setId(Long.valueOf(1));
		proj.setTitle("Mon projet");
		when(ps.findById(Long.valueOf(1))).thenReturn(proj);
		
		Project expected = api.findById(Long.valueOf(1));
		assertThat(expected).isNotNull();
		assertThat(proj).isEqualTo(expected);
	}
	
	@Test
	void testHomeProjects() {
		Project p1 = new Project();
		p1.setId(Long.valueOf(1));
		p1.setTitle("Mon projet");
		p1.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		Project p2 = new Project();
		p2.setId(Long.valueOf(2));
		p2.setTitle("Mon autre projet");
		p2.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 02), LocalTime.of(0, 0)));
		List<Project> list = new ArrayList<Project>();
		list.add(p2); list.add(p1);
		when(ps.findTop8ByOrderBySubmissionDateDesc()).thenReturn(list);
		
		List<Project> expected = api.homeProjects();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllProjects() {
		Project p1 = new Project();
		p1.setId(Long.valueOf(1));
		p1.setTitle("Mon projet");
		p1.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		Project p2 = new Project();
		p2.setId(Long.valueOf(2));
		p2.setTitle("Mon autre projet");
		p2.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 02), LocalTime.of(0, 0)));
		List<Project> list = new ArrayList<Project>();
		list.add(p1); list.add(p2);
		when(ps.findAll()).thenReturn(list);
		
		List<Project> expected = api.allProjects();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testGetUserProjects() {
		Project p1 = new Project();
		p1.setId(Long.valueOf(1));
		p1.setTitle("Mon projet");
		p1.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		Project p2 = new Project();
		p2.setId(Long.valueOf(2));
		p2.setTitle("Mon autre projet");
		p2.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 02), LocalTime.of(0, 0)));
		List<Project> list = new ArrayList<Project>();
		list.add(p1); list.add(p2);
		when(ps.getUserProjects(Long.valueOf(1))).thenReturn(list);
		
		List<Project> expected = api.getUserProjects(Long.valueOf(1));
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testGetUserBackedProjects() {
		Project p1 = new Project();
		p1.setId(Long.valueOf(1));
		p1.setTitle("Mon projet");
		p1.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		Project p2 = new Project();
		p2.setId(Long.valueOf(2));
		p2.setTitle("Mon autre projet");
		p2.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 02), LocalTime.of(0, 0)));
		List<Project> list = new ArrayList<Project>();
		list.add(p1); list.add(p2);
		when(ps.getUserBackedProjects(Long.valueOf(1))).thenReturn(list);
		
		List<Project> expected = api.getUserBackedProjects(Long.valueOf(1));
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testSaveProject() {
		Project p1 = new Project();
		p1.setId(Long.valueOf(1));
		p1.setTitle("Mon projet");
		p1.setSubmissionDate(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		when(ps.save(p1)).thenReturn(p1);
		
		Project expected = api.saveProject(p1);
		assertThat(expected).isNotNull();
		assertThat(p1).isEqualTo(expected);
	}
}
