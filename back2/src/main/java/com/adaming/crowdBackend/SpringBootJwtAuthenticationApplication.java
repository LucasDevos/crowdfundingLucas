package com.adaming.crowdBackend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.adaming.crowdBackend.enums.DonationTypeEnum;
import com.adaming.crowdBackend.model.Role;
import com.adaming.crowdBackend.model.RoleName;
import com.adaming.crowdBackend.model.User;
import com.adaming.crowdBackend.repository.RoleRepository;
import com.adaming.crowdBackend.repository.UserRepository;

@SpringBootApplication
public class SpringBootJwtAuthenticationApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAuthenticationApplication.class, args);
		System.out.println("serveur lancé");
		/*List<String> donationTypes = new ArrayList<String>();
		for (DonationTypeEnum d : DonationTypeEnum.values()) {
			donationTypes.add(d.toString());
		}
		System.out.println("donationTypes[0]="+donationTypes.get(0)+", donationTypes[1]="+donationTypes.get(1));*/
	}
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User();
		u1.setEmail("paul@adaming.com");
		u1.setName("Paul");
		u1.setPassword(encoder.encode("Password1"));
		u1.setUsername("paul");
		createUser(u1, RoleName.ROLE_ADMIN);
		
		User u2 = new User();
		u2.setEmail("alice@adaming.com");
		u2.setName("Alice");
		u2.setPassword(encoder.encode("Password1"));
		u2.setUsername("alice");
		createUser(u2, RoleName.ROLE_PM);
		
		
	}
	private User createUser(User user, RoleName roleName) {
		if(userRepository.existsByUsername(user.getUsername())) {
			System.out.println(user.getUsername() + " already exists. Nothing to do");
		}else {
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(roleName).get();
			roles.add(role);
			user.setRoles(roles);
			user = userRepository.save(user);
		}
		return user;
	}
	
}
