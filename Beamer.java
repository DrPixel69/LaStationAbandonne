/**
 * La classe Beamer permet de téléporter le joueur dans une salle qu'il a enregistré précédament.
 *
 * @author (Maxime BOUET)
 * @version (17/04/2020)
 */
public class Beamer extends Item
{
    private Room aRoomCharge;
    private GameEngine aGameEngine;
    /**
     * Constructeur d'objets de classe Beamer
     */
    public Beamer(final String pNom,final String pDescription, final int pMasse, final GameEngine pGameEngine)
    {
        super(pNom,pDescription,pMasse);
        this.aRoomCharge = null;
        this.aGameEngine = pGameEngine;
    } // Beamer(.)
    
    /**
     * Charge le beamer en sauvegardant d'endroit où il est.
     */
    public String charge(final Player pPlayer)
    {
        this.aRoomCharge = pPlayer.getCurrentRoom();
        return "Votre emplacement a bien été enrgistré dans le Beamer.";
    } // charge(.)
    
    /**
     * Cette méthode utilise le beamer et le joueur est téléporté à la room qu'il a précédament enregistré.
     */
    public String fire(final Player pPlayer)
    {
        if (this.aRoomCharge == null) return "la téléportation est impossible puisque tu n'as pas enregistré d'emplacement précédament.";
        this.aGameEngine.change(this.aRoomCharge);
        pPlayer.videRoomPrecedente(); // Empêche de retour en arrière.
        return "Téléportation réussie !";
    } // fire(.)
} // Beamer