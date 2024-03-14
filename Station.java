package application;

import java.util.ArrayList;
import java.util.List;

public class Station {

    private Integer id;
    private String name;
    private List<List<Node>> route_list;

    Station(int id, String name, List<List<Node>> route_list) {
        this.name = name;
        this.id = id;
        this.route_list = route_list;
    }

    public void SetName(int id, ArrayList<Node> nametoid) {
        for (int i = 0; i < nametoid.size(); i++) {
            if (id == nametoid.get(i).getDest())
                name = nametoid.get(i).getRoute(); //route here is the name
        }
    }

    public void Setid(String name, ArrayList<Node> nametoid) {
        for (int i = 0; i < nametoid.size(); i++) {
            if (name.equals(nametoid.get(i).getRoute()))
                id = nametoid.get(i).getDest(); //dest here is the id
        }
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }


    //method added for unit testing

    List<List<Node>> getRoute_list(){
        return route_list;
    }

}
