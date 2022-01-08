## LPOO_<15><01> - <BLACKJACK>

In this project you can simulate a BLACKJACK match, by making bets and playing with your cards and an interactive dealer.

This project was developed by *Domingos Santos* (*up201906680*@fe.up.pt), *Emanuel Gestosa* (*up202005485*@fe.up.pt) & *Igor Liberato* (**@fe.up.pt) for LDTS 2021⁄22.

### IMPLEMENTED FEATURES

- **Menu** - A basic menu that lets you either start the game or quit by taking input from the user.
- **Display Cards** - Display on the screen the cards in the player's and the dealer's hands.

### PLANNED FEATURES
  
- **Turn** - The player will decide which startegy is the best, accordind to his cards. He can choose between *Split*, *Double*, *Stand* & *Hit*.

### DESIGN

- **Problem in Context.** 
  
We didn't have any mesures to stop several instances of Game to be running, which we definitly don't want to happen.
  
- **The Pattern.** 
  
We applied the **Singleton** design pattern. **Singleton** is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.
  
- **Implementation.** 
  
![img](https://imgur.com/a/2jyeINi)
  
- **Consequences.** 
 
By using the **Singleton** desgin pattern we ensure that:
- The Game class has only a single instance.
- We have a global access point to that instance.
- The instance is initialized only when it's requested for the first time.

------

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

**Long Method** - Game.run() - Currently this method has two different functionalities (running the menu and the game itself). These two should be separated into individual methods.
**Long Class** - Table - This class does most of the heavy lifting for the flow of the game. As the code grows further we might need to extract some subclasses or interfaces to keep it from becoming more bloated.

------

### TESTING
  
We currently only test the methods in LanternaGUI with mockito. We haven't checked coverage or mutation testing for the time being.
