import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
 /**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est plutot simple, c'est un jeu basé sur des commandes. 
 * 
 * Cette classe permet de savoir facilement où peut-on aller et où somme nous.
 * 
 * @author  Maxime BOUET
 * @version 03.02.2020
 */
public class Room{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageNom;
    private ItemList aItems;
    /**
     * Permet d'initialiser les deux attributs
     */
    public Room(final String pDescription, final String pImage){
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageNom = pImage;
        this.aItems = new ItemList();
    } // Room(.)
    
    /**
     * Permet d'obtenir la valeur de getDescription().
     */
    public String getDescription(){
        return this.aDescription;
    } // getDescription()
    
    /**
     * retourne la sortie choisis.
     * @return la sortie qu'on a choisis.
     */
    public Room getExit(final String pDirection){
       return aExits.get(pDirection);
    }  // getExit(.)
    
    /**
     * Permet de trouver toutes les sorties disponible en fonction de là où on se trouve.
     * @return Une String des sorties.
     */
    public String getExitString(){
       StringBuilder vRes = new StringBuilder("Sorties : ");
       for(String exit : aExits.keySet() )
           vRes.append(" " + exit);
       return vRes.toString();
    } // getExitString()
    
    /**
     * Renvoie une description détaillée de cette pièce
     * sous la forme :
     *      Vous êtes sur des rails.
     *      Sorties : est ouest
     * @return Une description de la pièce, avec les sorties
     */
    public String getLongDescription(){
        return "Vous êtes " + aDescription + "\n" + this.getItemDescription() + "\n" + this.getExitString();
    } // getLongDescription()
    
    /**
     * retourne tous les items présents dans la salle où le joueur est.
     * @return tous les items présents dans la salle où le joueur est.
     */
    public String getItemDescription(){
        if (!this.aItems.isEmpty()){
            StringBuilder vSB = new StringBuilder("il y a ces/cet item(s) ici : ");
            Collection<Item> items = this.aItems.values();
            for (Item item : items){
                vSB.append(" ").append(item.getNom());
            }
            return vSB.toString();
        }
        else return "Il n'y a pas d'item ici.";
    } // getItemDescription()
    
    /**
     * Retourne le nom de l'image
     * @return le nom de l'image
     */
    public String getImageName(){
        return this.aImageNom;
    } // getImageName()
    
    /**
     * Retourne l'Item demandé.
     * @return l'Item demandé.
     */
    public Item getItem(final String pNom){
        return this.aItems.getItem(pNom);
    } // getItem(.)
    
    /**
     * Permet de créer une HashMap avec la direction et la salle vers laquelle pointe la direction.
     */
    public void setExits(final String pDirection, final Room pRoom){
        this.aExits.put(pDirection, pRoom);
    } // setExits(.)
    
    /**
     * Permet de rajouter un Item dans aItems.
     */
    public void addItem(final Item pItem){
        this.aItems.addItem(pItem);
    } // addItem(.)
    
    /**
     * Teste si la pièce contient l'item demandé.
     */
    public boolean hasItem(final String pNom){
        return this.aItems.hasItem(pNom);
    } // hasItem(.)
    
    /**
     * Retire un item à aItems.
     */
    public void enleveItem(final String pNom){
        this.aItems.enleveItem(pNom);
    } // enleveItem(.)
    
    /**
     * Teste si la Room en paramètre est une sortie de la room.
     */
    public boolean isExit(final Room pRoom)
    {
        return this.aExits.containsValue(pRoom);
    } // isExit(.)
    
    /**
     * Teste si la Current Room entrée est une TransporterRoom
     */
    public boolean estTransporterRoom()
    {
        return (this instanceof TransporterRoom);
    } // estTransporterRoom()
} // Room
