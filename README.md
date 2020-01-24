Argonaut Core
=============
This plugin is the core of the argonaut rpg system it will handle the following:
- Argonaut Module Loading
- Regions
- Quests
- NPCs
- Custom Damage and Abilities
- General API

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

TODO
----
- [x] Initial plugin base
- [x] NMSInterfaces for supporting multiple MC versions
- [ ] Module loading system (will piggie back off of Bukkit's plugin loading)
- [ ] Command API
- [ ] Inventory Menu API
- [ ] Book GUI API
- [ ] Sign GUI API
- [ ] Anvil GUI API
- [ ] Hologram API
- [ ] API for regions
- [ ] Data-driven region loading
- [ ] In-game region configuration
- [ ] API for NPCs
- [ ] Data-driven NPC loading
- [ ] In-game NPC configuration
- [ ] API for quests
- [ ] Data-driven quest loading
- [ ] In-gam quest configuration
