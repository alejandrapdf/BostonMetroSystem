package com.cs308gui.CS308AssignmentBoston;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private List<Edge> readList = new ArrayList<Edge>();
    private int index = 0;
    private ArrayList<Node> AssociateNameId = new ArrayList<Node> ();
    private String txt = "bostonmetro.txt";

    public  List<Edge> readFile() throws FileNotFoundException {

        FileInputStream stations = new FileInputStream(txt);
        Scanner reader = new Scanner(stations);

        while (reader.hasNext()) {
            int c = 0;
            int i = 0;
            int b = 0;
            if (reader.hasNext()) {


                String data = reader.nextLine();
                String[] arrSplit = splitLine(data);
                String[] finalarray = LineInTokens(arrSplit);
                int numofRoutes = 0;
                for ( i=0; i < finalarray.length; i++) {
                    if(isNumeric(finalarray[i]))
                        numofRoutes ++;
                }
                AssociateNameId(finalarray);

                numofRoutes = numofRoutes-1;

                b = numofRoutes/2;
                while( b > 0 ){
                    i = c+2;
                    c=c+3;
                    for(int j=1; j<=2; j++){
                        readList.add(new Edge(Integer.parseInt(finalarray[0]),Integer.parseInt(finalarray[i+j]),finalarray[i]));
                    }
                    b--;
                }
            } else {
                System.out.println("Corrupt data in file, could not load.");
                reader.close();
                return null;
            }


        }
        reader.close();

        return readList;
    }

    private void AssociateNameId (String[] finalarray){
        AssociateNameId.add(index, new Node(Integer.parseInt(finalarray[0]), finalarray[1] ));
        index++;
    }

    private String[]  LineInTokens (String[]  arrSplit){
        String[] finalarray ;
        int k =0;
        if(arrSplit[0].equals("")) {
            finalarray = new String[arrSplit.length-1];
            for (int a = 1; a <= arrSplit.length - 1; a++) {
                finalarray[k] = arrSplit[a];
                k++;
            }
        }else{
            finalarray = new String[arrSplit.length];
            finalarray = arrSplit;
        }
        for (int a = 0; a <finalarray.length; a++) {
            System.out.print(" "+ finalarray[a]+" ");
        }        return finalarray;
    }

    private String[]  splitLine(String data) {
        String[] split = data.split("\\s+");
        String StrSplit = Arrays.toString(split);
        StrSplit = StrSplit.trim();
        String s = StrSplit.substring(1, StrSplit.length() - 1) + "";
        String[] arrSplit = s.split(", ");

        return arrSplit;

    }

    private  boolean isNumeric(String string) {
        int intValue;


        if(string == null || string.equals("")) {

            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
         
        }
        return false;
    }

    public ArrayList<Node> getAssociateNameId(){
        return AssociateNameId;
    }


    //methods added for unit testing

    void setTxtSource (String newTxt){
        txt = newTxt;
    }

    List<Edge> getReadList () {
        return readList;
    }

}
