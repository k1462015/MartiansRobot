package com.company.Robot;


public class Robot {
    private int x;
    private int y;
    private Orientation orientation;
    private Status status;

    public Robot(int x,int y,String orientation){
        this.x = x;
        this.y = y;
        System.out.println("Setting robot "+x+" "+y+" to "+getOrientation(orientation));
        this.orientation = getOrientation(orientation);
        this.status = Status.ALIVE;
    }


    public void turn(String turnDirection){
        turnDirection = turnDirection.toLowerCase();
        int newOrientationIndex;
        switch (turnDirection){
            case "r":
                newOrientationIndex = (this.orientation.ordinal() + 1) % 4;
                System.out.println("Moving right from "+this.orientation+" to "+Orientation.values()[newOrientationIndex]);
                this.orientation = Orientation.values()[newOrientationIndex];
                break;
            case "l":
                newOrientationIndex = (this.orientation.ordinal() - 1) % 4;
                if(newOrientationIndex < 0){
                    newOrientationIndex = 3;
                }
                System.out.println("Moving left from "+this.orientation+" to "+Orientation.values()[newOrientationIndex]);
                this.orientation = Orientation.values()[newOrientationIndex];
                break;
        }
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
                return new int[]{this.x + 1,this.y};
            case SOUTH:
                return new int[]{this.x,this.y - 1};
            case WEST:
                return new int[]{this.x - 1,this.y};
        }
        return null;
    }

    /**
     * Executes forward move
     */
    public void moveForward(){
        int[] newPositions = getForwardPos();
        System.out.println("Moving robot from "+this.x+" "+this.y+" to "+newPositions[0]+" "+newPositions[1]+" ("+this.orientation+")");
        this.x = newPositions[0];
        this.y = newPositions[1];
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString(){
        return this.x+" "+this.y+" "+this.orientation+" "+this.status;
    }
}
