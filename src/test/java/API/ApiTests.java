package API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.sql.Timestamp;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.path.json.JsonPath;

class ApiTests {
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
	  MockMvc mockMvc;
	  this.mockMvc = MockMvcBuilders.standaloneSetup(new Controller()).build();
	  RestAssuredMockMvc.mockMvc(this.mockMvc);
	}

	@Test
	public static void validateJsonResponseCode() {
		RestAssuredMockMvc.
		given().
		when().
			get("http://localhost:8080/json").
		then().
			assertThat().
				statusCode(200);
	}

	@Test
	public static void validateJsonMessage() {
		RestAssuredMockMvc.
		given().
		when().
			get("http://localhost:8080/json").
		then().
			assertThat().
			body("message",equalTo("Welcome to the machine."));
	}
	
	@Test
	public static void validateJsonTimestamp() {
		double lowerTimeBoundry = System.currentTimeMillis() - 1000;
		double upperTimeBoundry = System.currentTimeMillis() + 1000;
		
		MockMvcResponse response = 
				RestAssuredMockMvc.
				given().
				when().
					get("http://localhost:8080/json").
				then().
					extract().response();
		
		JsonPath jsonPath = response.jsonPath();
		Timestamp responseTimestamp = jsonPath.get("timestamp");
		long responseMs = responseTimestamp.getTime();
		
		assertThat(lowerTimeBoundry <= responseMs && upperTimeBoundry >= responseMs);
	}
	
}
