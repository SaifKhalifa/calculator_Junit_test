package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Product Test Suite")
public class ProductTest
{
	Product product;

	@BeforeEach
	void setUp() throws Exception {
		product = new Product("test product", 4.00);
	}
	@BeforeAll
	void beforeAll() {
		System.out.println("Starting Product Tests...");
	}

	@BeforeEach
	void setup() {
		System.out.println("Setting up a new test case");
	}

	@Test
	@DisplayName("Test valid product creation")
	void testValidProductCreation() {
		Product product = new Product("Laptop", 1000);
		assertAll("Product Properties",
				() -> assertEquals("Laptop", product.getName()),
				() -> assertEquals(1000, product.getPrice())
		);
	}

	@Test
	@DisplayName("Test product creation with invalid price")
	void testInvalidPrice() {
		assertThrows(IllegalArgumentException.class, () -> new Product("Laptop", -100));
	}

	@ParameterizedTest
	@CsvSource({"10,900", "20,800", "50,500"})
	@DisplayName("Test discount application")
	void testApplyDiscount(double discount, double expectedPrice) {
		Product p = new Product("TV", 1000);
		p.applyDiscount(discount);
		assertEquals(expectedPrice, p.getFinalPrice(), 0.01);
	}

	@Test
	@DisplayName("Test invalid discount throws exception")
	void testInvalidDiscount() {
		Product p = new Product("TV", 1000);
		assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60));
	}

	@Test
	@Timeout(1)
	@DisplayName("Test final price calculation timeout")
	void testFinalPriceTimeout() {
		Product p = new Product("Phone", 500);
		p.applyDiscount(10);
		assertEquals(450, p.getFinalPrice(), 0.01);
	}

	@AfterEach
	void teardown() {
		System.out.println("Test case finished");
	}

	@AfterAll
	void afterAll() {
		System.out.println("All Product Tests Completed.");
	}
}
