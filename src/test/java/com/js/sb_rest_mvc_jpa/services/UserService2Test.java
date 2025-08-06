package com.js.sb_rest_mvc_jpa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.js.sb_rest_mvc_jpa.repository.User;
import com.js.sb_rest_mvc_jpa.repository.UserRepository;

public class UserService2Test {
	@Mock
	UserRepository repository;
	
	@InjectMocks
	UserService service;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetUserName() {
		Optional<User> user = Optional.of(new User(1L,"Sai"));
		when(repository.findById(1L)).thenReturn(user);
		
		String name = service.getUserName(1L);
		
		assertEquals("Sai",name);
		verify(repository).findById(1L);
	}	
	
	@Test
	void testFindByEmail() {
		when(repository.findByEmail(anyString())).thenReturn(new User(1L,"Sai"));
		
		User u = service.findByEmail("abc@gmail.com");
		u = service.findByEmail("def@gmail.com");
		
		assertEquals("Sai",u.getName());
		verify(repository).findByEmail("abc@gmail.com");
		verify(repository).findByEmail("def@gmail.com");
	}
	
	@Test
	void testGetUserName_withNegativeId_throwsException() {
		when(repository.findById(-1L)).thenThrow(new IllegalArgumentException("id cannot be <= 0"));
		Exception ex = assertThrows(IllegalArgumentException.class, ()->{
			service.getUserName(-1L);
		});
		assertEquals("id cannot be <= 0", ex.getMessage());
	}
	@Test
	void testGetUserName_withZeroId_throwsException() {
		when(repository.findById(0L)).thenThrow(new IllegalArgumentException("id cannot be <= 0"));
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			service.getUserName(0L);
		});
		assertEquals("id cannot be <= 0",ex.getMessage());
	}
}

