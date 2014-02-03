package be.kdg.spacecrack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ikke on 3-2-14.
 */
public class StartGameTest {


    private final GameController gameController = new GameController();

    @Test
    public void testNewGame() throws Exception {
        Game game = gameController.createNewGame();

        assertEquals("New Game expected",game.getClass(), Game.class );
    }
/*
    @Test
    public void testExistingGame() throws Exception {
        Game expectedGame = gameController.createNewGame();
        int gameId = 1;
        Game resultGame = gameController.getGame(gameId);

        assertEquals("ResultGame should be the same as expectedGame", expectedGame, resultGame);
    }

    @Test
    public void test2ExistingGame() throws Exception {
        Game expectedGame = gameController.createNewGame();
        gameController.createNewGame();

        assertEquals(expectedGame ,gameController.getGame(1));
    }*/
}
