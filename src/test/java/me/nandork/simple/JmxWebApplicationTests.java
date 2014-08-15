package me.nandork.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertFalse;

/**
 * Tests for {@link JmxWebApplication}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmxWebApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class JmxWebApplicationTests {

    RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}")
    int port;

    <T> ResponseEntity<T> get(String resourcePath, Class<T> responseType) {
        return restTemplate.getForEntity("http://localhost:" + port + resourcePath, responseType);
    }

    @Test
    public void testContextJmx() throws Exception {
        ResponseEntity<String> responseEntity = get("/jmx", String.class);

        assertFalse(responseEntity.getBody().isEmpty());
    }
}
