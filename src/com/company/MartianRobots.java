package com.company;

import com.company.Robot.Robot;
import com.company.Robot.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class MartianRobots {

    public static void main(String[] args) {
        System.out.println("Mission Martian Robots. Please enter \"finish\" once you are ready to launch!");
        ArrayList<String> userCommands = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //Collect user commands
        String input = null;
        while(!(input = scanner.nextLine()).equals("finish")){
            if(input.length() > 0){
                userCommands.add(input);
            }
        }

        //First line should be map size
        String[] mapSize = userCommands.get(0).split(" ");

        MarsMap marsMap = new MarsMap(Integer.parseInt(mapSize[0]),Integer.parseInt(mapSize[1]));
        for (int i = 1;i < userCommands.size();i++){
            String line = userCommands.get(i).toLowerCase();
            if(!line.matches("[f,r,k,l]+")){
                //First Line is initial robot specs
                String[] robotSpecs = line.split(" ");
                int initial_x = Integer.parseInt(robotSpecs[0]);
                int initial_y = Integer.parseInt(robotSpecs[1]);
                String initial_orientation = robotSpecs[2];
                Robot robot = new Robot(initial_x,initial_y,initial_orientation);
                ArrayList<int[]> scentsToAdd = new ArrayList<>();

                //Next Line is instructions
                String[] instructions = userCommands.get(i+1).split("");
                for(String instruction:instructions){
                    instruction = instruction.toLowerCase();
                    if(instruction.equals("f")){
                        int[] forwardPosition = robot.getForwardPos();

                        //Check if the next position is in scent -If it is, then ignore
                        if(!marsMap.isScentPosition(forwardPosition[0],forwardPosition[1])){
                            //Check if forward pos is off map - Set as lost and add to scent pos
                            if(marsMap.isOffPos(forwardPosition[0],forwardPosition[1])){
                                //Add position to scents
                                scentsToAdd.add(new int[]{forwardPosition[0],forwardPosition[1]});
                                System.out.println("Adding scent: "+forwardPosition[0]+" "+forwardPosition[1]);
                                robot.setStatus(Status.LOST);
                            }
                            robot.moveForward();
                        }else{
                            System.out.println("Ignoring as next position is in scent "+forwardPosition[0]+" "+forwardPosition[1]);
                        }

                    }else{
                        robot.turn(instruction);
                    }
                }
                for(int[] scent:scentsToAdd){
                    marsMap.getScentPositions().add(new int[]{scent[0],scent[1]});
                }
                marsMap.addRobot(robot);
            }
        }
        System.out.println(marsMap.toString());


        System.out.println("Thank you - Below were the commands you issued");
        System.out.println(userCommands.toString());

    }
}
