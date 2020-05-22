/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.   
 * 
 * Cette classe permet de traiter les commandes du joueur.
 * 
 * @author  Maxime BOUET
 * @version 03.02.2020
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Initialise le premier est de second mot de la commande.
     */
    public Command(final String pCommandWord, final String pSecondWord){
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } // Command(.)
    
    /**
     * Permet de récupérer le premier mot de la commande.
     */
    public String getCommandWord(){
        return this.aCommandWord;
    } // getCommandWord()
    
    /**
     * Permet de récupérer le second mot de la commande.
     */
    public String getSecondWord(){
        return this.aSecondWord;
    } // getSecondWord()
    
     /**
     * Cette fonction vaut vrai si la commande a un deuxieme mot.
     */
    public boolean hasSecondWord(){
        return this.aSecondWord != null;
    } // hasSecondWord()
    
    /**
     * Vérifie qu'une commande a été entrée.
     */
    public boolean isUnknown(){
        return this.aCommandWord == null;
    } // isUnknown()
} // Command
