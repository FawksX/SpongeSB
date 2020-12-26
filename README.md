# SpongySB

SpongySB is a Skyblock Core intended to run on SpongeForge API 7 servers running on 1.12. This will be used on Blaze Gaming as the "Pixelmon Skyblock" concept once finished.

Right now, this is very early stages and a lot is still to be decided. 

## Dependencies

As this is intended for Blaze Gaming Pixelmon, we run SF7.1 thus the dependencies match this

- SpongeAPI 7.1
- Aikar Commands Framework (aikar/commands)
- NucleusAPI 1.14.2-S7.1
- LuckPerms API 5.2
- HikariCP

## Default Configurations

This project uses Sponge Configurate's Object Mapper. Default configurations are generated when the plugin is ran

## Building

SpongySB uses Gradle to handle dependencies & building.

### Requirements
- Java 8 or newer
- Git

### Compiling from source

Simply git clone to your machine and execute `gradlew build`

---
## Completed Areas

- Initial Configuration (Will need to be abstracted more to be easier to utilise, but does work)
- Initial World Generation
- Initial Databases Creation and connections