package com.company;

import com.company.Robot.Robot;

import java.util.ArrayList;

/**
 * Created by tahmi on 23/01/2016.
 */
public class MarsMap {
    private int x_max;
    private int y_max;
    private ArrayList<int[]> scentPositions;
    private ArrayList<Robot> robots;

    public MarsMap(int x_max,int y_max) {
        this.x_max = x_max;
        this.y_max = y_max;
        scentPositions = new ArrayList<>();
        robots = new ArrayList<>();
    }

    public void addRobot(Robot robot){
        robots.add(robot);
    }


    public ArrayList<int[]> getScentPositions() {
        return scentPositions;
    }

    public void setScentPositions(ArrayList<int[]> scentPositions) {
        this.scentPositions = scentPositions;
    }

    /**
     * Checks if arguement x,y coordinate is off map
     * @param x - X Coordinate
     * @param y - Y Coordinate
     * @return
     */
    public boolean isOffPos(int x, int y){
        //Check if it is off the map
        if(x > x_max || x < 0 || y > y_max || y < 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks if coordinate matches any scent coordinates
     * @param x - X Coordinate
     * @param y - Y Coordinate
     * @return boolean - true if is scent, false if not scent
     */
    public boolean isScentPosition(int x,int y){
        for (int[] position:scentPositions){
            int x_scent = position[0];
            int y_scent = position[1];
            if(x_scent == x && y_scent == y){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String output = "";
        if(robots.size() > 0){
            System.out.println("Total Robots: "+robots.size());
            for (Robot robot:robots) {
                output += robot.toString()+"\n";
            }
        }else{
            output += "No Robots on mars";
        }
        return output;
    }


}
