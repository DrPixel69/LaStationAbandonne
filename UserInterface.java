import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.net.URL;
/**
 * Cette classe est une partie du jeu "Le métro abandonné". 
 * "Le métro abandonné" est très facile, c'est un jeu basé sur des commandes.   
 * 
 * Cette classe permet de traiter tout ce qui est visuel. C'est ce que le joueur voit, c'est l'interface.
 * 
 * @author  Maxime BOUET
 * @version 28.02.2020
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aButton;
    private JButton    aButton2;
    private JButton    aButton3;
    private JButton    aButton4;
    private JPanel    aButtonPanel;
    /**
     * Constructeur de la classe UserInterface.
     * Initialise l'attribut aEngine et créer la fenêtre de jeu.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Affiche du texte sur la fenêtre de texte.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Affiche du texte sur la fenêtre de texte. Suivi d'un retour à la ligne.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Affiche une image du dossier image sur la fenêtre de jeu
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image non trouvée : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * procédure permettant d'activer ou de désactiver la saisie dans le champs de saisie.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Procédure qui configure une interface utilisateur grafique.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Métro" ); // change the title
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        this.aButton = new JButton("regarder");
        this.aButton2 = new JButton("signer");
        this.aButton3 = new JButton("enleverCarte");
        this.aButton4 = new JButton("afficherCarte");
        
        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        this.aButtonPanel = new JPanel();
        this.aButtonPanel.setLayout(new GridLayout(2, 2));
        
        this.aButtonPanel.add(this.aButton);
        this.aButtonPanel.add(this.aButton2);
        this.aButtonPanel.add(this.aButton3);
        this.aButtonPanel.add(this.aButton4);

        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add(this.aButtonPanel, BorderLayout.WEST);

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        this.aButton.addActionListener(this);
        this.aButton2.addActionListener(this);
        this.aButton3.addActionListener(this);
        this.aButton4.addActionListener(this);

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Procédure qui vérifie les actions du joueur sur la fenêtre de jeu.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
            // because there is only one possible action (text input) :
        if(pE.getSource() == this.aButton)
            this.aEngine.interpretCommand("regarder");
        else if(pE.getSource() == this.aButton2)
            this.aEngine.interpretCommand("signer");
        else if(pE.getSource() == this.aButton3)
            this.aEngine.interpretCommand("enleverCarte");
        else if(pE.getSource() == this.aButton4)
            this.aEngine.interpretCommand("afficherCarte");
        else
            this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * Procédure qui analyse la commande entrée et qui la lit pour la traiter
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
