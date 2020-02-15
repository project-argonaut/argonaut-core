Argonaut Core
=============
[![Build Status](https://travis-ci.org/project-argonaut/argonaut-core.svg?branch=master)](https://travis-ci.org/project-argonaut/argonaut-core)
[![GitHub issues](https://img.shields.io/github/issues/project-argonaut/argonaut-core)](https://github.com/project-argonaut/argonaut-core/issues/)  
**Argonaut is built using Bukkit API Version 1.15, and that will not be changed**  

About
-----
Argonaut Core aims to provide a lot of fundamental functionality to a spigot Minecraft server to make
creating an RPG no longer require a full development team and a few thousand dollars. Below is a list of
features that Argonaut Core will have, currently the list is not final and will be added/removed from 
a lot while the plugin is still in early development.
- Modules
    - Plugins like Bukkit
    - Custom functionality
- Regions
    - Ambient particles
    - Entity spawners
    - Specific effects
    - Random events
- Dungeons
    - Ambient particles
    - Bosses
    - Entity spawners
    - Resetting
- Quests
    - Quests that refresh
    - Timed quests
    - One time quests
    - Multiple objectives
    - Multiple quest steps
    - Class requirements
        - Race
        - Class
        - Level
        - Quests
    - Rewards
        - Vault rewards :puke:
        - Command execute rewards
        - EXP Rewards
        - Item Rewards
- NPCs
    - Shops
    - Quest givers
    - Enemies
    - Guards
- Companions
    - NPCs
    - Animals
    - Trust levels
- Abilities
    - Grimoire
    - Passives
    - Auras
    - Particles
- Stats
    - Strength
        - Fury
        - Attack damage
        - Knockback resistance
        - Physical critical damage
    - Intelligence
        - Mana
        - Magic resistance
        - Magical damage
        - Magical critical damage
    - Agility
        - Stamina
        - Move Speed
        - Jump Height
    - Constitution
        - HP
        - Physical resistance
        - Reduced food consumption
    - Finesse
        - Critical chance
- Leveling
    - EXP
- Classes
    - Skill trees
    - Weaknesses
    - Unique abilities
    - Class requirements
        - Race
        - Level
        - Quest
- Entities
    - Bosses
    - Drops
    - Abilities
    - Particles
    - Weaknesses
- Races
    - Starting stats
    - Stat growths
    - Spawn points
    - Weaknesses
    - Sub-races

More information on the Argonaut system are below:

### Why make this?
When I was in middle school playing Minecraft with my friends we had found a server called
[Savage Realms RPG](https://savagerealms.fandom.com/wiki/Savage_Realms_Wiki) which is no longer
in operation. After the server stopped running I wanted to make my own server with the same
feature set as that one, but I soon realized that the plugins required were very complex or
just hadn't been updated to newer versions. So now, several years of development experience
later I have decided that I want to develop a plugin that was built specifically for making
these RPG experiences, having Hypixel Skyblock re-ignite my passion for the 'genre'. I hope
that with the development of this project that I can indirectly lead to the creation of several
other RPG servers that provide their own unique and interesting takes on the genre.

### Goals
The primary goal of Argonaut is to allow for the creation of RPG servers with nothing but this plugin
and some modules to extend it. Originally I had planned for this plugin to simply provide the regions,
dungeons, entities, NPCs, and quests functionality, while moving the other features to optional modules
that you can download and install. But eventually I had figured that was too complex for out of the box
development, so to make the plugin easier to setup I have decided to merge them all into the core plugin.

### Modules
Modules are the backbone of Argonaut and they are what make it viable for larger servers with dedicated
development teams to use. Modules allow developers to modify and add on to the deeper functionality of
Argonaut, while not potentially interfering with other modules. As a response to how difficult it normally
is to develop an add-on/patch for other bukkit plugins without decompiling and modifying the source.  
  
Argonaut comes with several internal modules as well, some disable-able while others aren't due to them
being important to the core functionality of the plugin. I tried to keep all of the major features listed
to their own self-contained modules that can be disabled.

### Regions
Argonaut is built around the idea of having one massive map static map that is the world the RPG is set in.
As a way to ensure that configuration files and storage doesn't get too confusing or messy the regions system
was developed. A region is one part of the world map. An Argonaut world can technically be all one region
but that would make it rather difficult to keep track of what's where, as well can limit nuanced functionality
quite substantially.  

#### Region Features
- Can be configured to announce their name every time upon entrance, only the first time they're
entered, or never.
- Can be nested, for instance you can have a region `The Shire` and there can be a region inside
`The Shire` called `Michael Delving`, what would happen is that `Michael Delving`'s custom logic
would happen on top of `The Shire`'s custom logic.
- NPCs can be region specific, obviously. This is the main allure of the regional system, this
allows you to assign NPCs to specific regions without polluting a main world file.
- Regions can have ambient effects. What does this mean? You can configure a region to have custom
particles playing all throughout the region to add to the ambiance. Have a region that is really warm?
Configure it to have smoke coming off of a specific block type, etc.
- Regions can be marked as spawnable regions. What this means is that monsters you configure will spawn
in this region on spawnable blocks, if the provided constraints are met.  
  
**TODO**

Setup
-----
When configuring permissions, please do not remove the `argonaut.command` permission from any users
you wish to be able play with argonaut. It will not grant permission to configuration commands, it
will only provide access to the commands necessary for argonaut to manage the player.

Permissions
-----------
**TODO**

Commands
--------
Some commands will remain internal, as they are not intended to be executed manually by the end user.
- `/argonaut version` - Prints the current version of the argonaut system.
- `/argonaut region create <name>` - Creates a new region.
- `/argonaut region delete <name>` - Deletes the given region.
- `/argonaut region info <name>` - Displays the information of the given region.  
**TODO**

Developing Modules
------------------
**TODO**

Common Errors
-------------
**TODO**

TODO
----
- [x] Finish TODO list
- [x] Verify feature list matches TODO list
- [x] Initial plugin base
- [x] Data-loader API
- [ ] Inventory Menu API
- [ ] Book GUI API
- [ ] Sign GUI API
- [ ] Anvil GUI API
- [ ] Hologram API
- [ ] Permissions API
- [x] NMS Version interfaces
    - [x] v1_15_r1
- [x] Module API
    - [x] Plugin Modules
    - [ ] Internal Modules
    - [x] Loading
    - [x] Dependencies
    - [ ] Service injectors
- [ ] Command API
    - [ ] Service injectors
- [ ] Advanced Listener API
    - [ ] Event bus hooking
    - [ ] Service injectors
- [ ] Regions
    - [ ] Ambient particles
    - [ ] Entity spawners
    - [ ] Data-driven loader
    - [ ] In-game configuration
    - [ ] Dungeon entrances
    - [ ] NPCs
    - [ ] Sub-regions
    - [ ] Special effects
    - [ ] Random events
- [ ] Dungeons
    - [ ] Ambient particles
    - [ ] Final Bosses
    - [ ] Mini Bosses
    - [ ] Scripted entity spawning
    - [ ] Dungeon resetting
    - [ ] Dungeon instances
- [ ] Quests
    - [ ] Cooldown repeating quests
    - [ ] Timed quests
    - [ ] One time quests
    - [ ] Basic quest objectives
    - [ ] Multi-step quests
    - [ ] Quest requirements
        - [ ] Class
        - [ ] Race
        - [ ] Level
        - [ ] Quests
    - [ ] Rewards
        - [ ] Vault rewards :puke:
        - [ ] Command execute rewards
        - [ ] EXP Rewards
        - [ ] Item Rewards
- [ ] NPCs
    - [ ] Shops
        - [ ] Vault :puke:
    - [ ] Quest givers
    - [ ] Enemies
    - [ ] Allies
    - [ ] Guards
- [ ] Companions
    - [ ] NPCs
    - [ ] Animals
    - [ ] Trust levels
- [ ] Abilities
    - [ ] Grimoire
    - [ ] Passives
        - [ ] Auras
    - [ ] Particles
- [ ] Stats
    - [ ] Strength
        - [ ] Fury
        - [ ] Attack damage
        - [ ] Knockback resistance
        - [ ] Physical critical damage
    - [ ] Intelligence
        - [ ] Mana
        - [ ] Magic resistance
        - [ ] Magical damage
        - [ ] Magical critical damage
    - [ ] Agility
        - [ ] Stamina
        - [ ] Move speed
        - [ ] Jump height
    - [ ] Constitution
        - [ ] HP
        - [ ] Physical resistance
        - [ ] Reduced food consumption
    - [ ] Finesse
        - [ ] Critical chance
- [ ] Leveling
    - [ ] XP
- [ ] Classes
    - [ ] Skill trees
    - [ ] Weaknesses
    - [ ] Unique abilities
    - [ ] Sub-classes
    - [ ] Class requirements
        - [ ] Race
        - [ ] Level
        - [ ] Quest completion
- [ ] Entities
    - [ ] Bosses
    - [ ] Drops
    - [ ] Abilities
    - [ ] Particles
    - [ ] Weaknesses
- [ ] Races
    - [ ] Starting stats
    - [ ] Stat growths
    - [ ] Spawn points
    - [ ] Weaknesses
    - [ ] Sub-races
    - [ ] Unique abilities