import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class SecondPage extends JPanel{
    Frame frame;
    private JLabel titleLabel;

    private JTable stationDataTable;
    private JScrollPane stationDataTableScroll;
    private JPanel tablePanel;

    // Declaring names for the columns
    private String[] columnsName = {"Station Name", "Address", "Unit Cost", "Displacement", "Directions"};
    private String[][] formattedStationData = {
        {"Petro-Canada", "9249 Ninth Ln, Markham, Ontario, Canada", "150.5 ¢", "3.0km", "Link Here"},
        {"Hello", "123 David St, Markham, Ontario, Canada", "150.5 ¢", "3.0km", "Link Here"},
        {"Siuuuuu", "321 Connor Street, Markham, Ontario, Canada", "150.5 ¢", "3.0km", "Link Here"},
        {"Station", "123 Sharani Street, Markham, Ontario, Canada", "150.5 ¢", "3.0km", "Link Here"}
    };
    private JButton sortByPriceButton;
    private JButton sortByDistanceButton;

    private BackendController backend;

    SecondPage(Frame frame, BackendController backend) throws IOException{
        super(null);
        this.frame = frame;
        this.backend = backend;

        // Creating label for a title on the second page
        titleLabel = new JLabel("Results");
        titleLabel.setBounds(400, 20, 100, 50);

        // Creating button to go back to the main page where the information is being entered
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.showMainPage());
        backButton.setBounds(25, 475, 100, 50);

        // Creating button the user can use to export the information about gas stations displayed
        // as a CSV file
        JButton csvButton = new JButton("Create CSV");
        csvButton.addActionListener(e -> {
            try {
                frame.createCSV();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });

        csvButton.setBounds(775, 475, 100, 50);

        // Adding label and buttons to the second page 
        this.add(titleLabel);
        this.add(backButton);
        this.add(csvButton);
        //Delete this later.
        showResults();
    }

    
    void showResults(){
        tablePanel = new JPanel(null);

        sortByPriceButton = new JButton("Sort");
        sortByPriceButton.addActionListener(e -> {this.remove(tablePanel); sortData(2);});
        sortByPriceButton.setBounds(460, 75, 80, 15);

        sortByDistanceButton = new JButton("Sort");
        sortByDistanceButton.addActionListener(e ->  {this.remove(tablePanel); sortData(3);});
        sortByDistanceButton.setBounds(540, 75, 80, 15);

        stationDataTable = new JTable(formattedStationData, columnsName);

        for(int columnNum = 0; columnNum < 5; columnNum++) {
            TableColumn column = stationDataTable.getColumnModel().getColumn(columnNum);
            if(columnNum == 1){
                column.setPreferredWidth(300);
            }
            else if(columnNum == 2){
                column.setPreferredWidth(75);
            }
            else if(columnNum == 3){
                column.setPreferredWidth(125);
            }
            else{
                column.setPreferredWidth(250);
            }
        }
        stationDataTableScroll = new JScrollPane(stationDataTable);
        stationDataTableScroll.setBounds(150, 90, 600, 450);

        this.add(sortByPriceButton);
        this.add(sortByDistanceButton);
        this.add(stationDataTableScroll);

        revalidate();
        repaint();
    }

    void clearCurrentData(){
        this.remove(stationDataTable);
        this.remove(stationDataTableScroll);
    }

    void getFormattedStationData(){
        //formattedStationData = backend.getFormattedStationData();
    }
    String[][] getCurrentStationData(){
        return this.formattedStationData;
    }

    void sortData(int element){
        System.out.println("Sorting");
        for(int i = 0; i < formattedStationData.length; i ++){
            for(int j = i + 1; j < formattedStationData.length; j ++){
                double currentCost = Double.parseDouble(formattedStationData[i][element].replace(" ¢", "").replace("km", ""));
                double nextCost = Double.parseDouble(formattedStationData[j][element].replace(" ¢", "").replace("km", ""));

                if(currentCost > nextCost){
                    String[] temp = formattedStationData[i];
                    formattedStationData[i] = formattedStationData[j];
                    formattedStationData[j] = temp;
                }

            }
        }

        showResults();
        revalidate();
        repaint();
    }

}

