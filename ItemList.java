import java.util.HashMap;
import java.util.Collection;
/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.   
 * 
 * Cette classe permet de traiter tous les items en rapport avec le joueur du joueur.
 * 
 * @author  Maxime BOUET
 * @version 20.03.2020
 */
public class ItemList{
    private HashMap<String, Item> aItem;
    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList(){
        this.aItem = new HashMap<String, Item>();
    } // ItemList()
    
    /**
     * retourne l'item.
     */
    public Item getItem(final String pNom){
        return this.aItem.get(pNom);
    } // getItem(.)
    
    /**
     * Teste si la liste contient l'item demandé.
     */
    public boolean hasItem(final String pNom){
        return this.aItem.containsKey(pNom);
    } // hasItem(.)
    
    /**
     * Ajoute un item à la liste d'item.
     */
    public void addItem(final Item pItem){
        this.aItem.put(pItem.getNom(), pItem); 
    } // addItem(.)
    
    /**
     * Retire un item à la liste.
     */
    public void enleveItem(final String pNom){
        this.aItem.remove(pNom);
    } // enleveItem(.)
    
    /**
     * Teste si la liste est vide et retourne vrai ou faux.
     */
    public boolean isEmpty(){
        return this.aItem.isEmpty();
    } // isEmpty()
    
    /**
     * retourne la liste des items que le joueur possède dans son inventaire.
     * @return la liste des items que le joueur possède dans son inventaire.
     */
    public String showItems(){
        StringBuilder vRes = new StringBuilder("");
        for(String item : aItem.keySet() )
            if(item != "vraiePioche") vRes.append(" " + item);
        return vRes.toString();
    } // showItems()
    
    /**
     * Retourne une collection des valeurs de la liste d'items.
     * @return une collection des valeurs de la liste d'items.
     */
    public Collection<Item> values(){
        return this.aItem.values();
    } // values()
} // ItemList