package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImp implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("FROM Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findRoleById(Long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }


    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT role FROM Role role WHERE role.role = :name", Role.class)
                .setParameter("name", "ROLE_" + name).getSingleResult();
    }
}
