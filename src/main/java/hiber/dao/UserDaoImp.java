package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private final String select = "from User u where u.car.series= :series and u.car.number= :number";

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserByCarNumberAndCarSeries(String number, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(select);
      query.setParameter("series", series);
      query.setParameter("number", number);
      return query.getSingleResult();
   }

}
