# My Personal Project

## Project Overview

This project contains an application that simulates the operation of a South American farm.
In the game, the player will have the chance to choose between different agricultural corps
and make the most profitable combination among them. As the game progresses, the player can 
expand the farm and eventually becomes the monopoly. 


This application is intended for people who has interests of economics and business operations.
As the game integrates economic concepts into the basic game operation, players would be able to
touch some economic knowledge when playing the game. This application interests me because I am 
also interested in the economic concepts and business operations. As being a BUCS student, it is
valuable for me to integrate the knowledge from both sides in the project.

## User Stories

- As a user, I want to add corps to my empty lands.
- As a user, I want to see the list of land I have.
- As a user, I want to add the annual income towards my total wealth.
- As a user, I want to purchase new land into my existing lands and specify land ID, land size, land profit, and corp.
- As a user, I want to have the option to save the player state to file.
- As a user, I want to have the option to load the player state from file.
- As a user, I want to be able to purchase land for the player and display the land in the panel at right
- As a user, I want to be able to add corps to my empty lands and display the corp change in the panel at right
- As a user, I want to be able to save the state of the application using the save data button
- As a user, I want to be able to load the state of the application using the load data button


## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by input the
  integer land ID and click the  " Purchase Land " button.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by click the
  "Plant Corn"/"Plant Cocoa"/"Plant Banana" button
- You can locate my visual component the left side of the GUI screen
- You can save the state of my application by click the "Save Data " button
- You can reload the state of my application by click the "Load Data" button


## Phase 4: Task 3

For this program, one thing I can do refactoring on is the searching algorithm on planting corps onto the empty lands. 
In the current searching algorithm, the program is using a for-each loop to search recursively for the empty lands. As
the player purchase more and more lands, the list will become longer and the searching process will inevitably taking 
longer time. For refactoring, I can add a sorting algorithm to place the empty lands in the front of the list and
therefore reduce the processing time.

