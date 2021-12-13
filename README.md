# COMP2042 Developing Maintainable Software Coursework
#### - Name: Liew Qian Hui
#### - Student ID: 20308255

## Project Name
COMP2042_CW_hcyql2

## Project Description
The project is about maintaining and extending a re-implementation of a classic retro game (Brick Breaker).  
In this project, git use, refactoring activities, addition of game features, documentation and explanation video have to be done in order to complete the project.

## Acknowledgement
The original codes of this project are from:
https://github.com/FilippoRanza/Brick_Destroy

## Table of Contents
1. [How To Download/Install the Project](#how-to-downloadinstall-the-project)  
2. [How To Play the Game](#how-to-play-the-game)  
3. [Git Use](#git-use)
4. [Refactoring](#refactoring)
5. [Additions](#additions)
6. [Documentation](#documentation)

## How To Download/Install the Project
Clone the remote repository to your own local repository or download the code as a zip folder.  
Open the codes using a Java IDE.  
Run the game by:  
Method 1: Run the GraphicsMain.java in src/main/java/brickdestroy/.  
Method 2: Run the COMP2042_CW_hcyql2-1.0-SNAPSHOT.jar in target/.  

## How To Play the Game
There are two modes in this Brick Destroy game.  
In normal mode, the player can skip level and adjust the ball's speed.  
In ranked mode, timer will be set and score will be recorded.  
Skipping levels and changing ball's speed are not allowed in ranked mode!  
There are 5 levels in each mode.  
1. Click the Start button to enter normal mode.  
2. Click the Ranked Mode button to enter ranked mode.  
3. Click the Exit button to close the game.  
4. Press space bar to start/stop the motion of the ball.  
5. Press A key or left arrow key to move the Player bar to the left.  
6. Press D key or right arrow key to move the Player bar to the right.  
7. Press ALT key + Shift key + F1 key to call the Debug Console. (Not applicable in ranked mode)  
8. The debug console is used to change the ball's speed, skip level and reset ball.  
9. Press Esc key to show Pause Menu.

## Git Use
The proof of screenshots (Liew_QianHui_GitUse.pdf) is attached in the submitted zip folder.

## Refactoring
1. Meaningful package naming/ organization
- All the classes of the project are stored in a package name called brickdestroy.
- All the classes are separated into different package names (model, view, controller) based on MVC pattern.
- Background image and high score file are stored in a package name called resources.
2. Basic Maintenance
- Encapsulate all the variables in every classes (eg. variable up, down, left, right in ball class, variable brick, ball, player in wall class, etc)
- Remove unused resources (eg. min_crack in brick class, parameters in wall constructor, etc.)
- Renaming variables (eg. ball: tmp -> tempBallFace, homemenu: menu_text -> exit_text, borderstoke -> borderstroke, player: movRight -> moveRight, etc)
- Remove duplicate codes (eg. create a new method (setPosition) to call duplicate codes in home menu, etc)
3. Design Pattern
- Singleton Design Pattern: Crack class is removed from Brick class, Level class is created by extracting methods from wall class
- Factory Design Pattern: Create Ball factory class and brick factory class to return different ball and brick objects based on their types.
4. Model-View-Controller(MVC) pattern
- Methods are extracted to new classes to implement MVC pattern.
- Model: Ball, ballFactory, Brick, BrickFactory, CementBrick, ClayBrick, Crack, Level, MagicBrick, Player, RubberBall, SteelBrick, Wall
- View: DebugConsole, DebugPanel, GameBoard, GameFrame, GameInfo, GameScore, GameTimer, HighScoreView, HomeMenu
- Controller: DebugConsoleController, GameBoardController, GameFrameController, GameInfoController, HighScoreController, HomeMenuController
5. Build tools
- Maven build tool is used. (pom.xml)
- Jar file is created. (COMP2042_CW_hcyql2-1.0-SNAPSHOT.jar can be found in target/)
6. Junit Tests
- Junit tests are created for all the classes in model package except for abstract ball and brick class.
- BallFactory, BrickFactory, CementBrick, ClayBrick, Crack, Level, MagicBrick, Player, RubberBall, SteelBrick, Wall

## Additions
1. Simple features
- Add Info Button at HomeMenu that links to a Info Page
- Add a background image at Home Menu
- Add rank mode button that links to the rank mode gameboard
- Add a high score button that links to the high score leaderboard
- Add arrow keys to control the movement of player (previously only Key A and D are allowed)
2. Highscore Pop Up, High Score Permanent File, High Score LeaderBoard
- The score feature is only available in the rank mode.
- There will be two score messages shown in the gameboard which are the player on-going score and the high score achieved read from the high score file.
- The player's score will be accumulated until GameOver or all walls destroyed.
- If the player manages to beat the high score, then the player will be asked for his/her name and the result will be stored in the high score file.
- High Score file stores the top 3 highest score.
3. Addition Level
- A level is added with two bricks: Magic brick and Steel brick.
- Magic brick (addition) will randomly change the ball's speed.(slow down or speed up)
4. Interesting addition (reward/penalty)
- Create a new mode for the game.
- Therefore, there are two modes of game, normal(original) and rank mode(addition).
- CountDown timer and highscore are set in rank mode and the player could not change the ball's speed and skip levels.
- Penalty: If the player could not break all the bricks in 5 minutes for each level, then GameOver regardless there is ball left.
- Reward: If the player manage to finish a level in 5 minutes, each second and number of balls left will be rewarded as 1 mark.

## Documentation
1. Javadocs
- Javadocs for all the classes are generated and are store in a file called Javadoc.
2. Class Diagram
- The class diagram (Liew_QianHui.pdf) is attached in the submitted zip folder.






