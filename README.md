# Demaia-RPG

This repository is an archive of past work. This is not a new project, and is being uploaded to GIT for posterity

The demaia RPG is a personal project developed in java. It consists of an RPG system developed from scratch, containing a functional overworld map, dialog system, inventory, and battle system. Only the first two zones have been implemented.

Map:

Maps are created in a custum-made map editor that allows for the placing of tiles, objects, and NPCs onto a set size grid, using self-made assets
Individual screens can be saved in files, or loaded from there
Map files can be created by arranging screen names onto a grid, as well as setting map borders
Maps can be loaded into the game, with each screen connecting to another screen when the player moves off the edge.
Loading zones can be added to connect one map to any other saved in the data
Interaction points can be written into the map file with special syntax, prompting it to load dialog trees from the associated dialog file


Dialog:

A unique psuedo-language was created in order to handle the dialog system in the game. Input is written in text format, and interpretted by the progarm in order to create the in-game dialog.
Commands created and recognized:

  new_x / end_x.
    Starts / ends dialog number x. The number is used to reference this dialog from the map file when setting interaction points.
    
  print_x_y_s:
    A dialog output where s is the text to be displayed.
    X represents the character who will be speaking and y represents the emotion they should display. These are used together to get the character portrait.
    
  check_x_c_y:
    A conditional operation.
    X represents a flag within the player's save file, C is a condition (<,=,>), and y is a hard-coded value to compare to.
    If the condition is true, the dialog continues. If false, the dialog is skipped up until the next check statement.
    
  acheive_x_y:
    Marks progress in the game.
    x represents the flag in the user's save data that should be updated, y represents the value it should be updated to.
    
  fight_x_y:
    Starts a scripted fight, with enemies set in the parameters.
    If the player wins, the dialog continues.
    
  heal:
    Fully heals the player


Battle system:
  Traditional turn-based combat.
  
  Attacks work on an elements system, where each attack has an element (slashing, poison, fire, etc.). Rather than having defence, each combattant has a multiplier on the damage they take from each element. For example, a swarm of bees is vulnerable to poison, but trying to slash at it with a sword is not effective.
  
  All attacks have set priority (1-5) that multiply the user's speed, allowing for some attacks to be weak and fast, while others are strong and slow
  
  All attacks have unique animations. While the impages are unchanging, each combattant's sprite has a set "hurt point" that the animations are centered on, allowing them to look like they connect.
  
  A large buff / debuff system is supported, with most attacks interacting with different statuses in different ways, in order to ensure all options are dynamc. For example, bite is a simple, high-priority, low-damage attack, but also transfers all poison and nausia from one target to the other.
  
  All attacks are kept in a list, such that new enemies / weapons can be created by selecting attacks from the list, rather than re-coding every time.
