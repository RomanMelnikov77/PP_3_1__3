package ru.kata.spring.boot_security.demo.dao;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void removeUser(int id) {
        Query query = entityManager.createQuery("delete FROM User where id = :id").setParameter("id", id);
        query.executeUpdate();
    }
    @Override
    public Optional<User> findByUsername(String username) {
        Query query = entityManager.createQuery(" SELECT u from User u where u.username = : username").setParameter("username", username);
        List<User> resultlist = query.getResultList();
        User user =resultlist.get(0);
        Optional<User> optUser = Optional.of(user);
        return optUser;
    }

}
