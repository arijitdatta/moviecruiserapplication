package com.stackroute.moviecruiserauthentication.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserauthentication.model.User;
import com.stackroute.moviecruiserauthentication.services.SecurityTokenGenerator;
import com.stackroute.moviecruiserauthentication.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SecurityTokenGenerator tokenGenerator;
	
	private User user;
	
	@InjectMocks
	UserController userController;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		user = new User("ArDa1384", "Arijit", "Datta", "123456", new Date());
	}
	
	@Test
	public void testRegisterUser() throws Exception{
		when(userService.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated()).andDo(print());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}

	
	private static String jsonToString(final Object obj) {
		String result;
		try {
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result=jsonContent;
		}
		catch(JsonProcessingException e) {
			result="Json processing exception";
		}
		return result;
	}

	@Test
	public void testLoginUser() throws Exception{
		String userId="ArDa1384";
		String password="123456";
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isOk());
		verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
		
	}
	
	

}
