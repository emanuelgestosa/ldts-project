# LDTS_1501 - BLACKJACK

In this project you can simulate a BLACKJACK match, by making bets and playing with your cards and an interactive dealer.

This project was developed by *Domingos Santos* (*up201906680*@fe.up.pt), *Emanuel Gestosa* (*up202005485*@fe.up.pt) & *Igor Liberato* (*up202000161*@fe.up.pt) for LDTS 2021⁄22.

Visit the code <a href="../src">here</a>.

![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/docs/docs/game.gif)


## Jump to topic
* [Implemented Features](#features)
  * [Menus](#menus)
  * [Table](#table)
  * [Player](#player)
  * [Dealer](#dealer)
* [Design](#design)
  * [We want to seperate internal representations of information from the ways information is presented to and accepted from the user](#we-want-to-seperate-internal-representations-of-information-from-the-ways-information-is-presented-to-and-accepted-from-the-user)
  * [We only want one instance of Game running](#we-only-want-one-instance-of-game-running)
  * [We want our program to be compatible with several GUI and also our Viewers shouldn't access Lanterna directly](#we-want-our-program-to-be-compatible-with-several-gui-and-also-our-viewers-shouldn't-access-lanterna-directly)
  * [We want to make it easy for switching between the different menus and play states of the game](#we-want-to-make-it-easy-for-switching-between-the-different-menus-and-play-states-of-the-game)
  * [We needed a global access point for the Table and an unique instance of it running](#we-needed-a-global-access-point-for-the-table-and-an-unique-instance-of-it-running)
* [Code Smells](#code_smells)
  * [Duplicate Code](#duplicate_code)
  * [Temporary Field](#temporary_field)
* [Testing](#testing)
* [Self-Evaluation](#self_evaluation)


## FEATURES

#### Menus
- The game has several menus: main menu, configs menu, bet menu and end round menu. Each one of this menus is represented by a State.
 
#### Table
- The table is resposible for managing the game logic. Prepares new rounds (cleaning player's and dealer's hands and bet) and calculates winnings (or losses) after the end of each round.

#### Player
- **Hit** - Gives a card to the player.
- **Stand** - Passes the turn.
- **Double Down** - Gives a card to the player, doubles the bet and passes the turn.
- **Split** - Divides the player initial hand in two (requires an adicional bet). The player will then play first with the hand on the left and then with the one on the right.

### Dealer
- The dealer will take his turn after the player has passed or has a hand of value 21 or above. The dealer will simply draw cards until his hand value hits 17 or more.

## DESIGN

### We want to seperate internal representations of information from the ways information is presented to and accepted from the user.

- **Problem in Context.**
In order to keep our code base more organized and readable we decided to use the Model-View-Controller architetural pattern. This way, our code will be separated into 3 distinct parts.

- **The Pattern.**

We applied the **MVC pattern**.

- **Implementation.**

![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/docs/docs/mvc.png)

- **Consequences.**

By using the MVC pattern we ensure that:
- We have models that contain the core functionality and data.
- Views that display the information to the user.
- Controllers that handles information received from the user.


### We only want one instance of Game running.

- **Problem in Context.**

We didn't have any mesures to stop several instances of Game to be running, which we definitley don't want to happen.

- **The Pattern.**

We applied the **Singleton** design pattern. **Singleton** is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.

- **Implementation.**

![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/docs/docs/game-singleton.png)

- **Consequences.**

By using the **Singleton** desgin pattern we ensure that:
- The Game class has only a single instance.
- We have a global access point to that instance.
- The instance is initialized only when it's requested for the first time.

### We want our program to be compatible with several GUI and also our Viewers shouldn't access Lanterna directly.

- **Problem in Context.**

Our viewers should not have the need to know how to interact directly with Lanterna. We also didn't want our program to be dependent on the Lanterna framework, so we made it easy for a developer to use a different graphical framework without having to change any code in the viewers. 

- **The Pattern.**

We applied the **Adapter** pattern. The **Adapter** pattern is a structural design pattern that allows objects with incompatible interfaces to collaborate.

- **Implementation.**

![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/docs/docs/adapter.png)

- **Consequences.**

By using the **Adapter** desgin pattern we ensure that:
- The GUI interface and its Lanterna implementation are separated from the main program logic.
- We can easily introduce a new graphical framework without changes to the client code, as long as we implement the methods in the GUI interface.

### We want to make it easy for switching between the different menus and play states of the game.

- **Problem in Context.**

We have a ton of different menus and states of the gameplay (betting, playing without split, with split, etc), so we needed an easy and simple way to go back and forth between those.

- **The Pattern.**

We applied the **State** pattern. The **State** pattern is a behavioral design pattern that lets an object alter its behavior when its internal state changes.

- **Implementation.**

![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/docs/docs/state.png)

- **Consequences.**

By using the **State** design pattern we ensure that:
- Our code is nicely organized, with each State having its own class.
- We can easily add new states at any time without messing with the code of the other states.
- Keep the code simple by avoiding massive conditional statements.

### We needed a global access point for the Table and an unique instance of it running.

- **Problem in Context.**

We don't need (and shouldn't) have more than one instance of Table running at any given time. It is also very convinient having a global access point to it.

- **The Pattern.**

Once again, we applied the **Singleton** pattern.

- **Implementation.**

![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/docs/docs/table-singleton.png)

- **Consequences.**

By using the **Singleton** desgin pattern we ensure that:
- The Table class has only a single instance.
- We have a global access point to that instance.
- The instance is initialized only when it's requested for the first time.

------

## KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

### Duplicate Code

Throughout the whole project, sometimes, there are two or more code fragments that look almost identical. A large number of conditional expressions are present and perform similar code.
For example, on controllers like BetController, EndStateController, TableController... MenuController we always repeat the same conditions.
To solve this code smell, we could merge these operators into a single condition through "Consolidate Conditional Expression" and "Extract Method" to place the condition in a separate method with an easy-to-understand name.


### Temporary Field
On Player class, Hand splitHand is a private field that get its value only under certain circumstances, otherwise, it is empty.
To solve this problem, we could put splitHand and all code operating it in a seperate class via "Extract Class".

------

### TESTING

<a href="https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/tree/testing-report/docs/CoverageTestReport/test">Coverage Report</a>


![img](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1501/blob/master/docs/test-coverage.png)

#### Mutation Testing Report


### SELF-EVALUATION

- Domingos Santos: ?? %
- Emanuel Gestosa: ?? %
- Igor Liberato: ?? %
