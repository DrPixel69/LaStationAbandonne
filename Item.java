/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.   
 * 
 * Cette classe permet de traiter les items du joueur.
 * 
 * @author  Maxime BOUET
 * @version 06.03.2020
 */
public class Item
{
    private String aNom;
    private String aDescription;
    private int aMasse;
    /**
     * Constructeur d'objets de classe Items
     */
    public Item(final String pNom,final String pDescription, final int pMasse){
        this.aNom = pNom;
        this.aDescription = pDescription;
        this.aMasse = pMasse;
    } // Item(.)

    /**
     * Retourne la description de l'objet.
     * @return la description de l'objet.
     */
    public String getDescription(){
        return this.aDescription;
    } // getDescription()
    
    /**
     * Retourne le nom de l'objet.
     * @return le nom de l'objet
     */
    public String getNom(){
        return this.aNom;
    } // getNom()
    
    /**
     * Retourne la masse de l'objet.
     * @return la masse de l'objet
     */
    public int getMasse(){
        return this.aMasse;
    } // getMasse()
    
    /**
     * retourne une description complète de ce qu'est l'objet.
     * Sous la forme :
     *         Vous avez "cet objet"
     *         Grâce à cet objet ...
     *         cela pèse environ ...
     * @return une description complète de ce qu'est l'objet
     */
    public String getItemDescription(){
        return "Vous avez un/une " + this.aNom + ".\n" + this.aDescription + ".\n" + "ça fait a vue d'oeil : " + this.aMasse + " grammes.";
    } // getItemDescription()
    
}// Item