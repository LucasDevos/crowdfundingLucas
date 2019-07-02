package com.adaming.crowdBackend.controllers.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.models.Donation;
import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.services.DonationService;

class DonationControllerApiTest {

	@InjectMocks
	DonationControllerApi api;
	
	@Mock
	DonationService ds;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGettersSetters() {
		Donation d1 = new Donation();
		d1.setAmount(10.0);
		d1.setContributor(new Contributor());
		d1.setDateTime(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		d1.setDonationType("PARTICULIER");
		d1.setId(Long.valueOf(1));
		d1.setProject(new Project());
		d1.getAmount();
		d1.getContributor();
		d1.getDateTime();
		d1.getDonationType();
		d1.getId();
		d1.getProject();
	}
	
	@Test
	void testAllDonations() {
		Donation d1 = new Donation();
		d1.setAmount(10.0);
		Donation d2 = new Donation();
		d2.setAmount(15.0);
		List<Donation> list = new ArrayList<Donation>();
		list.add(d1); list.add(d2);
		when(ds.findAll()).thenReturn(list);
		
		List<Donation> expected = api.allDonations();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	List<Donation> testList(){
		Donation d1 = new Donation();
		d1.setAmount(10.0);
		d1.setId(Long.valueOf(1));
		d1.setContributor(new Contributor("alain"));
		d1.setDateTime(LocalDateTime.of(LocalDate.of(2019, 07, 01), LocalTime.of(0, 0)));
		d1.setDonationType("ENTREPRISE");
		Donation d2 = new Donation();
		d2.setAmount(15.0);
		d2.setId(Long.valueOf(2));
		d2.setContributor(new Contributor("bertrand"));
		d2.setDateTime(LocalDateTime.of(LocalDate.of(2019, 07, 02), LocalTime.of(0, 0)));
		d2.setDonationType("PARTICULIER");
		List<Donation> list = new ArrayList<Donation>();
		list.add(d1); list.add(d2);
		return list;
	}
	
	@Test
	void testAllDonationsByIdAsc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByIdAsc()).thenReturn(list);
		
		List<Donation> expected = api.allDonationsByIdAsc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByIdDesc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByIdDesc()).thenReturn(list);
		Collections.reverse(list);
		
		List<Donation> expected = api.allDonationsByIdDesc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByAmountAsc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByAmountAsc()).thenReturn(list);
		
		List<Donation> expected = api.allDonationsByAmountAsc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByAmountDesc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByAmountDesc()).thenReturn(list);
		Collections.reverse(list);
		
		List<Donation> expected = api.allDonationsByAmountDesc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByDonationTypeAsc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByDonationTypeAsc()).thenReturn(list);
		
		List<Donation> expected = api.allDonationsByTypeAsc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByDonationTypeDesc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByDonationTypeDesc()).thenReturn(list);
		Collections.reverse(list);
		
		List<Donation> expected = api.allDonationsByTypeDesc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByContributorAsc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByContributorAsc()).thenReturn(list);
		
		List<Donation> expected = api.allDonationsByContributorAsc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByContributorDesc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByContributorDesc()).thenReturn(list);
		Collections.reverse(list);
		
		List<Donation> expected = api.allDonationsByContributorDesc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByDateTimeAsc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByDateTimeAsc()).thenReturn(list);
		
		List<Donation> expected = api.allDonationsByDateTimeAsc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testAllDonationsByDateTimeDesc() {
		List<Donation> list = testList();
		when(ds.findAllByOrderByDateTimeDesc()).thenReturn(list);
		Collections.reverse(list);
		
		List<Donation> expected = api.allDonationsByDateTimeDesc();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	

}
