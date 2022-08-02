package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {
    private final RoleDaoImpl roleDao;

    @Autowired
    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Transactional
    public void removeRole(int id) {
        roleDao.removeRole(id);
    }

    public Role findById(int id) {
        Optional<Role> opt = roleDao.getRoleById(id);
        if (opt.isEmpty()) {
            return null;
        } else {
            return opt.get();
        }
    }

    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    public Optional<Role> findByRoleName(String roleName) {
        return roleDao.findByRoleName(roleName);
    }

    public Collection<Role> getRoles(String[] roles) {
        Collection<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(findByRoleName(role).get());
        }
        return roleSet;
    }

}
