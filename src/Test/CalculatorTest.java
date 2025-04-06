package Test;

import static org.junit.jupiter.api.Assertions.*;

import Code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Calculator Test Suite")
public class CalculatorTest {
	
    Calculator calculator;

	@BeforeEach
	void setUp() throws Exception {
		calculator = new Calculator();
	}

	@Test
	@Order(1)
	@DisplayName("Test addition with multiple numbers")
	void testAddition() {
		int result = calculator.add(1, 2, 3);
		assertEquals(6, result, "Sum should be 6");
	}

	@Test
	@Order(2)
	@DisplayName("Test divide by zero throws exception")
	void testDivideByZero() {
		assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0), "Divide by zero should throw");
	}

	@Test
	@Order(3)
	@DisplayName("Test factorial of positive number")
	void testFactorial() {
		assertEquals(120, calculator.factorial(5));
		assertTrue(calculator.factorial(3) > 0);
	}

	@ParameterizedTest
	@Order(4)
	@ValueSource(ints = {0, 1, 2, 3})
	@DisplayName("Test factorial with parameterized input")
	void testFactorialParameterized(int n) {
		assertDoesNotThrow(() -> calculator.factorial(n));
	}

	@Test
	@Order(5)
	@Timeout(1)
	@DisplayName("Test factorial performance")
	void testFactorialTimeout() {
		assertEquals(3628800, calculator.factorial(10));
	}

	@Test
	@Disabled("Intentionally failing test - factorial of negative should throw")
	@DisplayName("Disabled test for negative factorial")
	void failingTest() {
		assertEquals(1, calculator.factorial(-1)); // This will fail
	}

}
