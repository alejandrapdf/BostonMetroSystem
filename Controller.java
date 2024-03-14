package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.io.FileNotFoundException;
import java.util.*;

public class Controller<User>{
    @FXML
    public Shape[] selected = new Shape[2];
    @FXML
    private VBox rootPane;
    @FXML
    private ComboBox<String> start, destination;
    @FXML
    private Label displayOutput,dispTime;
    @FXML
    private TextArea testPrint;
    @FXML
    private String[] linesBetweenStations = {"30-29","57-54-47-46","29-25","105-104","106-105","33-30" ,"31-29", "29-27","27-22","22-20" ,"20-19" ,"34-31", "41-34" ,"51-47","28-26" ,"60-33","58-56","61-58","83-81","119-118","53-41","19-17","72-69","70-62","54-47","28-27","123-122","107-106","100-98","120-98","30-28","79-70","47-46","62-53" ,"57-47" ,"51-41" ,"27-24" ,"50-48" ,"48-42" ,"112-111","111-110","110-109","109-108","108-107","103-100" ,"104-103" ,"124-123" ,"121-120" ,"98-94" ,"94-60" ,"4-3" ,"6-4" ,"9-6" ,"11-9" ,"13-11" ,"16-13" ,"18-16" ,"26-18" ,"8-7" ,"10-7" ,"14-10" ,"21-14" ,"23-21" ,"25-23" ,"96-95" ,"95-92" ,"92-89" ,"89-88" ,"88-87" ,"87-84" ,"84-79" ,"91-85" ,"86-85" ,"113-86" ,"114-113" ,"115-114" ,"116-115" ,"117-116" ,"118-117" ,"91-90" ,"90-65","65-57" ,"63-61","68-63" ,"73-68" ,"74-73","76-74","77-76","80-77","81-80","75-72","78-75","78-71","71-67","67-66","66-59","59-55","55-49","50-49","42-40","40-36","36-35","37-35","38-37","39-38","43-39","45-43","46-45","56-54","120-100-98","122-121","28-22","22-20O","20-15","15-12","12-5","5-2","2-1","93-82","53-51-41","64-52","52-44","32-30","44-32","82-64","97-93","99-97","101-99","102-101"};
   
    @FXML
    private AnchorPane map;
    ArrayList<List<Node>> Bestpath;
    GraphAPI model = new MGraph();
    
    @FXML
    protected void onButtonClick() throws FileNotFoundException {
      
        String initial = start.getSelectionModel().getSelectedItem();
        initial = initial.replaceAll(" ", "");
        String end = destination.getSelectionModel().getSelectedItem();
        
        end = end.replaceAll(" ", "");
        
        //checks if something is already selected on the map and clears it
        if (selected[0] != null)
            selected[0].setFill(Color.WHITE);
        if (selected[1] != null)
            selected[1].setFill(Color.WHITE);
  
        //finds station ID to find the correct map according to what the user has
        //selected
        int initialID = model.getID(initial);
        int endID = model.getID(end);
        selected[0] = (Shape)map.lookup("#" + initialID);
        selected[1] = (Shape)map.lookup("#" + endID);
        selected[0].setFill(Color.BLACK);
        selected[1].setFill(Color.BLACK);
        
        
        //calls show path to find and display the selected station's journey on the map
        showPath(initialID,endID);
    
    }
    
    
    
    
    public void selectStation(Shape value) {
        //If an already selected station is selected again, it is then deselected 
    	//and returned to being white
        if (value.getFill() == Color.BLACK)  {
            value.setFill(Color.WHITE);
            if(selected[1] == value) {
                selected[1] = null;
            }
            else if(selected[0] == value) {
                selected[0] = null;
            }
        }
        
        else {
        	//If a station is selected, turn circle black and display station in selection box
            value.setFill(Color.BLACK);
            if(selected[0] == null) {
                selected[0] = value;
                start.valueProperty().set(model.getName(Integer.valueOf(value.getId())));
            }
            else if(selected[1] == null){
                selected[1] = value;
                destination.valueProperty().set(model.getName(Integer.valueOf(value.getId())));
            }
            //If two stations are already selected and a user tries to select a third,
            //first station is deselected, first station is then replaced with what
            //was the second station and third station replaced the second in a round-robin
            //type system
            else {
                selected[0].setFill(Color.WHITE);
                selected[0] = selected[1];
                start.valueProperty().set(model.getName(Integer.valueOf(selected[0].getId())));
                selected[1] = value;
                destination.valueProperty().set(model.getName(Integer.valueOf(selected[1].getId())));
            }
        }
        //If both stations are deselected, clear all text boxes and clear map
        if(selected[1] == null && selected[0] == null) {
            Shape visibleLines;
            for(int i = 0; i<linesBetweenStations.length;i++) {
                visibleLines = (Shape)map.lookup("#" + linesBetweenStations[i]);
                visibleLines.setVisible(false);
                start.valueProperty().set(null);
                destination.valueProperty().set(null);
                testPrint.clear();
                displayOutput.setText("");
                dispTime.setText("");
            }
            
        }
        //If two stations are selected show route on map
        if(selected[1] != null && selected[0] != null) {
            try {
                showPath(Integer.valueOf(selected[0].getId()),Integer.valueOf(selected[1].getId()));
                
                
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void showDirections(ArrayList<List<Node>> path) {
    	//Prints travel instructions for the route in the text box
    	testPrint.setText("Best route to be followed: ");
        for (int i = 0; i < Bestpath.size(); i++) {
            testPrint.setText(model.printPath(Bestpath.get(i),(MGraph)model));
        }
        testPrint.setWrapText(true);
    }

    public void showPath(int from, int to)  throws FileNotFoundException {
        Bestpath = model.finalPath(from,to,(MGraph) model);
        
        int[] node = new int[2];
        displayOutput.setText("Your planned route is " + model.getName(from)  + " to " + model.getName(to));
        dispTime.setText("Your journey will take " + model.getTime() +" minutes");
        //Displays directions in text box
        showDirections(Bestpath);
        
        
        
        //This for loop gets a node and it's next node and combines the two in a format that the ID
        //of each of the lines is. The format is biggerStationID-smallerStationID
        String[] visible = new String[Bestpath.get(0).size() - 1];
        for (int i = 0; i+1 < Bestpath.get(0).size(); i++) {
            node[0] = Bestpath.get(0).get(i).getDest();
            node[1] = Bestpath.get(0).get(i + 1).getDest();
            Arrays.sort(node);
            //catches the exception that there are two lines that are between the same stations
            if(node[0] == 22 && node[1] == 20 && Bestpath.get(0).get(i+1).getRoute().equals("Orange")) {
                visible[i] = "#"+Integer.toString(node[1]) + "-" + Integer.toString(node[0])+"O";
            }
            else {
                visible[i] = "#"+Integer.toString(node[1]) + "-" + Integer.toString(node[0]);
            }
        }
        Shape visibleLines;
        String[] temp;
        int count = 0;
        boolean checkCombos = false;
        for(int i = 0; i<linesBetweenStations.length;i++) {
            temp = linesBetweenStations[i].split("-");
            checkCombos = false;
            
            //Catches where we have added extra lines for aesthetics where a route splits into two
            if(temp.length == 3) {
                String[] combos = {("#"+temp[0]+"-"+temp[1]),("#"+temp[0]+"-"+temp[2]),("#"+temp[1]+"-"+temp[2])};
                for(int j = 0; j <combos.length; j++) {
                    if(Arrays.asList(visible).contains(combos[j])) {
                        checkCombos = true;
                    }
                }
            }
          //Catches where we have added extra lines for aesthetics where a route splits into three
            else if(temp.length == 4) {
                System.out.println("I'm temp" + linesBetweenStations[i]);
                String[] combos = {("#"+temp[0]+"-"+temp[1]),("#"+temp[0]+"-"+temp[2]),("#"+temp[1]+"-"+temp[2]),("#"+temp[0]+"-"+temp[3]),("#"+temp[1]+"-"+temp[3]),("#"+temp[2]+"-"+temp[3])};
                for(int j = 0; j <combos.length; j++) {
                    if(Arrays.asList(visible).contains(combos[j])) {
                        checkCombos = true;
                    }
                }
            }
            //Shows the given path 
            if(Arrays.asList(visible).contains("#" + linesBetweenStations[i]) || checkCombos) {
                visibleLines = (Shape)map.lookup("#" + linesBetweenStations[i]);
                visibleLines.setVisible(false);
                System.out.println(visibleLines);
            }
          //Greys out all lines except the given path 
            else {
                visibleLines = (Shape)map.lookup("#" + linesBetweenStations[i]);
                visibleLines.setVisible(true);
            }
        }
    }
    @FXML
    public void onClick(MouseEvent event) {
    	//When a station is clicked run select station on shape
        Shape value = (Shape)event.getSource();
        selectStation(value);
    }
    public void callGraph() {
        try {
            model.Graph();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
