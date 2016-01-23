package com.company;

import static com.company.Orientation.*;

public class Robot {
    private int x;
    private int y;
    private Orientation orientation;
    private Status status;

    public Robot(int x,int y,String orientation){
        this.x = x;
        this.y = y;
        this.orientation = getOrientation(orientation);
        this.status = Status.ALIVE;
    }

    public void turn(String newOrientation){
        newOrientation = newOrientation.toLowerCase();
        this.orientation = getOrientation(newOrientation);
    }

    public Orientation getOrientation(String orientation){
        orientation = orientation.toLowerCase();
        switch (orientation) {
            case "n":
                return Orientation.NORTH;
            case "e":
                return Orientation.EAST;
            case "s":
                return Orientation.SOUTH;
            case "w":
                return Orientation.WEST;
        }
        return null;
    }

    /**
     * Calculates what the next move would be
     * According to orientations
     * @return int[]{newXPos,newYPos}
     */
    public int[] getForwardPos(){
        switch (this.orientation){
            case NORTH:
                return new int[]{this.x,this.y + 1};
            case EAST:
                return new int[]{this.x - 1,this.y};
            case SOUTH:
                return new int[]{this.x,this.y - 1};
            case WEST:
                return new int[]{this.x + 1,this.y};
        }
        return null;
    }

    /**
     * Executes forward move
     */
    public void moveForward(){
        int[] newPositions = getForwardPos();
        this.x = newPositions[0];
        this.y = newPositions[1];
    }

    public String toString(){
        return this.x+" "+this.y+" "+this.orientation+" "+this.status;
    }
}
