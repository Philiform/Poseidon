package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.utilities.Utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceTest.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	/** The service. */
	@InjectMocks
	private UserService service;

	/** The user connection repository. */
	@Mock
	private UserRepository repository;

	/** The utilities. */
	@Mock
	private Utilities utilities;

	/** The encoder. */
	@Mock
	public PasswordEncoder encoder;

	/** The list user. */
	List<User> listUser = new ArrayList<>();

	/** The user 1. */
	private User user1;

	/** The user 2. */
	private User user2;

	/** The saved user. */
	private User savedUser;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	private

	@BeforeEach
	void setUp() throws Exception {
		user1 = new User();
		user1.setId(1);
		user1.setFullname("Admin");
		user1.setUsername("admin");
		user1.setPassword("11aaAA&&");
		user1.setRole("ADMIN");

		user2 = new User();

		listUser.add(user1);
		listUser.add(user2);

		savedUser = new User();
		savedUser.setId(1);
		savedUser.setFullname("Admin");
		savedUser.setUsername("admin");
		savedUser.setPassword("11aaAA&&");
		savedUser.setRole("ADMIN");

	}

	/**
	 * Test when get user list then return list user not empty.
	 */
	@Test
	void test_WhenGetUserList_ThenReturnListUserNotEmpty() {
		given(repository.findAll()).willReturn(listUser);

		List<User> response = service.getUserList();

		verify(repository, times(1)).findAll();
		assertThat(response).isEqualTo(listUser);
	}

	/**
	 * Test given good new user when save user then return saved user.
	 */
	@Test
	void test_GivenGoodNewUser_WhenSaveUser_ThenReturnSavedUser() {
		given(utilities.isValidPassword(anyString())).willReturn(true);
		given(encoder.encode(any())).willReturn(user1.getPassword());
		given(repository.save(any())).willReturn(user1);

		Optional<User> response = service.saveUser(user1);

		verify(utilities, times(1)).isValidPassword(anyString());
		verify(encoder, times(1)).encode(any());
		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(user1));
	}

	/**
	 * Test given bad new user when save user then return optional empty.
	 */
	@Test
	void test_GivenBadNewUser_WhenSaveUser_ThenReturnOptionalEmpty() {
		given(utilities.isValidPassword(anyString())).willReturn(false);

		Optional<User> response = service.saveUser(user1);

		verify(utilities, times(1)).isValidPassword(anyString());
		verify(repository, times(0)).save(any());
		assertThat(response).isEqualTo(Optional.empty());
	}

	/**
	 * Test given good user id when get user for update then return user.
	 */
	@Test
	void test_GivenGoodUserId_WhenGetUserForUpdate_ThenReturnUser() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(user1));

		User response = service.getUserForUpdate(1);

		verify(repository, times(1)).findById(any(Integer.class));
		assertThat(response).isEqualTo(user1);
	}

	/**
	 * Test given bad user id when get user for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUserId_WhenGetUserForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getUserForUpdate(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update user when update user then return updated user.
	 */
	@Test
	void test_GivenGoodUpdateUser_WhenUpdateUser_ThenReturnUpdatedUser() {
		given(utilities.isValidPassword(anyString())).willReturn(true);
		given(repository.save(any())).willReturn(user1);

		Optional<User> response = service.updateUser(1, user1);

		verify(utilities, times(1)).isValidPassword(anyString());
		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(user1));
	}

	/**
	 * Test given bad update user when update user then return optional empty.
	 */
	@Test
	void test_GivenBadUpdateUser_WhenUpdateUser_ThenReturnOptionalEmpty() {
		given(utilities.isValidPassword(anyString())).willReturn(false);
		user1.setPassword("");

		Optional<User> response = service.updateUser(1, user1);

		verify(utilities, times(1)).isValidPassword(anyString());
		verify(repository, times(0)).save(any());
		assertThat(response).isEqualTo(Optional.empty());
	}

	/**
	 * Test given good user id when delere user then delete user.
	 */
	@Test
	void test_GivenGoodUserId_WhenDelereUser_ThenDeleteUser() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(user1));

		service.deleteUser(1);

		verify(repository, times(1)).findById(any(Integer.class));
		verify(repository, times(1)).delete(any(User.class));
	}

	/**
	 * Test given bad user id when delere user then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUserId_WhenDelereUser_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteUser(10));
		verify(repository, times(1)).findById(any(Integer.class));
		verify(repository, times(0)).delete(any(User.class));
	}

	/**
	 * Test given good user id when is username exist then return true.
	 */
	@Test
	void test_GivenGoodUserId_WhenIsUsernameExist_ThenReturnTrue() {
		given(repository.findByUsername(anyString())).willReturn(Optional.of(user1));

		boolean response = service.isUsernameExist(user1.getUsername());

		assertThat(response).isTrue();
		verify(repository, times(1)).findByUsername(anyString());
	}

	/**
	 * Test given bad user id when is username exist then return false.
	 */
	@Test
	void test_GivenBadUserId_WhenIsUsernameExist_ThenReturnFalse() {
		given(repository.findByUsername(anyString())).willReturn(Optional.empty());

		boolean response = service.isUsernameExist(user1.getUsername());

		assertThat(response).isFalse();
		verify(repository, times(1)).findByUsername(anyString());
	}

}
