package com.nnk.springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
//import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.utilities.Utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class UserControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

	/** The controller. */
	@Autowired
	private UserController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public UserService service;

	/** The utilities. */
	@MockBean
	public Utilities utilities;

	/** The user 1. */
	private static User user1;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		user1 = new User();
		user1.setId(1);
		user1.setFullname("User");
		user1.setUsername("user");
		user1.setPassword("11aaAA&&");
		user1.setRole("USER");

	}

	/**
	 * Test given controller then return not null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenController_ThenReturnNotNull() throws Exception {
		assertThat(controller).isNotNull();
	}

	/**
	 * Test given user when get user list then return html page user list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	public void test_GivenUser_WhenGetUserList_ThenReturnHtmlPageUserList() throws Exception {
		mockMvc
			.perform(get("/user/list"))
			.andExpect(model().attributeExists("userList"))
			.andExpect(view().name("user/list"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(utilities, times(1)).getUserLogged();
		verify(service, times(1)).getUserList();
	}

	/**
	 * Test when get user add then return html page user add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetUserAdd_ThenReturnHtmlPageUserAdd() throws Exception {
		mockMvc
			.perform(get("/user/add"))
			.andExpect(view().name("user/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test when get user validate then return html page user list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetUserValidate_ThenReturnHtmlPageUserList() throws Exception {
		given(service.isUsernameExist(anyString())).willReturn(false);
		given(service.saveUser(any())).willReturn(Optional.of(user1));

		mockMvc
			.perform(post("/user/validate")
				.flashAttr("user", user1))
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).isUsernameExist(anyString());
		verify(service, times(1)).saveUser(any());
		verify(service, times(1)).getUserList();
	}

	/**
	 * Test given username already exists when get user validate then return html page user add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenUsernameAlreadyExists_WhenGetUserValidate_ThenReturnHtmlPageUserAdd() throws Exception {
		given(service.isUsernameExist(anyString())).willReturn(true);

		mockMvc
			.perform(post("/user/validate")
				.flashAttr("user", user1))
			.andExpect(view().name("user/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).isUsernameExist(anyString());
		verify(service, times(0)).saveUser(any());
		verify(service, times(0)).getUserList();
	}

	/**
	 * Test when get user validate then return html page user add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenHasErrors_WhenGetUserValidate_ThenReturnHtmlPageUserAdd() throws Exception {
		mockMvc
			.perform(post("/user/validate"))
			.andExpect(model().hasErrors())
			.andExpect(view().name("user/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given id when get user update then return html page user update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetUserUpdate_ThenReturnHtmlPageUserUpdate() throws Exception {
		given(service.getUserForUpdate(any(Integer.class))).willReturn(user1);

		mockMvc
			.perform(get("/user/update/{id}", 1))
			.andExpect(model().attributeExists("user"))
			.andExpect(view().name("user/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getUserForUpdate(any(Integer.class));
	}

	/**
	 * Test given bad id when get user update then redirect to html page user list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetUserUpdate_ThenRedirectToHtmlPageUserList() throws Exception {
		given(service.getUserForUpdate(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/user/update/{id}", 1))
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).getUserForUpdate(any(Integer.class));
	}

	/**
	 * Test given good user when get user update then redirect to html page user list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenGoodUser_WhenGetUserUpdate_ThenRedirectToHtmlPageUserList() throws Exception {
		given(service.updateUser(any(Integer.class), any())).willReturn(Optional.of(user1));

		mockMvc
			.perform(post("/user/update/{id}", 1)
				.flashAttr("user", user1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateUser(any(Integer.class), any());
		verify(service, times(1)).getUserList();
	}

	/**
	 * Test given empty user when get user update then return html page user update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenEmptyUser_WhenGetUserUpdate_ThenReturnHtmlPageUserUpdate() throws Exception {
		mockMvc
			.perform(post("/user/update/{id}", 1)
				.flashAttr("user", new User()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("user/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(0)).updateUser(any(Integer.class), any());
		verify(service, times(0)).getUserList();
	}

	/**
	 * Test given illegal argument exception when get user update then return html page user update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetUserUpdate_ThenReturnHtmlPageUserUpdate() throws Exception {
		given(service.updateUser(any(Integer.class), any())).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(post("/user/update/{id}", 1)
				.flashAttr("user", user1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("user/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).updateUser(any(Integer.class), any());
		verify(service, times(0)).getUserList();
	}

	/**
	 * Test given id when get user delete then return html page deleted user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetUserDelete_ThenReturnHtmlPageDeletedUser() throws Exception {
		mockMvc
			.perform(get("/user/delete/{id}", 1))
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteUser(any(Integer.class));
		verify(service, times(1)).getUserList();
	}

	/**
	 * Test given illegal argument exception when get user delete then redirect to html page user list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetUserDelete_ThenRedirectToHtmlPageUserList() throws Exception {
		given(service.deleteUser(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/user/delete/{id}", 1))
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteUser(any(Integer.class));
		verify(service, times(0)).getUserList();
	}

}
