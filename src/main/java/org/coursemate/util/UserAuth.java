package org.coursemate.util;

import java.util.HashMap;
import java.util.Map;

public class UserAuth {
    // Sample user database
    private static Map<String, String> userDatabase = new HashMap<>();

    static {
        userDatabase.put("admin", "ADMIN");
        userDatabase.put("instructor", "INSTRUCTOR");
        userDatabase.put("student", "STUDENT");
    }

    public static String authenticate(String username) {
        return userDatabase.get(username);
    }
}
