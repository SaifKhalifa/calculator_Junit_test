package Test;

import Code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("User Service Test Suite")
public class UserServiceTest {
	private UserService service;

	@BeforeEach
	void init() {
		service = new UserService();
	}

	@Test
	@DisplayName("Valid authentication test")
	void testValidAuth() {
		assertTrue(service.authenticate("admin", "1234"));
	}

	@Test
	@DisplayName("Invalid authentication test")
	void testInvalidAuth() {
		assertFalse(service.authenticate("user", "wrong"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"test@example.com", "a@b.co", "user123@mail.org"})
	@DisplayName("Test valid email formats")
	void testValidEmails(String email) {
		assertTrue(service.isValidEmail(email));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "hello", "abc@", "null"})
	@DisplayName("Test invalid email formats")
	void testInvalidEmails(String email) {
		assertFalse(service.isValidEmail(email));
	}

	@Test
	@Timeout(1)
	@DisplayName("Authentication timeout test")
	void testAuthTimeout() {
		assertTrue(service.authenticate("admin", "1234"));
	}
}
