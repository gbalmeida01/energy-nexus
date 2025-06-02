package com.auth.service;

import com.auth.model.User;
import java.util.HashMap;
import java.util.Map;

    public class AuthService {
        private final Map<String, User> user = new HashMap<>();

        public AuthService() {
            user.put("admin", new User("admin", "1234"));
            user.put("gabriel", new User("gabriel", "senha"));
        }

        public User login(String username, String password) {
            User foundUser = user.get(username);
            if (foundUser != null && foundUser.authenticate(password)) {
                return foundUser;
            }
            return null;
        }
}
