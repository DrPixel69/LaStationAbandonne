import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est plutot simple, c'est un jeu basé sur des commandes.   
 * 
 * Cette classe créer les salles et exécute les commandes.
 * 
 * @author Maxime BOUET
 * @version 28.02.2020
 */
public class GameEngine
{
    private Parser aParser;
    private Player aPlayer;
    private HashMap<String, Room> aRooms;
    private UserInterface aGui;
    private int aCompteur;
    private RoomRandomizer aRoomRandomizer;
    private boolean aTest;
    private Room aAleaRoom;
    private String aImage;
    private boolean aIsSigne;
    /**
     * Constructeur d'objets de la classe GameEngine
     */
    public GameEngine(){
        this.aPlayer = new Player("Mercelino");
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
        this.aCompteur = 48;
        this.aTest = false;
        this.aAleaRoom = null;
        this.aImage = "";
        this.aIsSigne = false;
    } // GameEngine()
    
    /**
     * Crée l'endroit d'insertion de l'image de la salle.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    } // setGUI(.)
    
    /**
     * Affichage d'accueil du joueur.
     */
    private void printWelcome(){
        String vMess = "Bienvenue dans la station de métro abandonnée! \n Le but du jeu est de sortir d'ici le plus vite possible.\n Ecris 'aide' si tu en as besoins.";
        this.aGui.println(vMess);
        this.printLocationInfo();
    } // printWelcome()
    
    /**
     * Déclaration des salles et des Items.
     */
    private void createRooms(){
        //initialisation de toutes les pieces disponibles
        
        this.aRooms.put("stationAban", new Room("dans une station de métro abandonnée", "image/vStationAban.jpg"));
        this.aRooms.put("partieAr1",new Room("à l'arrière d'un métro","image/vPartieAr1.jpg"));
        this.aRooms.put("partieAv1",new Room("à l'avant d'un métro","image/vPartieAv1.jpg"));
        this.aRooms.put("rails1",new Room("sur des rails","image/vRails1.jpg"));
        this.aRooms.put("passage",new Room("dans un passage par dessus la ligne de métro","image/vPassage.jpg"));
        this.aRooms.put("controle",new Room("dans la salle de contrôle","image/vControle.jpg"));
        this.aRooms.put("toit",new Room("sur le toit d'un métro, vous pouvez descendre dans le trou","image/vToit.jpg"));
        this.aRooms.put("partieAr2",new Room("à l'arrière d'un métro","image/vPartieAr2.jpg"));
        this.aRooms.put("rails2",new Room("sur des rails","image/vRails2.jpg"));
        this.aRooms.put("partieAv2",new Room("à l'avant d'un métro","image/vPartieAv2.jpg"));
        this.aRooms.put("croisement",new Room("dans un croisement entre deux lignes de métro","image/vCroisement.jpg"));
        this.aRooms.put("rails4",new Room("sur des rails","image/vRails4.jpg"));
        this.aRooms.put("rails3",new Room("sur des rails","image/vRails3.jpg"));
        this.aRooms.put("stationActive",new Room("dans une station de métro active","image/vSortieActive.jpg"));
        this.aRooms.put("sortie",new Room("sortie de la station, félicitaions !","image/vSortie.jpg"));
        this.aRooms.put("rails5",new Room("sur des rails","image/vRails5.jpg"));
        this.aRooms.put("guichet",new Room("dans un ancien guichet de métro, il y a peu être des alarmes si vous continuez","image/vGuichet.jpg"));
        this.aRooms.put("fausseSortie",new Room("vous venez de sortir du métro mais une alarme à été activé, attendez l'arrivée de la police","image/vFausseSortie.jpg"));
        this.aRooms.put("sortiePlusBouchee",new Room("vous avez réussi a créer un passage pour sortir. C'est gagné!","image/vFausseSortie.jpg"));
        
        Item vraiePioche = new Item("vraiePioche", " C'est une pioche qui peut nous permettre de débloquer un passage qui est bouché",250);
        
        this.aPlayer.setVerif(); //Permet de créer le même Item que la clé pour ajouter la clé dans l'inventaire quand le joueur à tout ce qu'il faut.
        
        Item vNavigo = new Item("navigo", " C'est une carte NAVIGO qui peut servir pour sortir du métro",10);
        
        Door vSortie = new Door("si vous essayez de sortir de cette station sans carte .\n faites attention !","image/vSortie.jpg",vNavigo);
        
        Item vPioche = new Item("pioche", " C'est une pioche qui peut nous permettre de débloquer un passage qui est bouché",250);
        
        Door vSortieBouchee = new Door("vous êtes devant une sortie qui a été bouchée par des décombre. Si seulement vous aviez un outil pour tout nettoyer","image/vFausseSortie.jpg",vraiePioche);
        
        this.aRoomRandomizer = new RoomRandomizer(this.aRooms);
        Room vTeleportation = new TransporterRoom("Vous pouvez vous téléporter n'importe où aléatoirement .\n si vous touchez l'oeuvre en face de vous et que vous quittez la salle.","image/teleportation.jpg", this.aRoomRandomizer);
        
        
        //initialisation de toutes les sorties des pieces
        this.aRooms.get("stationAban").setExits("monte",this.aRooms.get("passage"));
        this.aRooms.get("stationAban").setExits("est",this.aRooms.get("rails1"));
        this.aRooms.get("stationAban").setExits("ouest",this.aRooms.get("partieAv1"));
        this.aRooms.get("stationAban").setExits("monteNE",this.aRooms.get("guichet"));
        
        this.aRooms.get("partieAr1").setExits("est",this.aRooms.get("partieAv1"));
        this.aRooms.get("partieAr1").setExits("ouest",this.aRooms.get("rails5"));
        
        this.aRooms.get("partieAv1").setExits("sud",this.aRooms.get("stationAban"));
        this.aRooms.get("partieAv1").setExits("ouest",this.aRooms.get("partieAr1"));
        
        //this.aRooms.get("rails1").setExits("est",this.aRooms.get("controle"));
        this.aRooms.get("rails1").setExits("ouest",this.aRooms.get("stationAban"));
        
        this.aRooms.get("passage").setExits("bas",this.aRooms.get("toit"));
        this.aRooms.get("passage").setExits("descendEst",this.aRooms.get("controle"));
        this.aRooms.get("passage").setExits("descendOuest",this.aRooms.get("stationAban"));
        
        this.aRooms.get("controle").setExits("monte",this.aRooms.get("passage"));
        this.aRooms.get("controle").setExits("nord",this.aRooms.get("rails1"));
        
        this.aRooms.get("toit").setExits("bas",this.aRooms.get("partieAr2"));
        
        this.aRooms.get("partieAr2").setExits("est",this.aRooms.get("rails2"));
        this.aRooms.get("partieAr2").setExits("ouest",this.aRooms.get("partieAv2"));
        
        this.aRooms.get("rails2").setExits("ouest",this.aRooms.get("partieAr2"));
        this.aRooms.get("rails2").setExits("sud",vSortieBouchee);
        this.aRooms.get("rails2").setExits("nord",this.aRooms.get("rails1"));
        
        this.aRooms.get("partieAv2").setExits("est",this.aRooms.get("partieAr2"));
        this.aRooms.get("partieAv2").setExits("ouest",this.aRooms.get("croisement"));
        
        this.aRooms.get("croisement").setExits("nord",this.aRooms.get("rails3"));
        this.aRooms.get("croisement").setExits("sud",this.aRooms.get("rails4"));
        this.aRooms.get("croisement").setExits("est",this.aRooms.get("partieAv2"));
        
        
        this.aRooms.get("rails4").setExits("nord",this.aRooms.get("croisement"));
        this.aRooms.get("rails4").setExits("ouest",vTeleportation);
        
        vTeleportation.setExits("sortir",this.aRooms.get(null));
        
        this.aRooms.get("rails3").setExits("nord",this.aRooms.get("stationActive"));
        
        this.aRooms.get("stationActive").setExits("sud",this.aRooms.get("rails3"));
        this.aRooms.get("stationActive").setExits("ouest",vSortie);
        this.aRooms.get("stationActive").setExits("nord",this.aRooms.get("rails5"));
        
        vSortieBouchee.setExits("sud",this.aRooms.get("sortiePlusBouchee"));
        
        vSortie.setExits("ouest",this.aRooms.get("sortie"));
        
        this.aRooms.get("guichet").setExits("est",this.aRooms.get("fausseSortie"));
        this.aRooms.get("guichet").setExits("descendSO", this.aRooms.get("stationAban"));
        
        this.aRooms.get("rails5").setExits("est",this.aRooms.get("partieAr1"));
        
        // Initialisation du lieu courant
        this.aPlayer.setCurrentRoom(this.aRooms.get("stationAban"));
        
        //ajouts des items dans quelques pièces
        this.aRooms.get("stationAban").addItem(new Beamer("beamer", " Cet objet permet de te téléporter dans la salle où tu veux. \n Mais qu'une seule fois !",15,this));
        
        this.aRooms.get("rails5").addItem(new Item("cerealeChoco", " C'est une barre de céréale qui peut nous permettre d'avoir assez d'énergie si on la mange",90));
        this.aRooms.get("rails5").addItem(new Item("cerealeFruit", " C'est une barre de céréale qui peut nous permettre d'avoir assez d'énergie si on la mange",90));
        
        this.aRooms.get("partieAr1").addItem(new Item("feuille", " C'est une feuille qui pourras être signée",10));
        this.aRooms.get("partieAr1").addItem(new Item("stylo", " ce stylo peut être utile dans cette pièce",10));
        
        
        this.aRooms.get("guichet").addItem(new Item("cerealeNoix", " C'est une barre de céréale qui peut nous permettre d'avoir assez d'énergie si on la mange",90));
        this.aRooms.get("guichet").addItem(vPioche);
        
        
        
        
        this.aRooms.get("controle").addItem(new Item("bouteilleEau", " C'est une bouteille d'eau qui peut nous permettre d'avoir plus d'énergie si on la boit",500));
        this.aRooms.get("controle").addItem(new Item("carte", " C'est une carte des ligne de métro pour se repérer plus facilement dans la galeries",25));
        
        this.aRooms.get("rails3").addItem(new Item("bouteille", " C'est une bouteille d'eau qui peut nous permettre d'avoir plus d'énergie si on la boit",500));
        
        this.aRooms.get("partieAr2").addItem(new Item("bouteilleFraiche", " C'est une bouteille d'eau qui peut nous permettre d'avoir plus d'énergie si on la boit",500));
        
        this.aRooms.get("partieAv2").addItem(new Item("cereale", " C'est une barre de céréale qui peut nous permettre d'avoir assez d'énergie si on la mange",90));
        
        this.aRooms.get("passage").addItem(new Item("cookie", " C'est un cookie qui donne beaucoup de force",30));
        
        this.aRooms.get("rails2").addItem(new Item("chiot", " C'est un chiot qui a été abandonné",300));
        this.aRooms.get("rails2").addItem(new Item("porte-feuille", " C'est un porte-feuille qui a été perdu",150));
        
        this.aRooms.get("toit").addItem(vNavigo);
        
    } // createRooms()
    
   /**
    * Traite (c'est-à-dire: exécute) la commande donnée.
    * Si cette commande met fin au jeu, 'true' est retourné,
    * Sinon 'false' est retourné.
    */
   public void interpretCommand( final String pCommandLine )
   {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "Je ne sais pas ce que tu veux dire..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "aide" ) )
            this.printHelp(vCommand);
        else if ( vCommandWord.equals( "vas" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "regarder" ) )
            this.look(vCommand);
        else if ( vCommandWord.equals( "boire" ) )
            this.boire(vCommand);
        else if ( vCommandWord.equals( "manger" ) )
            this.manger(vCommand);
        else if ( vCommandWord.equals( "prendre" ) )
            this.prendre(vCommand);
        else if ( vCommandWord.equals( "lacher" ) )
            this.lacher(vCommand);
        else if ( vCommandWord.equals( "arriere" ) )
            this.back(vCommand);
        else if (vCommandWord.equals("test"))
            this.test(vCommand);
        else if (vCommandWord.equals("charger"))
            this.charge(vCommand);
        else if (vCommandWord.equals("feu"))
            this.fire(vCommand);
        else if (vCommandWord.equals("alea"))
            this.alea(vCommand);
        else if (vCommandWord.equals("inventaire"))
            this.printItem(vCommand);
        else if (vCommandWord.equals("energie"))
            this.printEnergie(vCommand);
        else if (vCommandWord.equals("afficherCarte"))
            this.afficherCarte(vCommand);
        else if (vCommandWord.equals("enleverCarte"))
            this.enleverCarte(vCommand);
        else if (vCommandWord.equals("signer"))
            this.signer(vCommand);
        else if ( vCommandWord.equals( "stop" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Que veux-tu quitter ?" );
            else
                this.endGame();
        }
    } // interpretCommand(.)
    
    /**
     * La commande fire permet d'utiliser un item.
     */
    private void fire(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.fire(pCommand.getSecondWord()));
        }
        else{
            this.aGui.println("Merci d'indiquer le nom de l'objet que vous voulez utiliser.");
        }
    } // fire(.)
    
    /**
     * La commande load permet de charger un item.
     */
    private void charge(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.charge(pCommand.getSecondWord()));
        }
        else{
            this.aGui.println("Merci d'indiquer le nom de l'objet que tu veux charger.");
        }
    } // charge(.)
    
    /**
     * Permet de prendre la feuille dans les mains et de la signer 
     * Si le joueur a les bons items dans son inventaire.
     */
    private void signer(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'signer' n'est pas supposé avoir de second mot.");
        }
        else{
            if(!this.aIsSigne){//Permet de vérifier quelle image il faut afficher à l'écran.
                if(aPlayer.isStylo().equals("bon")){
                    if(this.isFeuille()){
                        this.aGui.showImage("image/feuilleSigne.jpg");
                        this.aImage = "image/feuilleSigne.jpg";
                        this.aGui.println("Tu viens de signer la preuve de ton passage ici.");
                        this.aIsSigne = true;
                        this.aPlayer.estSigne();
                    }
                    else if(this.isFeuilleSigne()){
                        this.aGui.println("Tu ne peux pas signer une feuille que tu as déja signée.");
                    }
                    else{
                        this.aGui.showImage("image/feuille.jpg");
                        this.aImage = "image/feuille.jpg";
                        this.aGui.println("que veux tu faire avec la feuille dans les mains ?");
                    }
                    
                }
                else if(aPlayer.isStylo().equals("pas stylo")){
                    this.aGui.println("Tu ne peux pas signer si tu n'as pas de stylo.");
                }
                else if(aPlayer.isStylo().equals("pas feuille")){
                    this.aGui.println("Tu ne peux pas signer si tu n'as pas de feuille.");
                }
                else{
                    this.aGui.println("Tu ne peux pas signer si tu n'as rien pour le faire.");
                }
            }
            else{
                this.aGui.println("Tu as déja signé la feuille.");
            }
        }
    } // signer(.)
    
    /**
     * Permet de savoir si le joueur a déja la feuille non-signée dans les mains.
     */
    private boolean isFeuille(){
        if(aImage.equals("image/feuille.jpg")){
            return true;
        }
        else return false;
    } // isFeuille()
    
    /**
     * Permet de savoir si le joueur a signé la feuille.
     */
    private boolean isFeuilleSigne(){
        if(aImage.equals("image/feuilleSigne.jpg")){
            return true;
        }
        else return false;
    } // isFeuilleSigne()
    
    /**
     * Permet d'afficher une carte pour aider le joueur.
     */
    private void afficherCarte(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'afficherCarte' n'est pas supposé avoir de second mot.");
        }
        else{
            if(this.aPlayer.isCarte()){
                if(this.aImage.equals("image/planV2.jpg")){
                    this.aGui.println("Tu affiche déjà la carte.");
                }
                else{
                    this.aPlayer.carteUtilise();
                    this.aGui.showImage("image/planV2.jpg");
                    this.aImage = "image/planV2.jpg";
                    this.aGui.println("tu as affiché la carte.");
                }
            }
            else this.aGui.println("Tu dois d'abord trouver la carte pour l'afficher.");
        }
    } // afficherCarte(.)
    
    /**
     * Permet d'enlever la carte que le joueur a fait afficher à l'écran.
     */
    private void enleverCarte(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'enleverCarte' n'est pas supposé avoir de second mot.");
        }
        else{
            if(this.aPlayer.isCarte()){
                if(!this.aImage.equals("image/planV2.jpg")){ // Permet de savoir quand la carte est encore affichée à l'écran.
                    this.aGui.println("tu ne peux pas enlever une carte non affichée.");
                }
                else{
                    this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName()); 
                    this.aImage = this.aPlayer.getCurrentRoom().getImageName();
                    this.aGui.println("tu as enlevé la carte.");
                }
            }
            else this.aGui.println("Tu dois d'abord trouver la carte puis l'afficher pour l'enlever.");
        }
    } // enleverCarte(.)
    
    /**
     * Permet d'afficher la liste d'item que le joueur a dans son inventaire.
     */
    private void printItem(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'inventaire' n'est pas supposé avoir de second mot.");
        }
        else{
            String vMess = "Tu ne sais plus quels objets tu as dans ton inventaire ? \n \n Voici tes Items de disponibles: " ;
            this.aGui.println(vMess + this.aPlayer.showItems());
        }
    } // printItem(.)
    
    /**
     * Permet d'indiquer au joueur l'énergie qu'il lui reste.
     */
    private void printEnergie(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'energie' n'est pas supposé avoir de second mot.");
        }
        else{
            String vMess = "Tu veux savoir ton état de fatigue ? \n \n Voici ton énergie: " ;
            this.aGui.println(vMess + this.aPlayer.showEnergie());
        }
    } // printEnergie(.)
    
    /**
     * Affichage du message d'aide, lorsque le joueur tape la commande 'aide'.
     */
    private void printHelp(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'aide' n'est pas supposé avoir de second mot.");
        }
        else{
            String vMess = "Tu es perdu(e). Tu es seul(e). \n \n Voici les commandes disponibles: " ;
            this.aGui.println(vMess + this.aParser.showCommands());
        }
    } // printHelp(.)
    
    /**
     * A pour but de déplacer le joueur dans une nouvelle pièce en fonction de la commande entrée.
     * si la commande est correcte, alors le joueur se déplace dans la nouvelle pièce
     * sinon, un message d'erreur est affiché.
     */
    public void goRoom(final Command pComad){
        if(!pComad.hasSecondWord()){
           this.aGui.println("Aller où ?");
           return;
        }
        
        String vDirection = pComad.getSecondWord();
        
        Room vNextRoom = this.aPlayer.getCurrentRoomExit(vDirection);
        
        if(this.aPlayer.getCurrentRoom().estTransporterRoom()){ // Vérifie que la currentRoom est de type transporterRoom.
            if(this.aTest && this.aAleaRoom != null){ // Vérifie que nous sommes dans un test.
                vNextRoom = this.aAleaRoom;
            }
            else{
                vNextRoom = ((TransporterRoom)(this.aPlayer.getCurrentRoom())).getExit(vDirection); // Prend une Room aléatoire.
            }
        }
        
        if(vNextRoom == null){
            this.aGui.println("Il n'y a pas de porte !");
        }
        else{
            this.change(vNextRoom);//Permet de traiter un changement de salle même si on connait seulement la salle où on veut que le joueur aille
            
        }
    } // goRoom(.)
    
    /**
     * permet de déplacer le joueur dans une nouvelle Room en vérifiant que le joueur a encore du temps.
     */
    public void change(final Room pRoom)
    {
        if(this.aCompteur <= 0){
            this.aGui.println("Tu es mort ! \n Tu a mis trop de temps à t'échapper et un train encore en activité ta percuté.");
            this.endGame();
            return;
        }
        this.aCompteur -= 1;
        this.aGui.print(this.aPlayer.change(pRoom));
        this.aPlayer.bonusMalus2();
        if(this.aPlayer.getFinDuJeu()){
            this.aGui.showImage("image/victoire.jpg");
            this.aGui.println("Bravo ! tu as réussi à sortir de la station de métro \n tu as donc gagné le jeu !");
            this.message();
        }
        else if(this.aPlayer.getPerdu()){
            this.aGui.showImage("image/alarme.jpg");
            this.aGui.println("Dommage... Tu as perdu en te faisant arrêter par la police, il y avait une alarme.");
            this.message();
        }
        else this.printLocationInfo();
            
    } // change(.)
    
    public void message() // Permet d'éviter la répétition.
    {
        int vBon = this.aPlayer.getBonus();
        if(this.aPlayer.isCarteUtilise() == 0) vBon += 1;
        this.aGui.println("Tu as fais " + vBon +" bons choix et " + this.aPlayer.getMalus() + " Mauvais !");
        if(this.aPlayer.getMalus() > 0 || this.aPlayer.getBonus() == 0) this.aGui.println("Tu peux mieux faire.");
        else this.aGui.println("Tu as fais les bons choix mais pas pour le plus important!");
    } // message()
    
    /**
     * Permet d'afficher une description complète de l'endroit où le joueur se trouve.
     */
    private void printLocationInfo(){
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription()); 
        if(this.aPlayer.getCurrentRoom().getImageName() != null)
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());  
            this.aImage = this.aPlayer.getCurrentRoom().getImageName();
    } // printLocationInfo()
    
    /**
     * C'est une procédure qui permet de regarder où on se trouve ou si il y a un deuxième mot a la commande, donne la description de l'item choisis.
     */
    public void look(final Command pCommand){
        if (!pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.look());
        }
        else{
            this.aGui.println(this.aPlayer.lookItem(pCommand.getSecondWord()));
        }
    } // look(.)
    
    /**
     * C'est une méthode qui permet de revenir dans la salle précédente.
     */
    public void back(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println("'arriere' n'est pas supposé avoir de second mot.");
        }
        
        if (this.aPlayer.hasRoomPrecedente()) this.aGui.println("Tu ne peux pas aller en arriere ici.");
        
        else{
            if(this.aCompteur <= 0){
                this.aGui.println("Tu es mort ! \n Tu a mis trop de temps à t'échapper et un train encore en activité ta percuté.");
                this.endGame();
                return;
            }
            this.aCompteur -= 1;
            this.aPlayer.arriere();
            this.printLocationInfo();
        }
    } // back(.)
    
    /**
     * C'est une méthode qui permet de manger pour retrouver de l'énergie si le joueur a à manger.
     */
    public void manger(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.manger(pCommand.getSecondWord()));
        }
        else this.aGui.println("Manger quoi ?");
    } // manger(.)
    
    /**
     * C'est une méthode qui permet de prendre un item qui est dans une pièce du jeu si cet item y est.
     */
    private void prendre(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.prendre(pCommand.getSecondWord()));
        }
        else this.aGui.println("Prendre quoi ?");
    } // prendre(.)
    
    /**
     * C'est une méthode qui permet de relacher un item dans une pièce du jeu si on a cet item.
     */
    private void lacher(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.lacher(pCommand.getSecondWord()));
        }
        else this.aGui.println("Lacher quoi ?");
    } // lacher(.)
    
    /**
     * C'est une procédure qui permet de boire pour retrouver de l'énergie si on a à boire.
     */
    public void boire(final Command pCommand){
        if (pCommand.hasSecondWord()){
            this.aGui.println(this.aPlayer.boire(pCommand.getSecondWord()));
        }
        else this.aGui.println("Boire quoi ?");
    } // boire(.)
    
    /**
     * Permet de lire un fichier pour executer des commandes automatiquement.
     */
    private void test(final Command pCommand) // aide sur gitHub.
    {
        String vNom;
        if (pCommand.hasSecondWord()) 
            vNom = pCommand.getSecondWord();
        else{
            this.aGui.println("Tu veux tester quoi ?");
            vNom = null;
        }
        
        try{
            this.aTest = true;
            Scanner vSc = new Scanner(new File(vNom+".txt"));
            while (vSc.hasNext()){
                this.interpretCommand(vSc.nextLine());
            }
            this.aTest = false;
        }
        catch(final java.io.FileNotFoundException pE){
            this.aGui.println("Le fichier demandé n'a pas été trouvé.");
        }
    } // test(.)
    
    /**
     * Permet de choisir la prochaine salle pendant un test pour faire comme si le joueur 
     * était téléporté aléatoirement.
     */
    public void alea(final Command pCommand)
    {
        if (!this.aTest)
        {
            this.aGui.println("Cette commande n'est utilisable que pendant un test.");
            return;
        }
        if (!pCommand.hasSecondWord())
        {
            this.aGui.println("alea doit avoir un second mot");
            return;
        }
        if (!aRooms.containsKey(pCommand.getSecondWord()))
        {
            this.aGui.println("Cette pièce n'existe pas.");
            return;
        }
        this.aAleaRoom = (aRooms.get(pCommand.getSecondWord())); // met en mémoire la Room voulu pour simuler un tirage aléatoire.
        this.aGui.println("La prochaine pièce est enregistrée.");
    } // alea(.)
    
    /**
     * Cette procédure permet de fermer la fenêtre du jeu si elle est appelée.
     */
    private void endGame(){
        this.aGui.showImage("image/vPerdu2.jpg"); 
        this.aGui.println( "Merci d'avoir joué, Bye." );
        this.aGui.enable( false );
    } // endGame()
} // GameEngine
