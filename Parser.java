import java.util.StringTokenizer;
/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.  
 * 
 * Ce Parser lit les commandes entrées par le joueur et essaye de les interpreter
 * . A chaque fois qu'il est appelé, il essaye de lire les deux mots de la commande. 
 * et un Objet de la classe est retourné.
 *
 * @author  Maxime BOUET
 * @version 03.02.2020
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)
            // permettra de lire les commandes au clavier
    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()
    
    /**
     * Affiche une liste des commandes valides
     */
    public String showCommands(){
        return aValidCommands.getCommandList();
    }//showCommands()
    
    /**
     * Retourne La prochaine commande du joueur.
     * @return La prochaine commande du joueur.
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word
        else
            vWord2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if ( this.aValidCommands.isCommand( vWord1 ) )
            return new Command( vWord1, vWord2 );
        else
            return new Command( null, vWord2 );
    } // getCommand(.)
} // Parser
