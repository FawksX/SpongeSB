# SpongySB

SpongySB is a Skyblock Core intended to run on SpongeForge API 7 servers running on 1.12. This will be used on Blaze Gaming as the "Pixelmon Skyblock" concept once finished.

Right now, this is very early stages and a lot is still to be decided. 

## Dependencies

As this is intended for Blaze Gaming Pixelmon, we run SF7.1 thus the dependencies match this

- PSCore (Build Latest and drop in libs)
- Pixelmon Reforged 8.1.2 (Grab latest and drop in libs)
- GooeyLibs (Provided)
- FAWE (Provided)
- SpongeAPI 7.1
- Sponge Configurate 4.0.0  
- Aikar Commands Framework (aikar/commands)
- NucleusAPI 1.14.2-S7.1
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