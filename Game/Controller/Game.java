package Game.Controller;

/**
 * Main class to start the program.
 *
 * @author Robert Rosencrantz, Adel Sabanovic, Sonja Peric, Yasir Kakar, Joakim Tell
 * @version 4.0
 */
public class Game {
    public static void main(String[] args) {
    	MusicController mc = new MusicController();
    	ClickController cc = new ClickController(mc);
        Controller c = new Controller(mc, cc);
    }
}
