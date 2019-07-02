package com.adaming.crowdBackend.controllers.api;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.services.ContributorService;

@SpringBootTest
public class ContributorControllerApiTest {

	//@Autowired   //Autowired doesn't work here :(
	
	@InjectMocks
	ContributorControllerApi api;
	
	@Mock
	ContributorService cs;
	
	/*@Before
	public void setUp() throws Exception {
	}*/
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//api = new ContributorControllerApi();
		//cs = new ContributorServiceImpl();
	}
	
	@Test
	void testGettersSetters() {
		Contributor c1 = new Contributor();
		c1.setAvatar("url");
		c1.setId(Long.valueOf(1));
		c1.setNickname("paul");
		c1.setTempAmount(0.0);
		c1.setTotal(0.0);
		c1.getAvatar();
		c1.getId();
		c1.getNickname();
		c1.getTempAmount();
		c1.getTotal();
	}

	@Test
	public void testFindUser() {
		Contributor c1 = new Contributor();
		c1.setNickname("paul");
		when(cs.findByNickname("paul")).thenReturn(c1);
	
		Contributor expected = api.findUser("paul");
		assertThat(expected).isNotNull();
		assertThat(c1).isEqualTo(expected);
	}
	
	@Test
	public void testAllContributors() {
		Contributor c1 = new Contributor();
		c1.setNickname("paul");
		Contributor c2 = new Contributor();
		c2.setNickname("alice");
		List<Contributor> list = new ArrayList<Contributor>();
		list.add(c1); list.add(c2);
		when(cs.findAll()).thenReturn(list);
	
		List<Contributor> expected = api.allContributors();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	public void testAllContributorsByNickname() {
		Contributor c1 = new Contributor();
		c1.setNickname("paul");
		Contributor c2 = new Contributor();
		c2.setNickname("alice");
		List<Contributor> list = new ArrayList<Contributor>();
		list.add(c2); list.add(c1);
		when(cs.findAllByNickname()).thenReturn(list);
		
		List<Contributor> expected = api.allContributorsByNickname();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	

}
