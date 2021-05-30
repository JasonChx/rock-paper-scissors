# rock-paper-scissors

Rock-Paper-Scissors game

## Business scenario

- The application implement Rock-Paper-Scissors game
- The game is played between a player and the application
- There could be many players with different names playing in parallel
- One player can play many rounds
- A round of the game is when a player ‘throws’ rock, paper or scissors shapes. The application, in turn, 
    generates an arbitrary choice of a shape and announces the winner of the round: player or the application.
- Each round of the game can have possible outcomes:
  - Draw (player and the application have same shapes)
  - Win or Loss for a player (see common rock-paper-scissors rules)
- The application can provide player’s statistics, e.g. rounds played, wins, losses…

### Prerequisites

* [Java 14 Runtime](https://www.oracle.com/au/java/technologies/javase/jdk14-archive-downloads.html)
* [MySql](https://www.mysql.com/downloads/)

#### Run

1. create local database 'rps' and 'rps_test'

2. Run Springboot LunchApplication

##### API
URL: /player/getList; 
RequestMethod: GET; 
Authorization: Basic Auth; Username: user; Password: password;
Return: a string list of player's name.

URL: /player/getStatistics; 
RequestMethod: GET; 
RequestParameter: player in String type; 
Authorization: Basic Auth; Username: user; Password: password;
Return: the given player's statistics, 
        in format of "Player '<player name>' played <rounds> round(s), wins: <number of wins> losses: <number of losses> and draws: <number of draws>"
        e.g. "Player 'John' played 6 round(s), wins: 2 losses: 3 and draws: 1"

URL: /game/play; 
RequestMethod: GET; 
RequestParameter: player in String type and shape in String type; 
Authorization: Basic Auth; Username: user; Password: password;
Return: the result of the current round, 
        in format of "Game played success: Player <player> thrown '<shape>' and application thrown '<shape>'
                      The outcome is <outcome>"
        e.g. "Game played success: Player bill thrown 'paper' and application thrown 'rock'
              The outcome is WIN"