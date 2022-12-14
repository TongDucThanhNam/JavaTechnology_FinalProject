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

    @Column(name = "permission")
    private String permission;

    public Role(int roleId, String name, String permission) {
        this.roleId = roleId;
        this.roleName = name;
        this.permission = permission;
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

    public String getPermissionId() {
        return permission;
    }

    public void setPermissionId(String permission) {
        this.permission = permission;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
