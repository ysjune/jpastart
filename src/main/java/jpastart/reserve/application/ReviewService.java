package jpastart.reserve.application;

import jpastart.jpa.EMF;
import jpastart.reserve.model.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ReviewService {

    public void review() {
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Review review = new Review("KR-S-01", 5, "최고에요!");
            entityManager.persist(review);
            Long id = review.getId();
            transaction.commit();
            System.out.println("after commit");
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
}
