package jpastart.reserve.application;

import jpastart.reserve.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class WithdrawService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void withdraw(String email) {
        try {
            User user = em.find(User.class, email);
            if (user == null) {
                throw new UserNotFoundException();
            }
            em.remove(user);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }
}
