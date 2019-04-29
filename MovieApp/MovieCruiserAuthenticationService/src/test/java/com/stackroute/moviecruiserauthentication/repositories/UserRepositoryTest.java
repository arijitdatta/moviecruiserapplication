package com.stackroute.moviecruiserauthentication.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserauthentication.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	

	@Before
	public void setUp() throws Exception {
		user = new User("John", "John Jenny", "Bob", "123456", new Date());
	}

	@Test
	public void testRegisterUserSuccess() {
		userRepository.save(user);
		Optional<User> userSearched = userRepository.findById(user.getUserId());
		assertThat(userSearched.equals(user));
	}

	
	
	

}
