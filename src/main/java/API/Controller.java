package API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import java.sql.Timestamp;

@RestController
public class Controller {
	@GetMapping(path = "/debug")
	public ResponseEntity<Long> debug() {
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.OK).body(ts1.getTime() - ts2.getTime());
	}

	@GetMapping(path = "/json")
	public ResponseEntity<String> json() {
		JSONObject response = new JSONObject();
		response.put("message", "Welcome to the machine.");
		response.put("timestamp", new Timestamp(System.currentTimeMillis()));
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	

}
