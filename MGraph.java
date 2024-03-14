package com.cs308gui.CS308AssignmentBoston;

import java.io.FileNotFoundException;
import java.util.*;

public class MGraph implements GraphAPI {

    private List<List<Node>> route_list = new ArrayList<>();
    private FileReader filereader = new FileReader();
    private Integer time;

    public void Graph() throws FileNotFoundException {
        // adjacency list memory allocation
        List<Edge> readList = filereader.readFile();

        for (int i = 0; i < readList.size(); i++)
            route_list.add(i, new ArrayList<>());

        // add edges to the graph
        for (Edge e : readList) {
            // creating the edge by connecting it with the destination node
            route_list.get(e.getSrc()).add(new Node(e.getDest(), e.getRoute()));
        }
    }

    public String printPath(List<Node> path, MGraph  graph){

        String totalPath = "";
        int i;
        for( i =0; i<path.size()-1; i++){
            Node node = path.get(i);
            Node nodenext = path.get(i+1);
            String stationName;
            stationName = graph.getName(node.getDest());
            if(i==0) {
                totalPath = totalPath + " " + " => " + stationName + " on line " + nodenext.getRoute();

            }
            else if(node.getRoute().equals((nodenext.getRoute()))) {
                totalPath = totalPath + " => " + stationName + " ";
            }
            else {
                totalPath = totalPath + " " + " => " + stationName + " change to " + nodenext.getRoute() + " line";
            }
        }
        //extracts the destination station
        totalPath = totalPath + " " + " => " + graph.getName(path.get(i).getDest());

        return totalPath;
    }

    public ArrayList<List<Node>>finalPath(int station1, int station2, MGraph graph){

        ArrayList<List<Node>> temp = FindAllPaths(station1, station2, graph);
        temp = fastestPath(temp,graph);
        temp = minPath(temp);
        return PathRouteBased(temp, graph);
    }

    public int getID(String stationName) {
        int id = 0;
        if(stationName.equals("St.PaulStreetEast")) {
            return 38;
        }
        else if(stationName.equals("St.PaulStreetWest")) {
            return 61;

        }
        else {
            ArrayList<Node> nametoid;
            nametoid = filereader.getAssociateNameId();
            ArrayList<String>  lines = new ArrayList<>();
            Station station = new Station(id,stationName,route_list);
            station.Setid(stationName,nametoid);
            id = station.getId();
            return id;
        }
    }

    public String getName(int stationID) {
        String name = "";
        ArrayList<String>  lines = new ArrayList<>();
        ArrayList<Node> nametoid;
        nametoid = filereader.getAssociateNameId();
        Station station = new Station(stationID,name,route_list);
        station.SetName(stationID,nametoid);
        name = station.getName();
        return name;
    }

    public Integer getTime() {
        return time;
    }


    private static boolean isNotVisited(Node n, List<Node> path) {
        int size = path.size();
        for(int i = 0; i < size; i++)
            if ((path.get(i).getDest() == n.getDest()))
                return false;

        return true;
    }

    private ArrayList<List<Node>> FindAllPaths(int StartPoint, int EndPoint, MGraph graph){

        int n = graph.route_list.size();
        ArrayList<List<Node>> allPaths = new ArrayList<List<Node> >(n);

        // Create a queue which stores
        // the paths
        Queue<List<Node> > queue = new LinkedList<>();

        // Path vector to store the current path
        List<Node> path = new ArrayList<>();
        Node startNode = new Node(StartPoint, "startroute");
        path.add(startNode);
        queue.offer(path);

        while(!queue.isEmpty()){
            path = queue.poll();
            Node last = path.get(path.size()-1);
            if(last.getDest() == EndPoint){
                allPaths.add(path);


            } else {

                List<Node> LastNodeList = graph.route_list.get(last.getDest());
                for(int i = 0; i < LastNodeList.size(); i++)
                {
                    if (isNotVisited(LastNodeList.get(i), path))
                    {
                        List<Node> newpath = new ArrayList<>(path);
                        newpath.add(LastNodeList.get(i));
                        queue.offer(newpath);
                    }
                }

            }

        }


        return allPaths;
    }

    private String[] Lines(ArrayList<List<Node>> paths, MGraph graph){


        int size = graph.route_list.size();
        int src_station =0;
        String[] routes = new String[11];
        int c =0;
        boolean found = false;
        routes[0] = graph.route_list.get(1).get(0).getRoute();

        while (src_station < size) {
            for (Node node : graph.route_list.get(src_station)) {
                for (int i = 0; i < 11; i++) {
                    if (node.getRoute().equals(routes[i])) {
                        found = true;
                        i = 11;
                    }
                }
                if(found == false) {
                    c++;
                    routes[c] = node.getRoute();

                }
                found = false;
            }
            src_station++;
        }


        return routes;
    }

    private ArrayList<List<Node>> fastestPath(ArrayList<List<Node>> paths, MGraph graph){

        String[] lines = Lines(paths,graph);
        ArrayList<Node> MinutesPerLine = new ArrayList<>();
        int mins = 2;
        int minMins = 400;

        for(int i = 0; i< lines.length; i++){
            MinutesPerLine.add(i,new Node(mins,lines[i]));
            mins++;
            if (mins == 4)
                mins = 2;
        }
        int finalMins = 0;
        int src_station = 0;
        int totalMins = 0;
        ArrayList<List<Node>>fastestPaths = new ArrayList<>();
        int n = paths.size();
        int s;
        int[] arr = new int[paths.size()];
        int a =0;
        while (src_station < n) {
            for (Node node : paths.get(src_station)) {
                for(int i =0; i<MinutesPerLine.size(); i++) {
                    if (node.getRoute().equals(MinutesPerLine.get(i).getRoute())) {
                        totalMins = totalMins + MinutesPerLine.get(i).getDest();
                    }
                }
                arr[src_station] = totalMins;

            }
            if(totalMins<=minMins) {

                minMins = totalMins;
                s = src_station;
                finalMins = minMins;
            }
            src_station++;
            totalMins = 0;
        }
        for(int i=0; i<arr.length; i++) {
            if (arr[i] == finalMins) {
                fastestPaths.add(a, paths.get(i));
                a++;
            }
        }

        for (int i = 0; i < fastestPaths.size();i++) {
            graph.printPath(fastestPaths.get(i),graph);
        }
        time = finalMins;

        return fastestPaths;

    }

    private ArrayList<List<Node>> minPath(ArrayList<List<Node>> paths){

        ArrayList<List<Node>> BestPaths = new ArrayList<List<Node> >();

        int[] sizes;
        sizes = new int[paths.size()];
        for(int i = 0; i<sizes.length; i++){
            sizes[i] = 0;
        }

        for(int i = 0; i<paths.size(); i++){
            sizes[i] = paths.get(i).size();
        }

        int minValue = sizes[0];

        for(int i=1; i<sizes.length; i++){
            if(sizes[i] < minValue){
                minValue = sizes[i];
            }
        }

        for(int i = 0; i< paths.size(); i++){
            if(paths.get(i).size() == minValue)
                BestPaths.add(paths.get(i));
        }

        return BestPaths;
    }

    private ArrayList<List<Node>> PathRouteBased(ArrayList<List<Node>> paths, MGraph graph){

        ArrayList<List<Node>> Routes = new ArrayList<List<Node> >();
        int changes = 0;
        String[] lines;
        int[] Changes = new int[paths.size()];

        int MinCount =300;
        int c = 0;

        for(int i=0; i<paths.size(); i++) {
            lines = new String[paths.get(i).size()];
            int a = 0;
            changes =0;
            for (int j = 0; j < paths.get(i).size(); j++) {
                lines[a] = paths.get(i).get(j).getRoute();
                a++;
            }
            for(int k=0; k<lines.length-1; k++){
                if(!lines[k].equals(lines[k+1]))
                    changes++;
            }
            Changes[i] = changes;
            if(changes<MinCount){
                MinCount = changes;
            }
        }

        for(int i=0; i<Changes.length; i++){
            if(Changes[i]==MinCount){
                Routes.add(c,paths.get(i));
                c++;
            }
        }
        return Routes;
    }


    //methods added for unit testing

    public List<List<Node>> getRoute_list(){
        return route_list;
    }

    public FileReader getFilereader(){
        return filereader;
    }

}