/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.  
 * 
 * Cette classe permet de lancer le jeu et de lui mettre fin si besoin.
 * 
 * @author  Maxime BOUET
 * @version 03.02.2020
 */
 public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     * Permet de lancer le jeu. 
     */
    public Game(){
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    } // Game()
} // Game
