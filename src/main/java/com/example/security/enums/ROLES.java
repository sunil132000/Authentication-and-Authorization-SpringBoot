package com.example.security.enums;

import java.util.Set;

public enum ROLES {
    ADMIN(Set.of(Permissions.EMPLOYEE_READ, Permissions.EMPLOYEE_WRITE , Permissions.EMPLOYEE_DELETE)),
    USER(Set.of(Permissions.EMPLOYEE_READ));

    private final Set<Permissions> permissions;

    ROLES(Set<Permissions> permissions){
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
