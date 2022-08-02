package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Optional;
public interface RoleDao {
    public void addRole(Role role);
    public void updateRole(Role role);
    public void removeRole(int id);
    public Optional<Role> getRoleById(int id);
    public List<Role> listRoles();
    public Optional<Role> findByRoleName(String roleName);
}