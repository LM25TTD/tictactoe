# TicTacToe game
Simple TicTacToe game developed in Java. All interaction is based on console.

# Building the application

This project is based on Apache Maven as build system and can be built from command line using the mvn executable. You can download Maven standalone installation from link:

https://www-us.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.zip 

Also remember, you need to have a valid JDK installation to perform the build and execution. 

The full code is inside the tictactoe folder, so using the command line from some terminal, you must enter inside this folder, like the example:

![img1](https://raw.githubusercontent.com/lm25ttd/tictactoe/master/img/img1.png)

At this point, you must call the “mvn” tool (from downloaded location or from path if is already configured) with the goal “package”, as below:

![img2](https://raw.githubusercontent.com/lm25ttd/tictactoe/master/img/img2.png)

After the build process and success, a .jar file will be generated inside the folder “target” located at the current folder:

![img3](https://raw.githubusercontent.com/lm25ttd/tictactoe/master/img/img3.png)

You are free to move or copy this .jar to any location where you can run it.

#	Running the application

To run the game properly, you must put the “.jar” file together with the “game.properties” file at same folder. After this, you are free to change the game settings inside the file and finally run the application using the “java -jar”command, like below:

![img4](https://raw.githubusercontent.com/lm25ttd/tictactoe/master/img/img4.png)

The game will run following the rules of the classic TicTacToe game and also follows the rules described at test description.

# Software design decisions 

The software was developed based on the classical MVC architecture.

The classes Player (and subclasses), Match (IMatch) and Board (IBoard) are placed to Model layer; The classes GameController is placed to Controller layer and finally the IView with ConsoleView classes are placed to View layer.

The entities and relation between them are modelled to reflect a real-word TicTacToe game round. We have Players who access a Board (a matrix) and put its symbol on some vacation line/column. A Match aggregate these two and each new Match requires a new Board and new Players. To orchestrate this dynamic, a game controller is necessary.

Some good practices of software development are applied, like interface-oriented development to keep the components weakly coupled and dependency injection strategy, with both concepts applied it`s possible to create unit tests without difficult, just injecting mock classes when necessary.

The main business rules are unit-tested, so we have 27 test cases which run automatically during the Maven build process. If some test fails, the build is interrupted with error.

Below we have a UML class diagram that describes the class/interfaces relations.

![img5](https://raw.githubusercontent.com/lm25ttd/tictactoe/master/img/img5.png)


