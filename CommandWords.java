/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.  
 * 
 * cette classe représente toutes les commandes qui sont disponibles.
 * C'est utile pour reconnaitre lesquelles sont bonnes.
 *
 * @author  Maxime BOUET
 * @version 03.02.2020
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private final String[] aValidCommands;
     /**
     * Le constructeur Initialise les mots qui sont valides pour une commande..
     */
    public CommandWords()
    {
        this.aValidCommands = new String[18];
        this.aValidCommands[0] = "vas";
        this.aValidCommands[1] = "aide";
        this.aValidCommands[2] = "stop";
        this.aValidCommands[3] = "regarder";
        this.aValidCommands[4] = "manger";
        this.aValidCommands[5] = "boire";
        this.aValidCommands[6] = "arriere";
        this.aValidCommands[7] = "test";
        this.aValidCommands[8] = "prendre";
        this.aValidCommands[9] = "lacher";
        this.aValidCommands[10] = "charger";
        this.aValidCommands[11] = "feu";
        this.aValidCommands[12] = "alea";
        this.aValidCommands[13] = "inventaire";
        this.aValidCommands[14] = "energie";
        this.aValidCommands[15] = "afficherCarte";
        this.aValidCommands[16] = "enleverCarte";
        this.aValidCommands[17] = "signer";
    } // CommandWords()
    
    /**
     * Affiche toutes les commandes valides sur System.out
     */
    public String getCommandList(){
        String vResult = "";
        StringBuilder vRes = new StringBuilder(vResult);
        for(String command : this.aValidCommands){
            vRes.append(command + ", ");
        }
        return vRes.toString();
    } // getCommandList()
    
    /**
     * Vérifie que le premier mot est une vrai commande. 
     * @return vrai si le mot est une commande validée,
     * sinon faux.
     */
    public boolean isCommand( final String pString )
    {
        for ( int i=0; i<this.aValidCommands.length; i++ ) {
            if ( this.aValidCommands[i].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand(.)
} // CommandWord