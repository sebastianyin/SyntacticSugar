#Use Cases

##GAME EDITOR

###1. Overall settings: determine parameters such as number of lives, initial resources, difficulty (-> divide in speed of enemies, spawn frequency, author, etc.). 
**Front End**: When we click create new game, the first window in the wizard will allow the user to select the setting for the game. 

**Back End**: Changing GUI elements updates values of OverallSettings variables.

**Data**: OverallSettings object contained within Game object

###2. New game / Edit game:
**Front End**: Upon selecting “Editor” from the main menu, allow the user the option of creating a completely new game (composed of 1 or more levels) or editing 

**Back End**: Either generates a new Game Object or edits the variables in a current Game Object.

**Data**: New Game object which goes straight into editing OverallSettings. Edit Game object which goes straight into editing OverallSettings.

###3. Create new tower type

**Front End**: Switch to the tower tab, which displays the pane containing existing tower types, followed by a plus button. Click the plus button to launch a window that allows users to set the tower properties, which contains an “add” button. Click the add button to add the tower to the pane.

**Back End**: User selects from a list of base Tower classes. A factory pattern will generate a Tower object and then subsequent GUI selections will bind attributes to the Tower object. This new type is stored as a Tower option.

**Data**: New Tower object which is added to the list of Tower objects within a Game object. The new Tower can either be of ‘universal’ type or ‘current game’ type. If a Tower is ‘universal’ type, it will be accessible to all future Game objects which are edited. If it is ‘current game’ type, it is only accessible to the current Game object being edited.

###4. Create new enemy type

**Front End**: Switch to the enemy tab, which displays the pane containing existing enemy types, followed by a plus button. Click the plus button to launch a window that allows users to set the monster properties. Click the add button to add the monster to the list of available monsters. 

**Back End**: User selects from a list of base Enemy classes. A factory pattern will generate an Enemy object and then subsequent GUI selections will bind attributes to the Enemy object. This new type is stored as an Enemy option.

**Data**: New Enemy object which is added to the list of Enemy objects within a Game object. The new Enemy can either be of ‘universal’ type or ‘current game’ type. If a Enemy is ‘universal’ type, it will be accessible to all future Game objects which are edited. If it is ‘current game’ type, it is only accessible to the current Game object being edited

###5. Edit enemy properties

**Front End**: Switch to the enemy tab, right click an enemy in the pane to open its editor window (almost the same as the one used to create the enemy.) Change desired properties and click “save.”

**Back End**: GUI selections will bind attribute classes to the Enemy, giving that Enemy object different functionalities.

**Data**: Edit Enemy object which is contained in the list of Enemy objects within a Game object. Can either be of ‘universal’ type or ‘current game’ type, but this status cannot be changed once created. 

###6. Edit tower properties

**Front End**: Switch to the tower tab, right click a tower in the pane to open its editor window (almost the same as the one used to create the enemy.) 

**Back End**: GUI selections will bind attribute classes to the Tower, giving that Tower object different functionalities.

**Data**: Edit Tower object which is contained in the list of Tower objects within a Game object. Can either be of ‘universal’ type or ‘current game’ type, but this status cannot be changed once created. 

###7. Add enemies to a level

**Front End**: Switch to enemy tab, click individual enemy to add it to the queue of enemies to be added in the game once it runs. (Queue defines enemy-add order) 

**Back End**: By selecting on a type of Enemy from the UI, a factory creates an instance of that type at a particular point in the game board specified by the mouse.

**Data**: Add a Enemy object to a Level object which is contained within a list of Level objects. 

###8. Edit map walkable areas

**Front End**: the user will be able to select cells in the grid and designate them as environment cells (we will have different choices available to the user, such as rocks, platforms for towers, etc). The user will select a cell and then the type of cell from the tile tab on the right part of the editor. 

**Back End**: Depending on the type of cell specified, a factory will create an instance of that cell. That cell will be placed  at a clickable location in the game board. Depending on the type of cell collected, it will determine whether other objects will be able to walk over that space of the board.

**Data**: Call a method within a Path object which changes whether it is walkable or not. 

###9. Select spawn locations for monsters and destination/base

**Front End**: The user will be able to move a tile representing the spawn area and choose the edge from which enemies are spawned. The same can be done for the finishing tile. 

**Back End**: An EnemySpawner object will be created. The object will be placed on the map and the internals will contain a factory pattern for creating enemies of that type. A destination point will be chosen and the movement algorithms employed by that enemy type will lead them to that point.

**Data**: The Spawn objects extend the Path object and allows monsters to spawn from them or navigate toward them. 

###10. Create new environment element

**Front End**: Switch to the tile tab, which displays the pane containing existing enemy tiles, followed by a plus button. Click the plus button to launch a window that allows users to set the tile properties (such as: whether a tower can be placed on it, image, is it walkable, etc). Click the add button to add the tile to the list of available tiles.

**Back End**: A generic Tile object will have a number of attributes bound to it by the GUI controls.

**Data**: Create new Scenery object which is added to the list of Tile objects within the Game object

###11. Determine order in which monsters appear

**Front End**: Add enemies to the queue as seen in previous use case. To change the order after they’ve been added, drag and drop elements within the queue.

**Back End**: A list will index the enemies that have yet to appear in the game. When enemies are shifted around, an algorithm will generate a new list with the new ordering for the enemies.

**Data**: Two elements are simply switched in their places. The queue is essentially a linked list so this is a fast operation. 

###12. Add Tile/scenery to the map

**Front End**: The user will click on an element from one of the tab screens. This will indicate that the user has selected an element to place on the map. Once the cursor is in the map area, the user will use the mouse and click in the grid to place element. The grid will indicate the possible tiles for placing the selected grid element. 

**Back End**: Handled using a factory pattern. The Tile's point location will be set once the user clicks on the correct location in the grid.

**Data**: The map is a (hash)map that maps a Point to a Tile (or null if nothing has been added). Adding a Tile to the map simply maps the Point to the Tile. 

###13. Create new level

**Front End**: above the grid map, there will be a menu bar that allows the user to change the level displayed, as well as the possibility of adding a new level and deleting the level. If you click “Add Level”, a pop up screen will open asking for the level number. Select a valid number and the editor will display a default level, ready to be modified.

**Back End**: This will instantiate a Level object and place it in the list that contains all the different levels for the game.

**Data**: Create a new Level object which has its own Map, Players, Towers, Enemies, and Settings. This Level object is added to the list of Level objects in the Game object. 

###14. Delete levels

**Front End**: Next to the drop down menu for levels, there will be a + and - button indicating adding and deleting a level. Select a level from the drop down menu and click the - button to delete. 

**Back End**: This will remove an object from the Levels list of the Game object.

**Data**: A Level object is removed from the Game object. 

###15. Save current game 

**Front End**: On the bottom right hand side of the screen, there will be a series of buttons, one of which will allow you to save the settings for the current levels to an XML file named by the user. 

**Back End**: The game loop is paused. The data saving process is initiated to write the game information to XML.

**Data**: The current Game object (with its state) is written to XML and overrides the previously existing Game object. 

###16.  Delete current game - (irreversible)

**Front End**: In the top menu, select File -> Delete Game. Done! 

**Back End**:  The game player's list of games will remove the specified game.

**Data**: The XML that the Game is stored in is deleted. 

##CHARACTER EDITOR

###17. Properties panel
**Front End**: The user will be able to view the properties panel when a tile is clicked. This properties panel will exist on the right hand side of the screen and display properties that are relevant to the tile clicked.

**Back End**: This will bind an attribute to the Player or Enemy object specified.

**Data**: The Tile object stores its properties as instance variables. 

###18. Dynamic update of Properties panel
**Front End**: The user will be able to see the properties panel change dynamically with each different tile that is selected. For instance, a mushroom tile will show different properties settings than an enemy tile.

**Back End**: Options for different attributes will be stored in observable lists that will update depending on the type of object selected.

**Data**: The Tile object’s instance variables pertaining to its properties are updated. 

###19. Adjustable GUI elements
**Front End**: The user will be able to modify the properties of the selected tile directly, by adjusting the GUI elements on the panel (e.g. sliders, check-boxes). The tile should update visibly as the properties are being adjusted. 

**Back End**: The associated variables will be adjusted in the back end and check boxes will bind attributes to the object.

**Data**: The Tile object’s instance variables pertaining to its properties are updated. 

###20.Create Additional ‘Character’ Objects (Power ups/collectibles)
**Front End**: Launch Character editor window. Select attributes of the objects, as well as their value. 

**Back End**: This would be a custom attribute object that would be created as a composite of other attributes.

**Data**: The ItemEditor creates Item objects which are added to the list of Item objects in the Game object. 

##GAME ENGINE

###21. Place towers

**Front End**: The user will click on a tower from the tower screen. This will indicate that the user has selected a tower to place on the map. Once the cursor is in the map area, the user will use the mouse and click in the grid to place element. 

**Back End**: A Tower object is instantiated and the clicking on a particular Tile retrieves the location of that Tile and sets the location of the Tower to that point.

**Data**: The CurrentGame object’s map is updated by adding a Tower to the Tile object. 

###22. Load an XML game and play from first level

**Front End**: in the “Select game” screen, the user will have the possibility to choose what game he/she wants to play from a drop-down menu. In the “Welcome to my game” screen, the user will select “From Start”, which will launch the game from the beginning. 

**Data**: A Game object is loaded from XML and a CurrentGame object is created which stores the current ‘savegame’ with the number of coins, and the placement of the towers and how many lives remain. 

###23. Load an XML game and play from last saved level 
**Front End**: Similar to playing from the first level. In the “Welcome to my game” splash screen, the user will select Load level, which will play any of the unlocked levels. 

**Data**: A CurrentGame object is loaded from XML which contains the number of coins, the placement of the towers, and how many lives remain. 

###24. Kill an enemy, update available resources
**Front End**: Once the game engine detects this event, the enemy will disappear from the map (with an explosion possibly). The screen would then refresh the amount of available resources depending 

**Back End**: This would be due to the a DamageEvent that reduces the enemy's health to <=0. As a result, the game engine will loop and remove the objects that are dead from the game.

**Data**: The CurrentGame object’s attributes, which include resources, are updated. The enemy is removed from the CurrentGame’s Enemy queue. 

###25. A missile collides with an enemy
**Front End**: The GUI detects the collision in the Game Engine and updates the screen accordingly.

**Back End**: Collision between missile and enemy is detected. The missile adds a HealthChangeEvent to the enemy's list of events to be processed. The missile then destroys itself by removing itself from the pane.

**Data**: The Enemy (which is contained in a queue) and is contained within the CurrentGame object has its health reduced. 

###26. Game object moves outside the screen
**Front End**: When the image is completely off the screen, the GUI will remove its ImageView from the Pane. The Game Engine will then remove the object from the game. 

**Back End**: This object is removed from the pane.

**Data**: The GameObject is removed from the List/Queue in which it resides. 

###27. Enemy reaches base
**Front End**: Similar to when an enemy reaches outside the screen. The number of lives will then be decreased. 

**Back End**: A GoalEvent is created that performs a particular action due to the conditions that have been met.

**Data**: The lives counter which is an instance variable within the CurrentGame object is decreased in number and the Goal conditions are checked to and the corresponding win/lose method is called if the conditions are met (or unmet). 

###28. Complete level (no enemies reach final point)
**Front End**: Once the level is completed, the engine will pause. The player will then have the opportunity to place new towers/change location of existing ones. It will then click a button “Next level”, that will load the next level and start spawning enemies. 

**Back End**: The game engine will handle transitioning to the next level by setting the current level to the next one in the list.

**Data**: The level counter within the CurrentGame object is updated to reflect the advancement in levels. A resource reward may be granted, thereby adding to the instance variable. 

###29. Win Game
**Front End**: Check if a checkpoint has been reached, and if so, change the boolean to true and as a result, change to a win game splash screen or next level.

**Back End**: If the GoalConditions for the final level are met, the back end will communicate to the front end that the game has completed and the loop will stop.

**Data**: The CurrentGame object is deleted (as the game is completed). 

###30. Enemy spawn
**Front End**: In the queue set at the beginning of the game, set the order in which enemies spawn. While the game is running, the enemies will spawn in that order at previously set intervals. They will be removed from the queue and spawned at the beginning of the path.

**Back End**: Provide a Spawner object that takes a list of Enemy objects as a parameter. The Spawner object will handle churning out the specified enemies at the correct times.

**Data**: The CurrentGame object’s queue is updated as Enemies are popped from the queue. Any enemies on the screen are put into another queue so collision detection can be performed on them.

###31. Start a new level - game is paused until user decides he/she is ready
**Front End**: When the user finishes a level, the game engine will pause the game. The player will then have the possibility to deploy new towers or move existing ones. The game will not resume until the user decides to click on a button that will initiate the next level/wave of enemies.

**Back End**: Provide game.pause() and game.play() methods to be called from the front end.

**Data**: The CurrentGame object’s level instance variable is simply incremented by 1.

###32. New tower is unlocked
**Front End**: An additional tower icon becomes solid colored in the object editor pane. The user is given the ability to click on the tower during gameplay and place it on the map.

**Back End**: Detect that the unlock conditions have been satisfied. Mark the previously locked Tower as unlocked.

**Data**: The list of Tower objects contained within CurrentGame is added to.

###33. Keep track of available money
**Front End**: The game engine view will implement the observer pattern, observing the available resources in the game engine. It will then update automatically whenever this parameter changes. The game engine will take care of the logic on when we gain/lose money.

**Back End**: A Wallet object will store the current amount of money available to the player, and update accordingly.

**Data**: The resource counters within CurrentGame are updated.

###34. Determine path of enemies
**Front End**: The various objects will relocate their associated ImageViews according to the logic defined by the game engine.

**Back End**: Get the Tile on the Game Board that the Enemy is currently standing on. Find all of the Tiles surrounding that Tile which return isWalkable() = TRUE. Pick a Tile from this list of accessible Tiles based on a specific algorithm (random walk, minimize cost heuristic, etc.)

**Data**: Paths are chosen based on the neighbor tile with the lowest cost heuristic as determined by the IMoveable object’s movement algorithm.

###35. Use money to purchase towers (some towers will be greyed out until you unlock them)
**Front End**: The game engine will observe the amount of available resources, and it will make only the towers that the user can afford clickable. When the user deploys the tower, the game engine will update the amount of available resources, which will cause the view to update thanks to the observer pattern.

**Back End**: Keep a boolean variable to keep track of whether or not a specific GameObject is available for use in-game. If this bolean is false, then the image of the GameObject will be grayed out in the View. If it's true, then the game object will be clickable from the front-end (and thus, able to be placed on the board).

**Data**: Money is decremented from CurrentGame object while a Tower is added to a Tile contained within the Map (hash)map..

###36. Save score to leaderboard
**Front End**: This option appears only when the endGame() method is called. It will create a pop up window that will offer the player the option to save the score to the leaderboard. The view will then pass the score to the game engine to update the necessary files.

**Back End**: Report the score to the GUI

**Data**: Leaderboard object is updated to reflect top n scores.

###37. Upgrade tower to increase tower HP, atk power, or atk range (might also consider an upgrade tree)
**Front End**: The user will select the tower by clicking on it. The view will then display (on the lower part of the screen) the various options for upgrading the tower. The view will also display the cost for each upgrade. Once the user clicks on one of the updates, the view will send the input to the game engine, which will update the tower parameters and the available resources. The ImageView will then be updated accordingly, as well as the display of the available resources.

**Back End**: Change the value of the damage attribute of the tower (either a Weapon, or the Tower itself)

**Data**: The Tower, which is contained within a Tile element, has its Attributes edited.

###38. Towers have different element attack effect: ice tower - decrease enemy moving speed, magnet tower - immobilize enemy for a period of time, etc.
**Front End**: The image view of the tower is updated to reflect the power up/down.

**Back End**: Create a new type of Projectile that implements a unique status effect on the thing that it collides with.

**Data**: The Tower object’s Attribute element is changed to reflect its different abilities.

###39. A Player fires a Weapon
**Front End**: Display the new Projectile generated by the Weapon

**Back End**: Player that stores the Weapon calls Weapon.use(). The specific implementation of Weapon determines exactly what kind of Projectile is “fired” (ie. instantiated and added to the GUI), then returns up to the Player (the Player does not store a GameEvent). If the Weapon has a limited number of shots, then the Weapon decrements it’s available shots counter.

**Data**: A Projectile (extends Item) object is created.

###40. A Player drinks a Potion to restore health
**Front End**: Display “drinking” animation

**Back End**: Player calls Potion.use(). layer calls Potion.use(). The specific type of Potion defines what kind of GameEvent is created. The Player gets this GameEvent and adds it to it’s list of Events to be processed. The specific type of GameEvent (ie. a HealthChangeEvent) will define how it operates on the specific Attribute of the Player.

**Data**: A Potion (extends Item) object is removed and the corresponding health is added to a Player object's health Attribute.
