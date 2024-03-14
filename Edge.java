package com.cs308gui.CS308AssignmentBoston;

public class Edge {

    private Integer src, dest;
    private String route;

    Edge(int src, int dest, String route) {
        this.src = src;
        this.dest = dest;
        this.route = route;
    }

    public int getSrc(){
        return src;
    }

    public int getDest(){
        return dest;
    }

    public String getRoute(){
        return route;
    }


    //method added for unit testing

    boolean isEqual(Edge e){
        if ( (this.src.equals(getSrc()) ) && (this.dest.equals(e.getDest())) && (this.route.equals(e.getRoute())) )
            return true;
        return false;
    }

}
