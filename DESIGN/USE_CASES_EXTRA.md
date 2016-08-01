#Use Cases Extra

##1. Create a player object and set its initial location on map

Within game authoring environment, the user selects a tile and then chooses Player object image from Player editor tab and sets Player’s properties before adding Player to a PlayerQueue. Once user selects ‘Set’, tile’s instance of PlayerQueue is updated to store newly generated PlayerObject. Within game environment, when game begins, the instance of Player immediately spawns at tile location. 

##2. Create spawner and set it at a tile spawn location.  
In the spawner editor tab, select an image to use, set spawner properties, such as maximum health, damage power, and spawning number, then click the add button to add the newly created spawner to a temporary queue. Once the spawner is in the temporary queue, select a tile on the map and click the set button in the editor pane. The spawners will spawn from this tile, which is indicated by setting a new image to the tile itself. To view the spawners associated with a tile, right-click on the tile and select Edit. 

##3. Add health attribute to GameObject

When adding a new GameObject to a tile, user has option to set the object’s properties once an ImageView has been selected from the list. The user clicks ‘add’, which adds object to temporary ObjectQueue. Once ‘add’ is clicked, scene is switched and new pane appears one right side of application window, which allows user to set individual properties for specific object. In this case, a user could set a Spawner’s health. Once player clicks ‘set’ button, tile’s ObjectQueue is updated with reference to newly generated object.

##4. Add damage attribute to GameObject
When adding a new GameObject to a tile, user has option to set the object’s properties once an ImageView has been selected from the list. The user clicks ‘add’, which adds object to temporary ObjectQueue. Once ‘add’ is clicked, scene is switched and new pane appears one right side of application window, which allows user to set individual properties for specific object. In this case, a user could set a Spawner’s damage component. Once player clicks ‘set’ button, tile’s ObjectQueue is updated with reference to newly generated object.

##5. Set a walkable path on the game map 
The map will be initialized with an entire map of scenery tiles. To set a walkable path, the user can either select one tile at a time and set it to the preferred path tile or click on a group of path tiles and set those to the same preferred tile type. To set a walkable path, sequential path tiles must be set to path tiles to get from the initial position to the end goal. 

##6. Set a player’s death condition

When adding a new GameObject to a tile, user has option to set the object’s properties once an ImageView has been selected from the list. The user clicks ‘add’, which adds object to temporary ObjectQueue. Once ‘add’ is clicked, scene is switched and new pane appears one right side of application window, which allows user to set individual properties for specific object. In this case, if the user chooses to add a Player object, they can set it’s death condition (minimum health). Once player clicks ‘set’ button, tile’s ObjectQueue is updated with reference to newly generated object.

##7. Two objects collide
This is handled in the CollisionManager. This class contains a single static method that takes in a unmodifiable collection of ISimpleObjects. It then runs an O(n^2) check to look for collisions by seeing if the objects’ bounding boxes overlap. If an intersection occurs, the “actor” checks its collision map for the events associated with the “target.” It then gives those events to the target and target's’ attributes are appropriately affected.

##8. Set a enemies’ death condition

When adding a new GameObject to a tile, user has option to set the object’s properties once an ImageView has been selected from the list. The user clicks ‘add’, which adds object to temporary ObjectQueue. Once ‘add’ is clicked, scene is switched and new pane appears one right side of application window, which allows user to set individual properties for specific object. In this case, if the user chooses to add a Spawner object, they can set it’s death condition (time limit or minimum health). Once player clicks ‘set’ button, tile’s ObjectQueue is updated with reference to newly generated object.

##9. Set a location reached condition

This will be a Condition class. All of the conditions are located in the GameManager. They loop through an unmodifiable collection of objects to check if their conditions are met. This particular location will check for PlayerObject bounding box coordinates to see if they are in the vicinity of the location specified.

##10. Set a time survived condition

Similar to #9 except in that the condition keeps track of the number of frames that have passed.

##11. Set events for collision on specific type of GameObject

The authoring environment will allow the user to choose what types of collisions an object will apply to another type of object.

##12. Remove element from the view

Within game engine, if element is to be removed from view, the object must first be removed from the game ‘universe’. Game engine checks each element’s state at each frame and if death condition is met, element is removed from ‘universe’. GUI which relies on game engine’s current state of ‘universe’ updates its view whenever ‘universe’ is updated, which in turn results in element not being displayed in view.

##13. Choose between launching game or editor

When program is initiated, a splash screen appears, which allows user to either ‘Play a Game’, ‘Create a New Game’, or ‘Edit an Existing Game’. If the first is selected, the game environment GUI is launched, which loads the user selected XML file and fills the GUI components accordingly. If other two options selected, then either new game authoring environment GUI is instantiated (create new game) or a game authoring GUI is instantiated and its components filled according to the user selected XML file. 

##14. Click on an object in the game map and edit its properties. 
Right click on the object in the game map and select the Edit option. This will bring the user to a pane displaying the object’s properties that the user can adjust directly on screen. The user can then save these new property values and continue adding objects to the map as before. 

##15. Select all tiles in game map

Game authoring environment has ‘Select All’ button, which loops through the GridPane and updates opacity of each tile to illustrate that it is ‘selected’. 

##16. Update the position of an object in the view 

Game engine checks each element’s state at each frame and updates the ‘universe’ accordingly (i.e. updates position of each object). GUI which relies on game engine’s current state of ‘universe’ updates its view whenever ‘universe’ is updated, which in turn results in elements in the view being updated (i.e. object in view moves).

##17. View list of spawners associated with spawn location. 

Each Tile object has an associated list of SpawnerObjects. Whenever a user selects a tile, they can choose to add a new SpawnerObject at this location. User selects Spawner image and clicks ‘add’. This triggers scene switch, which allows user to set individual properties specific to that object. Once user selects ‘set’, the newly generated object or objects is/are added to the tile’s list of SpawnerObjects. If user right-clicks on tile, a context menu opens up, which gives them choice to ‘Edit properties’. This triggers right side of application window to display scene with tile’s properties, which includes all of its current associated SpawnerObjects. If tile has Spawners, the image of each Spawner in list will appear in this pane, along with number of instances of that Spawner.

##18. Deselect all tiles in game map
If a collection of game tiles are selected, the user can click on the Clear All button on the left hand side of the GUI in the TileTools menubar. This will de-select all of the ‘selected’ tiles on screen.

##19. Start a new game
The user should be able to start a new game from the splash screen. This will clear all previous data to restart the game at level 0. 

##20. Visualize the help file for a game
The user should be able to launch the help file from the game authoring environment, or from the splash screen, or while the game is paused. This help file can be located from a default local file, and can be updated from an online source. 

##21. Switch between levels using the GUI. 
On the left hand side of the GUI, under the Level Tools menu bar, there is a drop down menu that contains a list of the names of all of the levels that have been started or that are being currently edited. The user will only be able to see one level on the screen at once, but should be able to switch to a different map by selecting a different level from the drop down menu. 

##22. Set enemies movement to track

This will be handled by the AIMotionAttribute which implements ISimpleMover, an interface that sets how an enemy will move about the Universe.

##23. Attach a weapon object to a tower.
In the TowerEditor tab, there will be a box that allows the user to select a weapon to attach to the current tower. The user will be able to see all of the currently available weapons in a drop down menu and select one to be used by the tower being created. 

##24. Edit multiple tiles at once (select tiles to be edited individually or select all)

Whenever a tile is ‘selected’ by the user via a left-click, it is added to a list of currently selected tiles. If a user wants to select 20 tiles to all be the same type of path tile, they select each one and then choose select tile editor tab. They choose ‘path’ option in drop-down menu and then select image. Once ‘add’ button has been clicked, each tile in the list of current list of tiles is updated according to this new change (in this case, each tile’s associated ImageView is changed).

##25. Create multiple instances of spawner object at once to be spawned from same location
When creating a spawner object, set the spawn number to be the number of identical spawners wanted from one location. Once this is done, the same procedure for setting an individual spawner to a tile will be the same. 

##25. Save individual level XML to a directory of level XML files. 

A game folder will a 'level' folder which has multiple 'level' files, each of which is a `Level` object xstreamed into XML.

##26. Create usable item for player
In the ItemEditor menu in the tab pane, select attributes and an image that will be associated with the item and set those values as needed. Once that is done, the user will be able to click an add button that will add the new weapon to the current library of items available to the user. 

##27. Tile has new state when the mouse hovers over it. 
When the user hovers over a specific tile, there should be a UX response to show that the tile is being interacted with. This could be a bordered tile with a slight colour overlay, or a tile that slightly expands in size.

##28. Identify which tiles are spawner locations

Whenever a SpawnerObject is added to a tile, the object is added to the tile’s SpawnerObject list and the tile’s associated ImageView is updated. This new ‘Spawner’ ImageView is very distinct and allows user to easily identify which tiles on map are spawner locations.

##29. HUD utility that keeps track of important game statistics

This class will exist in the GameManager and will keep track of the statistics relevant to the level. It will produce an observable list for the front end to display.

##30. Path-finding utility

PathFinder object which takes a Map<Point, Boolean> and a start and end point, and finds the shortest path from start to end point. The booleans denote traversable points.

##31. Resource engine utility

This utility will simplify how resources are used by the different parts of the engine.

##32. Different tabs should be able to animate by fading transition.

When the user clicks a different tab in the TabPane, the scene should be able to transition from one scene to the next by first fading out the original scene, and fading in the new scene. This feature should be abstracted enough to apply for any scene transition that occurs.

##33. Menu Bars should be able to animate by a drop down transition.

- When the user clicks on the menu button at the top of the game authoring environment, the menu should be able to drop down with a transition effect. When the user clicks away, the menu should be able to animate upwards to hide the menu content.

##34. User right-clicks on tile and context menu is opened

When the user right clicks a tile, a context menu pops up right next to the cursor, allowing the user to select multiple options e.g. to edit the tile or add an object. These options will change sidepane of the game authoring environment. 


##35. Right clicking tile can change properties.

The tile that is right clicked will display a context menu that has an option to populate the properties on the right pane. 

##36. The properties side pane will dynamically change depending on the relevant properties of the type of tile being selected.

When the user chooses different types of tiles, e.g. path vs scenery, the properties pane will update according to what properties are relevant to the tile.

##37. CSS Utility

A CSS Utility should be able to dynamically apply and remove CSS tags to GUI elements to change their properties.

##38. Select tower from pre-determined list and place on map during game play

While still in the game editor, the user will be able to set a list of towers will be associated with a certain level. In the game playing mode, the user will be able to see all of the towers available for that level and be able to click on the tower, click on a tile, and set the tower to that tile. 

##39. Create items, such as powerups, that can be placed on the map and collected during game play. 

From the list GameObject items that are currently available to the user, click on an item, click on a game tile and set that item to that tile. 

##40. Set tile to be destination tile for spawners

When a user right-clicks on a tile, they have the option to ‘Edit Properties’. This changes scene within right side of application window to display the tile and its associated objects’ properties. One of these objects properties, specific to SpawnerObejcts, is a double, which identifies the object’s destination point. The user sets this destination to either be a specific tile or a Player object (i.e. if game is PacMan), which the associated SpawnerObject will try to move (according to its path finding algorithm) towards during a game run.

