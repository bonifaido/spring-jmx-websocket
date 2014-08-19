package me.nandork.simple.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collaborator {

    private String role;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "role='" + role + '\'' +
                ", user=" + user +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {

        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                    "email='" + email + '\'' +
                    '}';
        }
    }
}
