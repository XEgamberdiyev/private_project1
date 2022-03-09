package uz.pdp.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public User registerUser(User registeringUser) {
        return userDao.registerUserToDb(registeringUser);
    }

    @Transactional
    public User loginUser(User loginUser) {
        return userDao.loginUser(loginUser);
    }

    @Transactional
    public List<User> getAuthor() {
        return userDao.getAuthor();
    }

    @Transactional
    public User getUserById(String userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, Integer.parseInt(userId));
        return user;
    }

    @Transactional
    public List<User> getAllusers(){
        List<User> allUser = userDao.getAllUser();
        return allUser;
    }


    @Transactional
    public User editUser(User user){
        return userDao.editUser(user);
    }

    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    public void deleteUserById(Integer mentorId) {
        userDao.deleteUserById(mentorId);
    }
}
