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

import com.adaming.crowdBackend.models.Category;
import com.adaming.crowdBackend.services.CategoryService;

class CategoryControllerApiTest {
	
	@InjectMocks
	CategoryControllerApi api;
	
	@Mock
	CategoryService cats;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAllCategories(){
		List<Category> list = new ArrayList<Category>();
		Category c1 = new Category();
		c1.setId(Long.valueOf(1));
		c1.setName("Nature");
		c1.setDescription("description nature");
		c1.setColor("#00ff00");
		Category c2 = new Category();
		c1.setId(Long.valueOf(2));
		c1.setName("Tech");
		c1.setDescription("description tech");
		c1.setColor("#ff0000");
		list.add(c1); list.add(c2);
		when(cats.findAll()).thenReturn(list);
		
		List<Category> expected = api.allCategories();
		assertThat(expected).isNotNull();
		assertThat(list).isEqualTo(expected);
	}
	
	@Test
	void testSave() {
		Category c1 = new Category();
		c1.setId(Long.valueOf(1));
		c1.setName("Nature");
		c1.setDescription("description nature");
		c1.setColor("#00ff00");
		when(cats.save(c1)).thenReturn(c1);
		
		Category expected = api.save(c1);
		assertThat(expected).isNotNull();
		assertThat(c1).isEqualTo(expected);
	}

}
