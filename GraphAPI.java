package application;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface GraphAPI {

    void Graph() throws FileNotFoundException;

    ArrayList<List<Node>>finalPath(int station1, int station2, MGraph graph);
    
    String printPath(List<Node> path,MGraph  graph);
    
    int getID(String stationName);

    String getName(int stationID);

    Integer getTime();

}
