/**
 * Cette classe permet de détecter quand il faut téléporter le joueur.
 * 
 * @author Maxime BOUET
 * @version 22.04.2020
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRoom;
    /**
     * Constructeur d'objets de classe TransporterRoom
     */
    public TransporterRoom(final String pDescription, final String pImage, final RoomRandomizer pRoom)
    {
        super(pDescription,pImage);
        this.aRoom = pRoom;
    } // TransporterRoom(.)

    /**
     * Retourne la sortie de la Pièce en fonction de la direction donnée.
     * Et si la sortie est "sortir", le joueur est téléporter aléatoirement.
     */
    @Override public Room getExit(final String pDirection)
    {
        if(pDirection.equals("sortir")){ // téléporte le joueur.
            return this.aRoom.getRoom();
        }
        else {
            return super.getExit(pDirection);
        }
    } // getExit(.)
} // TransporterRoom