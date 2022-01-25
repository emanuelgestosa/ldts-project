# LDTS_1501 - BLACKJACK

In this project you can simulate a BLACKJACK match, by making bets and playing with your cards and an interactive dealer.

This project was developed by *Domingos Santos* (*up201906680*@fe.up.pt), *Emanuel Gestosa* (*up202005485*@fe.up.pt) & *Igor Liberato* (*up202000161*@fe.up.pt) for LDTS 2021⁄22.

Visit the code <a href="./src">here</a>.

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
* [Code Smells](#code_smells)
  * [](#)
  * [](#)
  * [](#)
  * [](#)
  * [](#)
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

We applied the MVC pattern.

- **Implementation.**

![img](MISSING)

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

![img](https://i.imgur.com/sHrW6m2.png)

- **Consequences.**

By using the **Singleton** desgin pattern we ensure that:
- The Game class has only a single instance.
- We have a global access point to that instance.
- The instance is initialized only when it's requested for the first time.

------

## KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

### Long Method
Some functions like STEP on BetController have a lot of code lines. It can be fixed by calling other smaller functions that execute those lines, making it easier to analyze and to debug.
- **Long Method** - Game.run() - Currently this method has two different functionalities (running the model.menu and the game itself). These two should be separated into individual methods.

- **Long Class** - model.game.table.Table - This class does most of the heavy lifting for the flow of the game. As the code grows further we might need to extract some subclasses or interfaces to keep it from becoming more bloated.

------

### TESTING

We currently only test the methods in gui.LanternaGUI with mockito. We haven't checked coverage or mutation testing for the time being.

### SELF-EVALUATION

- Domingos Santos: ?? %
- Emanuel Gestosa: ?? %
- Igor Liberato: ?? %
