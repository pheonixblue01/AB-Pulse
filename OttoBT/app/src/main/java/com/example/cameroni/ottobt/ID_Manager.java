package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
import java.util.Stack;

public class ID_Manager {
    public ID_Manager(){

    }

    private int topID = 0;
    private Stack<Integer> returnedIDs = new Stack<Integer>();

    public int GetID(){
        if(returnedIDs.size() == 0){
            int oldTopID = topID;
            topID++;
            return oldTopID;
        }
        else
            return returnedIDs.pop();
    }

    public void ReturnID(int id){
        returnedIDs.push(id);
    }
}
