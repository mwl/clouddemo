package dk.mwl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

@SpringBootApplication
@Controller
public class ClouddemoApplication {

    @Value("${cloud.app.name:Demo}")
    String cloudAppName;

    @Value("${host:UNKNOWN}")
    String host;

	public static void main(String[] args) {
		SpringApplication.run(ClouddemoApplication.class, args);
	}

	@GetMapping("/")
    @ResponseBody
    public Map<String, ?> index() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("appName", cloudAppName);
        result.put("host", host);
        return result;
    }

    @RequestMapping("/{statusCode}")
    public ResponseEntity<Map<String, ?>> statusCode(@PathVariable int statusCode) {
        HttpStatus status = HttpStatus.valueOf(statusCode);

        HashMap<String, Object> result = new HashMap<>();
        result.put("appName", cloudAppName);
        result.put("host", host);
        result.put("statusCode", status.value());
        result.put("statusPrase", status.getReasonPhrase());
        return new ResponseEntity<>(result, status);
    }
}
