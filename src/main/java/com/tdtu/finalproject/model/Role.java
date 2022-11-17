package com.tdtu.finalproject.model;

public class Role {
    private int id;
    private String name;
    private String defaultRole;
    private int Permission;

    public Role(int id, String name, String defaultRole, int permission) {
        this.id = id;
        this.name = name;
        this.defaultRole = defaultRole;
        Permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    public int getPermission() {
        return Permission;
    }

    public void setPermission(int permission) {
        Permission = permission;
    }
}
