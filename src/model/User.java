package model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private Role.RoleNameUser role;
    private double tienNap = 0;
    private Role.RoleRankUser roleRankUser;

    public User() {
    }

    public User(int id, String username, String password, Role.RoleNameUser role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public Role.RoleRankUser getRoleRankUser() {
        return roleRankUser;
    }

    public void setRoleRankUser(Role.RoleRankUser roleRankUser) {
        this.roleRankUser = roleRankUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTienNap() {
        return tienNap;
    }

    public void setTienNap(double tienNap) {
        this.tienNap = tienNap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role.RoleNameUser getRole() {
        return role;
    }

    public void setRole(Role.RoleNameUser role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", point=" + tienNap +
                ", roleRankUser=" + roleRankUser +
                '}';
    }
}
