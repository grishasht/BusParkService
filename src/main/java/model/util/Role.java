package model.util;

import java.util.Arrays;

public enum Role {
    ADMIN("admin"),
    DRIVER("driver"),
    GUEST("guest");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
    public static Role contains(String value) {
        return Arrays.stream(Role.values()).filter(a -> a.getValue().equals(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
