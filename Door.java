import java.util.ArrayList;
/**
 * Door permet de creer des portes fermées et ouvrables avec une clé.
 * 
 *
 * @author Maxime BOUET
 * @version 17/04/2020
 */
public class Door extends Room
{
    private boolean aEstOuvert;
    private Item aCle;
    private ArrayList<Room> aExits;
    /**
     * Constructeur d'objets de classe Door
     */
    public Door(final String pDescription, final String pImage, final Item pCle)
    {
        
        super(pDescription, pImage);
        this.aEstOuvert = false;
        this.aCle = pCle;
        this.aExits = new ArrayList<Room>();
    } // Door(.)

    /**
     * Retourne la clé nécessaire à l'ouverture de la porte.
     * @return la clé nécessaire à l'ouverture de la porte.
     */
    public Item getCle()
    {
        return this.aCle;
    } // getCle()
    
    /**
     * Retourne la prochaine Room.
     * @return la prochaine Room.
     */
    public Room getNextRoom(final Room pCurrentRoom)
    {
        return this.aExits.get(0); // c'est la seule sortie de la Door (ne marche avec les door a une seule sortie).
    } // getNextRoom(.)
    
    /**
     * Cette méthode ajoute une sortie à la porte.
     */
    @Override public void setExits(final String pDirection,final Room pRoom)
    {
        super.setExits(pDirection, pRoom);
        this.aExits.add(pRoom);
    } // setExits(.)
    
} //Door