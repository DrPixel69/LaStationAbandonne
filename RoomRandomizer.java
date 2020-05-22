import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
/**
 * Cette classe permet de choisir une pièce aléatoirement.
 * 
 * @author Maxime BOUET
 * @version 22.04.2020
 */
public class RoomRandomizer
{
    private ArrayList<Room> aRooms;
    /**
     * Constructeur d'objets de classe RoomRandomizer.
     */
    public RoomRandomizer(final HashMap<String,Room> pRooms)
    {
        this.aRooms = new ArrayList<Room>(pRooms.values());
    } // RoomRandomizer(.)

    /**
     * Retourne une pièce aléatoire en ne prenant pas les pièces interdites.
     * @return une pièce aléatoire en ne prenant pas les pièces interdites.
     */
    public Room getRoom()
    {
        Room result = this.aRooms.get((new Random()).nextInt(aRooms.size())); // tire un nombre aléatoire pour choisir un salle.
        if(result == this.aRooms.get(8) || result == this.aRooms.get(9) || result == this.aRooms.get(11) || result == this.aRooms.get(14)){
            return getRoom();
        }
        return result;
    } // getRoom()
} // RoomRandomizer