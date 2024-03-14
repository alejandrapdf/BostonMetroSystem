package com.cs308gui.CS308AssignmentBoston;

public class Node {

    private Integer dest;
    private String route;

    Node(int value, String route) {
        this.dest = value;
        this.route = route;
    }

    public int getDest(){
        return dest;
    }

    public String getRoute(){
        return route;
    }


    //method added for unit testing

    boolean isEqual(Node n){
        if ( (this.dest.equals(n.getDest()) ) && (this.route.equals(n.getRoute())) )
            return true;
        return false;
    }

}
