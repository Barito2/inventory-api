package com.enigma.api.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		Application application = new Application();
		assertNotEquals(application, null);
	}

}
