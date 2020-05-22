import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est plutot simple, c'est un jeu basé sur des commandes.  
 * 
 * Cette classe contient toutes les informations propres au joueur. Par exemple la masse totale que porte le joueur.
 * 
 * @author Maxime BOUET
 * @version 18.03.2020
 */
public class Player{
    private String aNom;
    private Room aCurrentRoom;
    private Stack<Room> aRoomPrecedente;
    private int aMaxMasse;
    private int aCurrentMasse;
    private ItemList aInventaire;
    private int aEnergieManger;
    private int aEnergieBoire;
    private Item aVerif;
    private int aBonus;
    private int aMalus;
    private int aSigne;
    private int aCarteUtilise;
    private boolean aFinDuJeu;
    private boolean aPerdu;
    /**
     * Constructeur de la classe player.
     */
    public Player(final String pNom){
        this.aNom = pNom;
        this.aCurrentRoom = null;
        this.aRoomPrecedente = new Stack<Room>();
        this.aCurrentMasse = 0;
        this.aMaxMasse = 1200;
        this.aInventaire = new ItemList(); 
        this.aEnergieManger = 0;
        this.aEnergieBoire = 0;
        this.aBonus = 0;
        this.aMalus = 0;
        this.aSigne = 0;
        this.aCarteUtilise = 0;
        this.aFinDuJeu = false;
        this.aPerdu = false;
    } // Player(.)
    
    /**
     * Permet de retourner le nom du joueur.
     * @return Permet de retourner le nom du joueur.
     */
    public String getNom(){
        return this.aNom;
    } // getNom()
    
    /**
     * Permet de savoir si le joueur a finit le jeu.
     */
    public boolean getFinDuJeu(){
        return this.aFinDuJeu;
    } // getFinDuJeu()
    
    /**
     * Permet de savoir si le joueur a perdu ou non.
     */
    public boolean getPerdu(){
        return this.aPerdu;
    } // getPerdu()
    
    /**
     * Permet de savoir combien de bonnes actions a fait le joueure.
     */
    public int getBonus(){
        return this.aBonus;
    } // getBonus()
    
    /**
     * Permet de savoir combien de mauvais choix a fait le joueur.
     */
    public int getMalus(){
        return this.aMalus;
    } // getMalus()
    
    /**
     * Retourne la pièce dans laquelle se trouve le joueur.
     * @return la pièce dans laquelle se trouve le joueur.
     */
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    } // getCurrentRoom()
    
    /**
     * Retourne les sorties de la CurrentRoom en fonction de la direction donnée.
     * @return les sorties de la CurrentRoom en fonction de la direction donnée.
     */

    public Room getCurrentRoomExit(final String pDirection)
    {
        return this.aCurrentRoom.getExit(pDirection);
    } // getCurrentRoomExit(.)
    
    /**
     * Retourne la masse totale de l'inventairedu joueur.
     * @return la masse totale de l'inventaire du joueur.
     */
    public int getCurrentMasse(){
        return this.aCurrentMasse;
    } // getCurrentMasse()
    
    /**
     * Retourne la masse maximale de l'inventaire du joueur.
     * @return la masse maximale de l'inventaire du joueur.
     */
    public int getMaxMasse(){
        return this.aMaxMasse;
    } // getMaxMasse()
    
    /**
     * retourne l'inventaire du joueur.
     * @return l'inventaire du joueur.
     */
    public ItemList getInventaire()
    {
        return this.aInventaire;
    } // getInventaire()
    
    /**
     * Permet de déplacer le joueur vers une nouvelle Room.
     */
    public String change(final Room pRoom){ //aide sur gitHub
        this.aRoomPrecedente.push(this.aCurrentRoom);
        if (!pRoom.isExit(this.aCurrentRoom)) // évite de pouvoir retourner en arrière si la sortie n'existe pas.
        {
            this.videRoomPrecedente();
        }
        
        if(this.aInventaire.hasItem("pioche") && this.aEnergieManger >= 4 && this.aEnergieBoire >= 3){
            this.aInventaire.addItem(aVerif); // Rajoute dans l'inventaire du joueur la bonne clé quand il a tous les items necessaires.
            
        }
        
        if (pRoom instanceof Door) // Vérifie que pRoom est de type Door.
        {   
            if (this.aInventaire.hasItem((((Door)pRoom).getCle().getNom())))
            {
                this.setCurrentRoom(((Door)pRoom).getNextRoom(this.aCurrentRoom));
                
                if(this.aCurrentRoom.getDescription().equals("sortie de la station, félicitaions !")){ // Vérifie que le joueur est dans la bonne salle.
                    this.aFinDuJeu = true;
                }
                return "Le passage est ouvert et tu as réussi à passer !\n";
            }
            
            if(this.aCurrentRoom.getDescription().equals("sur des rails") && this.aInventaire.hasItem("pioche")){
                return "Le passage est bouché, tu as une pioche mais il faut avoir assez d'énergie.\n";
            }
            else if(this.aCurrentRoom.getDescription().equals("sur des rails") && this.aEnergieManger >= 3 && this.aEnergieBoire >= 3){
                return "Le passage est bouché, tu as assez d'énergie mais pas de pioche pour creuser.\n";
            }
            else if(this.aCurrentRoom.getDescription().equals("sur des rails") && this.aEnergieManger >= 3 && this.aEnergieBoire >= 3 && this.aInventaire.hasItem("pioche")){
                this.aFinDuJeu = true;
                this.setEnergieManger(0);
                this.setEnergieBoire(0);
            }
            else return "tu n'as pas de carte Navigo pour sortir.\n";
        }
        this.aCurrentRoom = pRoom;
        if(this.aCurrentRoom.getDescription().equals("vous venez de sortir du métro mais une alarme à été activé, attendez l'arrivée de la police")){
            this.aPerdu = true;
        }
        return "Tu as changé de salle.\n";
    } // change(.)
    
    /**
     * Retourne la desription de la CurrentRoom.
     * @return la desription de la CurrentRoom.
     */
    public String look(){
        return this.aCurrentRoom.getLongDescription();
    } // look()
    
    /**
     * Retourne la desription de l'item mis en paramètre.
     * @return la desription de l'item mis en paramètre.
     */
    public String lookItem(final String pNom){
        if (this.aInventaire.hasItem(pNom)){
            return this.aInventaire.getItem(pNom).getItemDescription();
        }
        else return "Tu ne peux pas regarder la description d'un item que tu ne possède pas";
    } // lookItem()
    
    /**
     * Permet de déplacer le joueur vers la pièce précédente.
     */
    public void arriere(){
        this.aCurrentRoom = this.aRoomPrecedente.pop();
    } // arriere()
    
    /**
     * Teste si il y a une Room précédente.
     */
    public boolean hasRoomPrecedente(){
        return this.aRoomPrecedente.empty();
    } // hasRoomPrecedente()
    
    /**
     * Retourne si le joueur a une carte dans son inventaire.
     * @return si le joueur a un carte dans son inventaire.
     */
    public boolean isCarte(){
        return aInventaire.hasItem("carte");
    } // isCarte()
    
    /**
     * Retourne la liste des items du joueur.
     * @return la liste des items du joueur.
     */
    public String showItems(){
        return this.aInventaire.showItems();
    } // showItems()
    
    /**
     * Permet d'indiquer au joueur sa quantité d'énergie restante.
     */
    public String showEnergie(){
        if((this.aEnergieManger + this.aEnergieBoire) == 0){
            return "ton énergie est vide.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 1){
            return "ton énergie est pratiquement vide.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 2){
            return "ton énergie est faible.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 3){
            return "ton énergie n'est pas suffisante.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 4){
            return "ton énergie n'est pas suffisante.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 5){
            return "ton énergie n'est pas suffisante.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 6){
            return "ton énergie est pratiquement suffisante.\n";
        }
        else if((this.aEnergieManger + this.aEnergieBoire) == 7){
            return "ton énergie est suffisante.\n";
        }
        else{
            return "ton énergie est pleine !";
        }
    } // showEnergie()
    
    /**
     * Permet de manger un item qui est prévu pour se faire manger. Si le joueur veut manger un item pas prévu à cet effet, un message s'affiche.
     */
    public String manger(final String pNom){
        if (this.aInventaire.hasItem(pNom)){
            if(pNom.equals("cookie")){
                this.aEnergieManger += 1;
                this.aCurrentMasse -= this.aInventaire.getItem(pNom).getMasse();
                this.aInventaire.enleveItem(pNom);
                this.aMaxMasse = 1700;
                return "tu as mangé un magic cookie, tes forces sont boostées !" + "\n" + "tu peux porter " + this.aMaxMasse + " grammes au maximum et tu es à : " + this.aCurrentMasse + " grammes.";
            }
            else if(pNom.equals("cerealeFruit") || pNom.equals("cerealeChoco") || pNom.equals("cerealeNoix") || pNom.equals("cereale")){
                this.aEnergieManger += 1;
                this.aCurrentMasse -= this.aInventaire.getItem(pNom).getMasse();
                this.aInventaire.enleveItem(pNom);
                return "tu as mangé une barre de céréale, ta faim réduit un peu." + "\n" + "tu peux porter " + this.aMaxMasse + " grammes au maximum et tu es à : " + this.aCurrentMasse + " grammes.";
            }
            else return "tu ne peux pas manger cet item.";
        }
        else return "tu ne peux pas manger un item que tu ne possède pas.";
    } // manger(.)
    
    /**
     * C'est une méthode qui permet de boire pour retrouver de l'énergie si on a à boire.
     */
    public String boire(final String pNom){
        if (this.aInventaire.hasItem(pNom)){
            if(pNom.equals("bouteille") || pNom.equals("bouteilleEau") || pNom.equals("bouteilleFraiche")){
                this.aEnergieBoire += 1;
                this.aCurrentMasse -= this.aInventaire.getItem(pNom).getMasse();
                this.aInventaire.enleveItem(pNom);
                return "tu as bu une bouteille d'eau entière ! Ta soif réduit peu a peu." + "\n" + "tu peux porter " + this.aMaxMasse + " grammes au maximum et tu es à : " + this.aCurrentMasse + " grammes.";
            }
            else return "tu ne peux pas boire cet item là !";
        }
        else return "tu ne peux pas boire un item que tu n'as pas.";
    } // boire(.)
    
    /**
     * Permet de prendre un objet disponible dans une salle pour le mettre dans l'inventaire du joueur.
     */
    public String prendre(final String pNom){
        if (this.aCurrentRoom.hasItem(pNom)){
            int vItemMasse = this.aCurrentRoom.getItem(pNom).getMasse();
            if (this.aCurrentMasse + vItemMasse <= this.aMaxMasse){
                if(pNom.equals("chiot") && this.aInventaire.hasItem("porte-feuille")){
                    return "Tu ne peux pas prendre le chiot si tu as déjà pris le porte-feuille.";
                }
                else if(pNom.equals("porte-feuille") && this.aInventaire.hasItem("chiot")){
                    return "Tu ne peux pas prendre le portefeuille si tu as déjà pris le chiot.";
                }
                else{
                    this.aInventaire.addItem(this.aCurrentRoom.getItem(pNom));
                    this.bonusMalus(pNom); // Met a jour les bonus et malus.
                    this.aCurrentMasse += vItemMasse;
                    this.aCurrentRoom.enleveItem(pNom);
                    return pNom + " a été ramassé(e)." + "\n" + "tu peux porter " + this.aMaxMasse + " grammes au maximum et tu es à : " + this.aCurrentMasse + " grammes.";
                }
            }
            else return "Vous ne pouvez pas prendre aussi lourd." + "\n" + "tu peux porter " + this.aMaxMasse + " grammes au maximum et tu es à : " + this.aCurrentMasse + " grammes.";
        }
        else return "Il n'y a pas cet objet à cet endroit.";
    } // prendre(.)
    
    /**
     * permet de mettre à jour les bonus que le joueur a et les malus à chaque fois qu'il change de salle.
     */
    public void bonusMalus(final String pNom){
        if(pNom.equals("chiot")){
            this.aBonus += 1;
        }
        if(pNom.equals("porte-feuille")){
            this.aMalus += 1;
        }
    } // bonusMalus(.)
    
    /**
     * permet de mettre à jour les bonus que le joueur a et les malus à chaque fois qu'il change de salle.
     */
    public void bonusMalus2(){
        if(this.aSigne == 1){ // Met le bonus qu'une seule fois.
            this.aBonus += 1;
            this.aSigne += 1;
        }
    } // bonusMalus2()
    
    /**
     * Permet de savoir si la feuille a été signée.
     */
    public void estSigne(){
        this.aSigne += 1;
    } // estSigne()
    
    /**
     * permet de savoir si la carte a été déjà utilisée une fois.
     */
    public void carteUtilise(){
        this.aCarteUtilise += 1;
    } // carteUtilise()
    
    /**
     * permet de savoir si la carte a été déjà utilisée une fois.
     */
    public int isCarteUtilise(){
        return this.aCarteUtilise;
    } // isCarteUtilise()
    
    /**
     * Permet de jeter un objet que on avait ramassé dans une salle et disparait de l'inventaire du joueur.
     */
    public String lacher(final String pNom){
        if (this.aInventaire.hasItem(pNom)){
            this.aCurrentRoom.addItem(this.aInventaire.getItem(pNom));
            if(pNom.equals("chiot")){
                this.aBonus -= 1;
            }
            if(pNom.equals("porte-feuille")){
                this.aMalus -= 1;
            }
            this.enleveItem(pNom);
            return pNom + " a été jeté(e)." + "\n" + "tu peux porter " + this.aMaxMasse + " grammes au maximum et tu es à : " + this.aCurrentMasse + " grammes.";
        }
        else return "Cet objet n'est pas dans ton inventaire.";
    } // lacher(.)
    
    /**
     * Permet de préparer le beamer pour pouvoir ensuite avec "fire" téléporter le joueur où le beamer a été chargé.
     */
    public String charge(final String pItemNom)
    {
        if (!this.aInventaire.hasItem(pItemNom)){
            return "Cet objet n'est pas dans votre inventaire.";
        }
        
        Item vItem = this.aInventaire.getItem(pItemNom);
        if (vItem instanceof Beamer){ // Vérifie que l'item mis en paramêtre est de type Beamer.
            return ((Beamer)vItem).charge(this);
        }
        else{
            return "Cet objet ne peut pas être chargé.";
        }
    } // charge(.)
    
    /**
     * retourne une chaine de charactère pour savoir si le joueur à les bon items pour signer la feuille.
     */
    public String isStylo(){
        if(this.aInventaire.hasItem("stylo") && this.aInventaire.hasItem("feuille")) return "bon";
        else if(this.aInventaire.hasItem("stylo")) return "pas feuille";
        else if(this.aInventaire.hasItem("feuille")) return "pas stylo";
        else return "rien";
    } // isStylo()
    
    /**
     * Permet de téléporter le joueur où le beamer a été chargé
     */
    public String fire(final String pItemNom)
    {
        if (!this.aInventaire.hasItem(pItemNom)){
            return "Cet objet n'est pas dans votre inventaire.";
        }
        
        Item vItem = this.aInventaire.getItem(pItemNom);
        if (!(vItem instanceof Beamer)){
            return "Cet objet ne peut pas être utilisé.";
        }
        
        this.aInventaire.enleveItem(pItemNom); // Permet d'utiliser le Beamer qu'une seule fois.
        return ((Beamer)vItem).fire(this);
    } // fire(.)
    
    /**
     * Change la Room où est le joueur
     */
    public void setCurrentRoom(final Room pRoom){
        this.aCurrentRoom = pRoom; 
    } // setCurrentRoom(.)
    
    /**
     * Permet de rajouter un item dans l'inventaire du joueur quand celui-ci à la pioche et assez d'énergie.
     */
    public void setVerif(){
        Item vraiePioche = new Item("vraiePioche", " C'est une pioche qui peut nous permettre de débloquer un passage qui est bouché",250);
        this.aVerif = vraiePioche;
    } // setVerif()
    
    /**
     * Permet de donner une valeur à l'énergie du joueur grâce à la nourriture.
     */
    public void setEnergieManger(final int pNombre){
        this.aEnergieManger = pNombre;
    } // setEnergieManger(.)
    
    /**
     * Permet de donner une valeur à l'énergie du joueur grâce aux boissons.
     */
    public void setEnergieBoire(final int pNombre){
        this.aEnergieBoire = pNombre;
    } // setEnergieBoire(.)
    
    /**
     * Enlève l'item choisis de l'inventaire.
     */
    public void enleveItem(final String pNom)
    {
        this.aCurrentMasse -= this.aInventaire.getItem(pNom).getMasse();
        this.aInventaire.enleveItem(pNom);
    } // enleveItem(.)
    
    /**
     * Vide la roomPrecedente.
     */
    public void videRoomPrecedente()
    {
        this.aRoomPrecedente.clear();
    } // videRoomPrecedente()
} // Player 