package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome friend. Please enter \"end\" once you are ready to roll!");
        ArrayList<String> userCommands = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //Collect user commands
        String input = null;
        while(!(input = scanner.nextLine()).equals("end")){
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

                //Next Line is instructions
                String[] instructions = userCommands.get(i+1).split("");

                marsMap.addRobot(robot);
            }
        }
        System.out.println(marsMap.toString());


        System.out.println("Thank you");
        System.out.println(userCommands.toString());

    }
}
