package com.tdtu.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "roleId")
    private int roleId;

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "Permission")
    private int Permission;

    public Role(int roleId, String name, int permission) {
        this.roleId = roleId;
        this.roleName = name;
        Permission = permission;
    }

    public Role() {

    }

    public Integer getRoleId() {
        return roleId;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getPermission() {
        return Permission;
    }

    public void setPermission(int permission) {
        Permission = permission;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
