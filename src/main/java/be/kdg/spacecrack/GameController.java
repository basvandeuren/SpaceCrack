package be.kdg.spacecrack;

import java.util.ArrayList;
import java.util.List;

/* Git $Id$
 *
 * Project Application Development
 * Karel de Grote-Hogeschool
 * 2013-2014
 *
 */
public class GameController {
    List<Game> games;

    public GameController() {
        this.games = new ArrayList<Game>();
    }

    public Game createNewGame() {
        Game game;
        game = new Game();
        games.add(game);
        return game;
    }

    public Game getGame(int gameId) {
        return games.get(gameId );
    }
}
