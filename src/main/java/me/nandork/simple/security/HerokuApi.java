package me.nandork.simple.security;

import me.nandork.simple.security.model.Authorization;
import me.nandork.simple.security.model.Collaborator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

class HerokuApi {

    private static final String API_URL = "https://api.heroku.com";

    private final RestTemplate restTemplate = new RestTemplate();
    private String authorization;

    public List<Collaborator> getCollaborators(String appName) {
        HttpHeaders httpHeaders = newHttpHeaders();

        ResponseEntity<Collaborator[]> entity = get("/apps/" + appName + "/collaborators", Collaborator[].class, httpHeaders);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(entity.getBody());
        }
        throw new HttpClientErrorException(entity.getStatusCode());
    }

    public void authenticate(String username, String password) {
        HttpHeaders httpHeaders = newHttpHeaders();
        String authorization = authorizationFromUsernamePassword(username, password);
        httpHeaders.add("Authorization", authorization);

        ResponseEntity<Authorization> entity = post("/oauth/authorizations", Authorization.class, httpHeaders);

        if (entity.getStatusCode().is2xxSuccessful()) {
            this.authorization = authorizationFromToken(entity);
        } else {
            throw new HttpClientErrorException(entity.getStatusCode());
        }
    }

    private String authorizationFromUsernamePassword(String username, String password) {
        return "Basic " + base64(username + ":" + password);
    }

    private String authorizationFromToken(ResponseEntity<Authorization> entity) {
        return "Basic " + base64(":" + entity.getBody().getAccessToken().getToken());
    }

    static String base64(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    private HttpHeaders newHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/vnd.heroku+json; version=3");
        if (authorization != null) {
            httpHeaders.add("Authorization", authorization);
        }
        return httpHeaders;
    }

    private <T> ResponseEntity<T> get(String path, Class<T> responseType, HttpHeaders headers) {
        return restTemplate.exchange(API_URL + path, HttpMethod.GET, new HttpEntity<>(headers), responseType);
    }

    private <T> ResponseEntity<T> post(String path, Class<T> responseType, HttpHeaders headers) {
        return restTemplate.postForEntity(API_URL + path, new HttpEntity<>(headers), responseType);
    }
}
