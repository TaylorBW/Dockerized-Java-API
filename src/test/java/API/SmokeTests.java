package API;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class SmokeTests {
	@Autowired
	private Controller controller;

	@Test
	public void controllerCheck() throws Exception {
		assertThat(controller).isNotNull();
	}

}
