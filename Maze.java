/**
 * Copyright © 2016 Nick Villano
 *
 * All rights reserved. No part of this publication may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the publisher. Even with prior written
 * permission, no part of this publication may be used in a commercial setting
 * unless consent is given to do so from the publisher. For permission requests,
 * contact the publisher, Nick Villano.
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * A perfect maze generated using recursive backtracking.
 *
 * This code received an official rating of 10 out of 10 Qualities. Here is some ASCII art to celebrate this achievement.
 * 
 *                                   ,-.                  ,--,    ,--,                                                                                            
 *              ,--,             ,--/ /|    ,---. ,--,  ,--.'|  ,--.'|                                                                                            
 *       ,---,,--.'|           ,--. :/ |   /__./,--.'|  |  | :  |  | :                   ,---,   ,---.                                                            
 *   ,-+-. /  |  |,            :  : ' ,---.;  ; |  |,   :  : '  :  : '               ,-+-. /  | '   ,'\                                                           
 *  ,--.'|'   `--'_      ,---. |  '  /___/ \  | `--'_   |  ' |  |  ' |    ,--.--.   ,--.'|'   |/   /   |                                                          
 * |   |  ,"' ,' ,'|    /     \'  |  \   ;  \ ' ,' ,'|  '  | |  '  | |   /       \ |   |  ,"' .   ; ,. :                                                          
 * |   | /  | '  | |   /    / '|  |   \   \  \: '  | |  |  | :  |  | :  .--.  .-. ||   | /  | '   | |: :                                                          
 * |   | |  | |  | :  .    ' / '  : |. ;   \  ' |  | :  '  : |__'  : |__ \__\/: . .|   | |  | '   | .; :                                                          
 * |   | |  |/'  : |__'   ; :__|  | ' \ \   \   '  : |__|  | '.'|  | '.'|," .--.; ||   | |  |/|   :    |                                                          
 * |   | |--' |  | '.''   | '.''  : |--' \   `  |  | '.';  :    ;  :    /  /  ,.  ||   | |--'  \   \  /                                                           
 * |   |/     ;  :    |   :    ;  |,'     :   \ ;  :    |  ,   /|  ,   ;  :   .'   |   |/       `----'                                                            
 * '---'      |  ,   / \   \  /'--'        '---"|  ,   / ---`-'  ---`-'|  ,     .-.'---'                  ___                                                     
 *             ---`-'   `----'                   ---`-'      .--.--.    `-___--'          ,--,           /  .\                                                    
 *                        ,---, ,--,                        /  /    '.  ,--.'|_         ,--.'|           \  ; |                                                   
 *            ,---.     ,---.',--.'|        ,---,          |  :  /`. /  |  | :,'        |  | :            `--"                                                    
 *           '   ,'\    |   | |  |,     ,-+-. /  | ,----._,;  |  |--`   :  : ' :        :  : '                                                                    
 *    ,---. /   /   |   |   | `--'_    ,--.'|'   |/   /  ' |  :  ;_   .;__,'  /      .--|  ' |     ,---.                                                          
 *   /     .   ; ,. : ,--.__| ,' ,'|  |   |  ,"' |   :     |\  \    `.|  |   |     /_ ./'  | |    /     \                                                         
 *  /    / '   | |: :/   ,'   '  | |  |   | /  | |   | .\  . `----.   :__,'| :  , ' , ' |  | :   /    /  |                                                        
 * .    ' /'   | .; .   '  /  |  | :  |   | |  | .   ; ';  | __ \  \  | '  : |_/___/ \: '  : |__.    ' / |                                                        
 * '   ; :_|   :    '   ; |:  '  : |__|   | |  |/'   .   . |/  /`--'  / |  | '.'.  \  ' |  | '.''   ;   /|___                                                     
 * '   | '.'\   \  /|   | '/  |  | '.'|   | |--'  `---`-'| '--'.     /  ;  :    ;\  ;   ;  :    '   |  / /  .\                                                    
 * |   :    :`----' |   :    :;  :    |   |/      .'__/\_: | `--'---'   |  ,   /  \  \  |  ,   /|   :    \  ; |                                                   
 *  \   \  /         \   \  / |  ,   /'---'       |   :    :             ---`-'    :  \  ---`-'  \   \  / `--"                                                    
 *   `----'           `----'   ---`-'              \   \  /                         \  ' ;        `----'                                                          
 *      ,---,   ,----..           ,--,    ,---,   ,----..'              ,----..      `--`                     ,--,            ___                                 
 *   ,`--.' |  /   /   \         / .`| ,`--.' |  /   /   \             /   /   \                            ,--.'|    ,--,  ,--.'|_   ,--,                        
 *  /    /  : /   .     :       /' / ;/    /  : /   .     :           /   .     :           ,--,            |  | :  ,--.'|  |  | :,',--.'|                        
 * :    |.' '.   /   ;.  \     /  / .:    |.' '.   /   ;.  \         .   /   ;.  \        ,'_ /|            :  : '  |  |,   :  : ' :|  |,              .--.--.    
 * `----':  .   ;   /  ` ;    /  / ./`----':  .   ;   /  ` ;        .   ;   /  ` ;   .--. |  | :   ,--.--.  |  ' |  `--'_ .;__,'  / `--'_      ,---.  /  /    '   
 *    '   ' ;   |  ; \ ; |   / ./  /    '   ' ;   |  ; \ ; |        ;   |  ; \ ; | ,'_ /| :  . |  /       \ '  | |  ,' ,'||  |   |  ,' ,'|    /     \|  :  /`./   
 *    |   | |   :  | ; | '  /  /  /     |   | |   :  | ; | '        |   :  | ; | ' |  ' | |  . . .--.  .-. ||  | :  '  | |:__,'| :  '  | |   /    /  |  :  ;_     
 *    '   : .   |  ' ' ' : /  /  /      '   : .   |  ' ' ' :        .   |  ' ' ' : |  | ' |  | |  \__\/: . .'  : |__|  | :  '  : |__|  | :  .    ' / |\  \    `.  
 *    |   | '   ;  \; /  |;  /  /       |   | '   ;  \; /  |        '   ;  \; /  | :  | : ;  ; |  ," .--.; ||  | '.''  : |__|  | '.''  : |__'   ;   /| `----.   \ 
 *    '   : |\   \  ',  ./__;  /        '   : |\   \  ',  /          \   \  ',  . \'  :  `--'   \/  /  ,.  |;  :    |  | '.';  :    |  | '.''   |  / |/  /`--'  / 
 *    ;   |.' ;   :    /|   : /         ;   |.' ;   :    /            ;   :      ; :  ,      .-.;  :   .'   |  ,   /;  :    |  ,   /;  :    |   :    '--'.     /  
 *    '---'    \   \ .' ;   |/          '---'    \   \ .'              \   \ .'`--" `--`----'   |  ,     .-./---`-' |  ,   / ---`-' |  ,   / \   \  /  `--'---'   
 *              `---`   `---'                     `---`                 `---`                    `--`---'            ---`-'          ---`-'   `----'              
 *  
 * I just wanted to let the world know that I do believe in true random numbers. 
 * I hope I did not offend anyone by using only the measly pseudo random numbers 
 * that java provides. If you ever need this program (or any program) to 
 * implement true random numbers from the spring of true randomness, 
 * <https://www.random.org>, I would be glad to send you my Java code for using 
 * their API to generate truly random integers. In the words of The Beach Boys, 
 * "Be true to your school" and remember what got you to where you are today.
 * Here are a few quotes I will gift you with as a consolation: 
 * 
 * "The generation of random numbers is too important to be left to chance."
 *  —Robert R. Coveyou
 * 
 * "Random numbers should not be generated with a method chosen at random."
 *  —Donald Knuth
 * 
 * "Any one who considers arithmetical methods of producing random digits is, of
 *  course, in a state of sin."
 *  —John von Neumann
 * 
 * May these words of wisdom bring you courage.
 */
public class Maze {

    /**
     * The size of the maze including walls and halls.
     */
    private int resolution;
    /**
     * The final maze.
     */
    private char[][] maze;
    /**
     * The character representing a wall in the final maze.
     */
    private final char wall;
    /**
     * The character representing a hall in the final maze.
     */
    private final char hall;
    /**
     * A list of all the past x coordinates starting from the oldest and ending
     * with the newest.
     */
    private ArrayList<Integer> pastX;
    /**
     * A list of all the past y coordinates starting from the oldest and ending
     * with the newest.
     */
    private ArrayList<Integer> pastY;
    /**
     * Tells if each position of the maze has been visited by the generator.
     * True if visited.
     */
    private boolean[][] visited;

    /**
     * Creates a new Maze object. Sets up the maze to be generated and generates
     * a maze starting at (1, 1) of the maze.
     *
     * @param res The resolution that the maze will be including walls. The maze
     * is a square.
     * @param inW The character that will represent walls in the final maze.
     * @param inH The character that will represent halls in the final maze.
     */
    @SuppressWarnings("Convert2Diamond")
    public Maze(int res, char inW, char inH) {
        // initialize the array lists to track past positions to aid with the recursion process
        pastX = new ArrayList<Integer>();
        pastY = new ArrayList<Integer>();

        wall = inW; // sets the wall symbol to the one indicated in the parameters
        hall = inH; // sets the hall symbol to the one indicated in the parameters

        setup(res); // setsup the maze for the generation
        generate(1, 1); // generates the maze using recursive backtracking
    }

    /**
     * Sets up for the maze for the actual generation. Sets the resolution and
     * the boarder to walls and visited.
     */
    private void setup(int res) {
        resolution = res; // sets the size of the maze
        maze = new char[resolution][resolution]; // instantiates where the maze will be stored
        visited = new boolean[resolution][resolution]; // instantiates where the visited cells will be tracked
        // sets all the cells of the maze to walls
        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                maze[x][y] = wall;
            }
        }
        // sets all of the cells on the boarder of the maze to visited so that they cannot be broken through
        for (int i = 0; i < resolution; i++) {
            visited[0][i] = true;
            visited[i][0] = true;
            visited[resolution - 1][i] = true;
            visited[i][resolution - 1] = true;
        }
    }

    /**
     * Generates the maze part of the maze using recursive backtracking to break
     * down walls until a perfect maze has been created.
     *
     * @param xPos The x coordinate to break a wall from
     * @param yPos The y coordinate to break a wall from
     */
    private void generate(int xPos, int yPos) {
        /*
         RULES:
         1. set current spot to visited and opened
         2. select a random direction to try to move in
         3. for the selected direction: go there if it: is not visited, is a wall, it is not touching any other opened spaces on the 3 other sides
         4. if it is not a valid spot, pick another direction
         5. if there is no valid direction, go back to the previous section and try again
        
         */

        // Part 1
        visited[xPos][yPos] = true; // Sets the current position to visited
        maze[xPos][yPos] = hall; // Sets the current position to a hall
        // End Part 1
        // These booleans will be used so that the program knows when it needs to backtrack
        boolean cannotGoN = false;
        boolean cannotGoS = false;
        boolean cannotGoE = false;
        boolean cannotGoW = false;

        boolean canGoSomewhere = true; // this will be false once the program knows that it can not move in any direction and will triger the recursion
        // Part 4
        while (canGoSomewhere) { // this loop runs until it is known that the computer cannot validly break a wall
            // Part 2
            // generates a random direction to try to break a wall and move in
            Random rand = new Random();
            int goThisWay = rand.nextInt(4);
            // Part 3
            // It now attempts to go in the direction mentioned in the comment after each of the first if statements.
            // I am not going to comment all four directions. Each is similar.
            // the nested if statements could be formatted as one using the && oporater but it is easier to read and comment with three seperate ones
            if (goThisWay == 0) { // attempt to go north
                if (maze[xPos][yPos - 1] == wall && !visited[xPos][yPos - 1]) { // checks to see if the spot that they are trying to break is a wall and not already visited
                    if (maze[xPos][yPos - 2] == wall && maze[xPos - 1][yPos - 1] == wall && maze[xPos + 1][yPos - 1] == wall) { // checks to see if the spots around the spot it is trying to move into are walls (not including the current spot)
                        pastX.add(xPos); // puts the current x coordinate into the past x coordinates to prepare to break the wall
                        pastY.add(yPos); // puts the current y coordinate into the past x coordinates to prepare to break the wall
                        generate(xPos, yPos - 1); // goes to the next spot where it will break the wall and try to move again
                        canGoSomewhere = false;
                    } else { // if the spot was not able to be turned into a hall because a spot next to it was a hall then it cannot move in this direction
                        cannotGoN = true; // makes sure the program does not try again so that it can eventually recurse
                    }
                } else { // if the spot was not able to be turned into a hall because it was an already visited wall
                    cannotGoN = true; // makes sure the program does not try again so that it can eventually recurse
                }
            } else if (goThisWay == 1) { // east
                if (maze[xPos + 1][yPos] == wall && !visited[xPos + 1][yPos]) {
                    if (maze[xPos + 2][yPos] == wall && maze[xPos + 1][yPos + 1] == wall && maze[xPos + 1][yPos - 1] == wall) {
                        pastX.add(xPos);
                        pastY.add(yPos);
                        generate(xPos + 1, yPos);
                        canGoSomewhere = false;
                    } else {
                        cannotGoE = true;
                    }
                } else {
                    cannotGoE = true;
                }
            } else if (goThisWay == 2) { // south
                if (maze[xPos][yPos + 1] == wall && !visited[xPos][yPos + 1]) {
                    if (maze[xPos][yPos + 2] == wall && maze[xPos - 1][yPos + 1] == wall && maze[xPos + 1][yPos + 1] == wall) {
                        pastX.add(xPos);
                        pastY.add(yPos);
                        generate(xPos, yPos + 1);
                        canGoSomewhere = false;
                    } else {
                        cannotGoS = true;
                    }
                } else {
                    cannotGoS = true;
                }
            } else if (goThisWay == 3) { // west
                if (maze[xPos - 1][yPos] == wall && !visited[xPos - 1][yPos]) {
                    if (maze[xPos - 2][yPos] == wall && maze[xPos - 1][yPos + 1] == wall && maze[xPos - 1][yPos - 1] == wall) {
                        pastX.add(xPos);
                        pastY.add(yPos);
                        generate(xPos - 1, yPos);
                        canGoSomewhere = false;
                    } else {
                        cannotGoW = true;
                    }
                } else {
                    cannotGoW = true;
                }
            }
            // End Part 3
            // Part 5
            if (cannotGoN && cannotGoS && cannotGoE && cannotGoW) { // if the program cannot break a wall in any direction
                canGoSomewhere = false; // this tells the program that it cannot recurse (the while loop will now stop repeating)
            }
            // End Part 5
        }
        // the recursion process
        if (pastX.size() > 0 && pastY.size() > 0) { // if the program is not done creating the maze because it is not yet back at (1, 1)
            int tempX = pastX.get(pastX.size() - 1); // store the x coordinate of the spot to recur to
            int tempY = pastY.get(pastY.size() - 1); // store the y coordinate of the spot to recur to
            pastX.remove(pastX.size() - 1); // remove the x coordinate of the spot to recur to from the list of past positions
            pastY.remove(pastY.size() - 1); // remove the y coordinate of the spot to recur to from the list of past positions
            generate(tempX, tempY); // recurse to the previous position
        }
    }

    /**
     * Gets the final maze.
     *
     * @return the char[][] maze.
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public char[][] getMaze() {
        return maze;
    }

    /**
     * Prints the maze to the screen.. See the parameters for formatting
     * details.
     *
     * @param withSpaces True to print the maze in a nice format. False to make
     * it so that all that is printed is the characters with line breaks to
     * start each new row. If true, after each column there is a space that
     * makes the maze closer to a square and easier to look at.
     */
    public void println(boolean withSpaces) {
        for (int row = 0; row < resolution; row++) {
            for (int column = 0; column < resolution; column++) {
                if (withSpaces) {
                    System.out.print(maze[row][column] + " ");
                } else {
                    System.out.print(maze[row][column]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Used to test the Maze object. Generates a new Maze object and prints it
     * to the screen using the println method for the Maze object formatted with
     * spaces to improve the visual appeal (the spaces are done in the
     * printToOutput method.).
     *
     * @param args
     */
    public static void main(String[] args) {
        Maze g = new Maze(25, '█', ' ');
        // prints out the maze
        g.println(true);
    }
}
