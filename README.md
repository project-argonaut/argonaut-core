Argonaut Core
=============
[![Build Status](https://travis-ci.org/project-argonaut/argonaut-core.svg?branch=master)](https://travis-ci.org/project-argonaut/argonaut-core)  
This plugin is the core of the argonaut rpg system it will handle the following:
- Modules
- Regions
- Quests
- NPCs
- Custom HP Values
- Abilities
- Custom Systems
    - Mana
    - Stamina
    - Fury
- Classes

Argonaut is currently in development, so please be patient as these features slowly find their way into the system!

Setup
-----
Argonaut currently relies on ProtocolLib, please install it along with Argonaut!  
  
When configuring permissions, please do not remove the `argonaut.command` permission from any users
you wish to be able play with argonaut. It will not grant permission to configuration commands, it
will only provide access to the commands necessary for argonaut to manage the player.

Permissions
-----------
- `argonaut.command` - Only remove from those not allowed to play with argonaut.
- `argonaut.command.*` - This will grant access to all argonaut commands, **will allow access to configuration commands!**
- `argonaut.command.version` - Grants access to the `/argonaut version` command.
- `argonaut.command.region.*` - This will allow access to all region configuration commands.
- `argonaut.command.region.create` - This will allow access to the `/argonaut region create <name>` command.  
**TODO**

Commands
--------
Some commands will remain internal, as they are not intended to be executed manually by the end user.
- `/argonaut version` - Prints the current version of the argonaut system.
- `/argonaut region create <name>` - Creates a new region.
- `/argonaut region delete <name>` - Deletes the given region.
- `/argonaut region info <name>` - Displays the information of the given region.

Developing Modules
------------------
**TODO**

Common Errors
-------------
**TODO**

TODO
----
- [x] Initial plugin base
- [x] Version interfaces
    - [ ] NMS interfaces.
    - [ ] ProtocolLib packet interface.
    - [ ] Bukkit API interfaces.
- [x] Module API
    - [x] Plugin Modules
    - [ ] Internal Modules
    - [x] Loading
    - [x] Dependencies
    - [ ] Service injectors
- [ ] Command API
- [ ] Advanced Listener API
- [ ] Inventory Menu API
- [ ] Book GUI API
- [ ] Sign GUI API
- [ ] Anvil GUI API
- [ ] Hologram API
- [ ] Regions
    - [ ] API
    - [ ] Data-driven loading
    - [ ] In-game configuration
- [ ] NPCs
    - [ ] API
    - [ ] Data-driven loading
    - [ ] In-game configuration
- [ ] Quests
    - [ ] API
    - [ ] Data-driven loading
    - [ ] In-game configuration
- [ ] Custom Damage
