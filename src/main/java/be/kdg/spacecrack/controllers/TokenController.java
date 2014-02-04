package be.kdg.spacecrack.controllers;

import be.kdg.spacecrack.Exceptions.UserNotFoundException;
import be.kdg.spacecrack.model.AccessToken;
import be.kdg.spacecrack.model.User;
import be.kdg.spacecrack.utilities.HibernateUtil;
import be.kdg.spacecrack.utilities.ITokenValueGenerator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Tim on 3/02/14.
 */
@Controller
@RequestMapping("/accesstokens")
public class TokenController {
    private ITokenValueGenerator generator;

    public TokenController() {
    }

    public TokenController(ITokenValueGenerator generator) {
        this.generator = generator;
    }


     @RequestMapping(method = RequestMethod.POST, value="/request",consumes = "application/json")
      public @ResponseBody AccessToken getToken(@RequestBody User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        org.hibernate.Transaction tx = session.beginTransaction();
        System.out.println("transaction started");
        @SuppressWarnings("JpaQlInspection") Query q = session.createQuery("from User u where u.name = :name and u.password = :password");
        q.setParameter("name", user.getName());
        q.setParameter("password", user.getPassword());
        User result = (User) q.uniqueResult();
        tx.commit();

        if(result == null)
        {
            throw new UserNotFoundException();
        }
        System.out.println("transaction committed");

        String tokenvalue = generator.generateToken();
        AccessToken accessToken = new AccessToken(tokenvalue);

        return accessToken;

    }


}
