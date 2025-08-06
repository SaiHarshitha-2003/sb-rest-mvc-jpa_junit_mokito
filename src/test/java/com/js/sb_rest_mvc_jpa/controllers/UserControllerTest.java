package com.js.sb_rest_mvc_jpa.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.js.sb_rest_mvc_jpa.services.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	void testGetUserName() throws Exception{
		String name = "Mocked John";
		Mockito.when(userService.getUserName(1L)).thenReturn(name);
		
		mockMvc.perform(get("/users/id/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(name));
	}
}
