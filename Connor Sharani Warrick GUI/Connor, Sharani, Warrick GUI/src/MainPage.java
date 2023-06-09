import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainPage extends JPanel{
    Frame frame;
    
    private JComboBox<String> stationDropdown;
    private JComboBox<String> radiusDropdown;
    private JTextField addressField;

    private JLabel gasLabel;
    private JLabel locationLabel;
    private JLabel budgetLabel;
    private JLabel maintitleLabel;

    JLabel mainMenuPictureLabel = new JLabel(new ImageIcon("src/Images/MainScreen.png"));
    //Assigning options for the dropdown menus for gas type and budget
    private String[] stationOptions = {"Regular", "Midgrade", "Premium", "Diesel", "Tesla Supercharge"};
    private String[] radiusOptions = {"2.5", "5", "7.5", "10", "12.5", "15"};

    private BackendController backend;

    MainPage(Frame frame, BackendController backend)throws IOException{
        super(null);
        this.frame = frame;
        this.backend = backend;

        //Creating Labels (Tiles and subtiles) on the main page and assinging coordinates  
        maintitleLabel = new JLabel("Local Gas Price Checker");
        maintitleLabel.setBounds(400,20,200,100);
        gasLabel = new JLabel("Choose your gas type");
        gasLabel.setBounds(100, 300, 200, 50);
        locationLabel = new JLabel("Please enter your address:");
        locationLabel.setBounds(100,365,200,50); 
        budgetLabel = new JLabel("Select your budget:");
        budgetLabel.setBounds(100,430,300,100);

        createInputs();
        this.add(createFindGasButton());

        mainMenuPictureLabel.setBounds(0, 0, 900, 600);
        this.add(mainMenuPictureLabel);
        
        revalidate();
        repaint();
    }
    // Creating the dropdown menus for gas type and budget
    void createInputs() {
        stationDropdown = new JComboBox<>(stationOptions);
        stationDropdown.setSelectedIndex(0);
        stationDropdown.setBounds(100, 330, 200, 50);

        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(200, 50));
        addressField.setBounds(100, 400, 350, 50);

        radiusDropdown = new JComboBox<>(radiusOptions);
        radiusDropdown.setSelectedIndex(0);
        radiusDropdown.setBackground(new Color(255, 172, 28));
        radiusDropdown.setBounds(100, 480, 100, 50);

        //Adding labels, dropdown menues to the page 
        this.add(stationDropdown);
        this.add(addressField);
        this.add(radiusDropdown);
        this.add(maintitleLabel);
        this.add(gasLabel);
        this.add(locationLabel);
        this.add(budgetLabel);
    }
    // Creating button for the user to press after inputting their information
    // Uses the information added to load the gas stations
    private JButton createFindGasButton() {
        JButton findGasButton = new JButton("Find Gas");
        findGasButton.addActionListener(e ->
        {
            try {
                checkAddress();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            catch(InterruptedException e2){
                e2.printStackTrace();
            }
        }
        
        );
        findGasButton.setBounds(600, 400, 200, 100);
        return findGasButton;
    }
    //Checks the address entered to make sure the address exists 
    void checkAddress() throws IOException, InterruptedException{
        /*
        if(!backend.setUserAddress(addressField.getText())){
            JOptionPane.showMessageDialog(this,"Please Enter A Valid Address in Ontario.","Reprompt", JOptionPane.WARNING_MESSAGE);
        }
        else{
            frame.showSecondPage();
        }
         */
        frame.showSecondPage();
    }

    String getStation(){
        return stationDropdown.getSelectedItem().toString();
    }
    double getRadius(){
        return Double.parseDouble(radiusDropdown.getSelectedItem().toString());
    }
    
}
