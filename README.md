# PlatformPanic
2D Platformer Time-Trial Game in Java
Created by Russell Wright

Requires the Java Runtime Environment version 8


=======================================================================================

Controls (Field)
  Left Arrow    - Move Left 
  Right Arrow   - Move Right
  A             - Jump
  Esc           - Menu/End Stage
  
Controls (Menu)
  Left Arrow    - Move Cursor Left
  Right Arrow   - Move Cursor Right
  Up Arrow      - Move Cursor Up
  Down Arrow    - Move Cursor Down
  A             - Confirm
  Esc           - Cancel/Previous Menu
  

=======================================================================================

Known Bugs

- The camera starts from the wrong spot when respawning in some levels
- When resetting some levels sometimes, some tiles get placed in the wrong locations
- When moving the cursor on the stage select screen, the game sometimes crashes
- When selecting a stage on the stage select screen immediately after exiting a level, the game sometimes crashes
- MP3 looping sometimes "skips", where no sound is heard for a brief moment
- When a MovingPlatform slides into a narrow opening with you standing on it and you fall, you will not be able to land on platforms.  Although the current level designs should not allow this to happen normally.


=======================================================================================

Changelog

v1.00 (beta/release/whatever I'm done) (uploaded 06/22/16)

- Disassembled latest version to revive the project
- Cleaned up everything to remove Eclipse warnings
- Packaged classes into proper modules
- Converted into a desktop application from applet
- Removed old insecure website integration
- No more accounts or leaderboards :( maybe again in the future
- Changed FPS to 60
- Added saving of local data
- Reduced number of worlds to 5 and number of total levels to 25 for completion's sake
- Changed World 1 tileset
- Added World 3 and 4 tilesets
- Added Level 2-4, 4-3, 5-4, 
- Redesigned 4-4, 4-5, 5-1, 5-2, 5-3, 5-5
- Added mp3 support via javafx MediaPlayer
- Added music tracks for each world that loop
- Added SpikeFill object (aka lava) with animated tileset
- Fixed MovingPlatform not killing you if it pushes you into spikes
- Fixed crash on stage select due to rendering LevelPicture race condition
- Fixed Spike tiles not appearing sometimes
- Removed Double Jump and rebalanced the game without it
- Fixed animation problem when landing from an AngledSpring jump


v0.83 (alpha) (uploaded 08/20/10)

- Added a map for each level on the Level Select screen, which is only displayed when selecting completed levels
- Added a new MovingPlatform subtype, the ControllablePlatform
- Added Levels 3-4, 6-1, and 6-2


v0.82 (alpha) (uploaded 08/16/10)

- Fixed bug where when riding a GuidedPlatform or MovingPlatform that shoves the player into a wall the player would be dropped down through the platform instead of being pushed smoothly across it and off of it
- Added Level 3-3 and 7-2, which are remakes of some level sections from Mega Man 2
- Added Level 7-3, which is inspired by some level design from Super Mario World
- Font color in the High score pages reflect the bronze, silver, and gold times for each stage
- Mouse support on LevelCleared screen 


v0.81 (alpha) (uploaded 08/08/10)

- Added "GuideRail" object type and "GuidedPlatform" platform type
- Added Level 5-5 and updated numerous other levels
- High score page is now embedded in the main site template
- Additional game data is saved to the Retrogrames user-database to populate the statistics page


v0.8 (alpha) (uploaded 07/27/10)

- Added "SpringBoard" and "AngledSpring" objects, which have been introduced to some levels
- Added a new original Ground tileset (World 5)
- Added a new LevelElement subclass, the "Spike", which has its own graphical tileset similar to Ground LevelElements, and it will kill the player instantly upon contact
- Added a death animation and sound effect
- Added new levels and began rearranging levels in an effort to have a more feasible difficulty progression throughout the game

   - Level 2-3 has been moved to be what is now Level 3-1, and has been replaced by a new level
   - Level 1-3 has been moved to be what is now Level 3-2, and has been replaced by a new level
   - Added Level 4-1, 4-2, 5-1, and 5-2
- Pressing "ESC" during gameplay will now immediately end the current stage
- Pressing "A" during the Level Cleared animation will now skip the animation and go directly to the menu
- Fixed a bug that prevented you from standing on one platform while touching another
- Fixed a bug where the player would sometimes clip through the ceiling when riding an upwards-moving platform instead of dropping through the platform
- Fixed MovingPlatforms such that an upwards-moving platform will pick up a nearby player who is standing on the ground, but it is not yet totally functional when picking up a player who is standing on a different platform
- Fixed some SQL security loopholes
- Implemented a high score page


v0.7 (alpha) (uploaded 07/18/10)

- In addition to checking who is logged in, the game will now save your records to the site's database when you leave or refresh this page
- A player who is not logged in will be recognized as "Default" and records will not be loaded or saved


v0.6 (alpha) (uploaded 07/15/10)

- Platform Panic will check who is logged in and load the user's records into the game from the site's database. This essentially allows users to save their progress through the game as well.
- Basic mouse support on Main Menu and Level Select screen
- Simplified Main Menu


v0.5 (alpha) (uploaded 06/27/10)

- First incarnation of Platform Panic on <old website>
- New original main menu and level select menu
- The Level Clock records gameplay time and awards medals based on times achieved
- The Level Clear Screen displays your current time, standing record time, and target times for medals
- Levels 1.1, 1.2, 1.3, 1.4, 1.5, 2.1, and 2.2 are available, but may end up revised or rearranged in later releases
