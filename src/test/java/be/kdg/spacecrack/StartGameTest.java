package be.kdg.spacecrack;

import be.kdg.spacecrack.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ikke on 3-2-14.
 */
public class StartGameTest {


    private final GameController gameController = new GameController();

    @Before
    public void setUp() throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Game game = new Game();
        session.saveOrUpdate(game);
        tx.commit();

    }

    @Test
    public void testNewGame() throws Exception {
        int gameId = gameController.createNewGame();
        int gameId2 = gameController.createNewGame();

        assertNotSame("Make sure that a new Id is given.", gameId, gameId2);
    }

    @Test
    public void testGetGame() throws Exception {
        int gameId = gameController.createNewGame();
        Game resultGame = gameController.getGame(gameId);

        assertEquals("ResultGame should be the same as expectedGame", gameId, resultGame.getGameId());
    }

    @Test
    public void test2ExistingGames() throws Exception {
        int gameId = gameController.createNewGame();
        gameController.createNewGame();

        assertEquals(gameId ,gameController.getGame(gameId).getGameId());
    }


}
