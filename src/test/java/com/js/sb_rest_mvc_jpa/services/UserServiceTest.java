package com.js.sb_rest_mvc_jpa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.js.sb_rest_mvc_jpa.repository.User;
import com.js.sb_rest_mvc_jpa.repository.UserRepository;

public class UserServiceTest {
	@Test
	void testGetUserName() {
		//Create a mock and define behavior
		UserRepository mockRepo = mock(UserRepository.class);
		Optional<User> user = Optional.of(new User(1L,"Sai"));
		when(mockRepo.findById(1L)).thenReturn(user);
		
		//Inject mock into service and call method
		UserService service = new UserService(mockRepo);
		String name = service.getUserName(1L);
		
		//Verify the result
		assertEquals("Sai",name);
		
		//Interaction with mock
		verify(mockRepo).findById(1L);
	}
}
