# Semester-Project-Legends-Of-Quintessence

## Team: 
Tim Lenahan, Mohammad Ozaslan, Jennifer Riley

Presentation: https://drive.google.com/file/d/1BL8GwGI2lYs0mRmJz4A72CsC8D1Eh3xS/view?usp=sharing

Project 6 WriteUp: https://docs.google.com/document/d/1Dox8toPxczAf1hjw4r7djgn3b1aki6PDgiEE51ByJVY/edit?usp=sharing

## Game Description 
A card game where you use your cards to attack your opponent’s base and to defend your own base. The object of the game is to get your opponent’s base’s hit points down to zero. Legends of Quintessence bases much of it’s game logic on The Legends of Runeterra card game. It is a strategy based game where you select cards to play while conserving resources, attacking your opponent, and defending your own base.

You can find game rules here: https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/blob/main/LegendOfQuintessenceRules%20(1).pdf

## Game Creation
We used Jmonkey Engine and NiftyGUI to create and run our game.
Jmonkey Engine: https://jmonkeyengine.org/

## Dependencies
[jMonkey SDK 3.2.4](https://github.com/jMonkeyEngine/sdk/releases/tag/v3.2.4-stable-sdk1)
[Nifty GUI](https://javadoc.io/doc/com.github.nifty-gui/nifty/latest/overview-summary.html)

These are the versions of the jMonkey SDK and Nifty API we used in development. Even though the program may run on other versions these are the versions recommended.

## Installation 

Install [jMonkey SDK](https://github.com/jMonkeyEngine/sdk/releases/tag/v3.2.4-stable-sdk1) stand alone uses Netbeans IDE.
Install [jMonkey library]() in Intellij or your favorite Java IDE.

After cloning our [repo](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence.git) and executing executing a build of our program any necessary Java libraries should be installed and the [LegendsOfQuintessence.java](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/blob/main/src/LegendsOfQuintessence/LegendsOfQuintessence.java) will initialize the program.

## Object Oriented Designs

#### Facade:
Because of the complexity of our programs subsytem (the jMonkey engine) we are relying on a Facade like pattern within our [gameplay](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/tree/main/src/LegendsOfQuintessence/gameplay) which helps to decouple our game logic from the game engine itself. 

#### Decorator: 
We are relying on Decorations of the [Nifty ScreenController](http://nifty-gui.sourceforge.net/projects/nifty/apidocs/de/lessvoid/nifty/screen/class-use/ScreenController.html) to enable functionality on our [xml screens](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/tree/main/assets/Interface) [StartScreen](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/blob/main/assets/Interface/StartScreen.xml) and [Board](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/blob/main/assets/Interface/Board.xml).

#### Builder: 
Upon examing our program you will see **Build** or **Builder** used in a lot of places. The reason for this is because our program relies upon the [Nifty builder package](http://nifty-gui.sourceforge.net/projects/nifty/apidocs/de/lessvoid/nifty/builder/package-summary.html) which allows our program to dynamically add xml elements to our current jMonkey rendered xml screens. As a card game needing variations of cards we are working toward abstracting how the cards are built themselves so as create various types within a factory pattern.

#### Strategy: 
Throughout the program we use simple strategy patterns to take some of the heavy lifting, which occurs with GUI element interactions, out of classes and methods and encapsulates them in their own class such as [SimpleElementsFactory](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/blob/main/src/LegendsOfQuintessence/player/SimpleElementsFactory.java) (apologies for the misleading name, even though future builds could use this to create varying types of our player 1 and 2 of our two player game). SimpleElementsFactory uses our [PlayerElementsBuilder](https://github.com/CSCI4448-JMT/Semester-Project-Legends-Of-Quintessence/blob/main/src/LegendsOfQuintessence/player/PlayerElementBuilder.java) to extract the xml elements from the board xml screen and to build our players' cards into the xml returning references to both for our player objects for manipulation throughout the game.
