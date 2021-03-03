# Loading timer

Find us on [Modrinth](https://modrinth.com/mod/loading-timer) and [CurseForge](https://www.curseforge.com/minecraft/mc-mods/loading-timer)!

## What is this?

This is my attempt at creating a mod using Fabric. All it does is shows you in logs or in a toast notification at startup how much time it took to load the Minecraft client.

## What inspired me to make this mod?

There is an optimization mod by sk1er for Forge 1.8.9 called "Patcher". This mod included a feature that showed you how long Minecraft took to start up. I really liked the feature, but I couldn't find any mod in the FabricMC toolchain that could do that exact feature. Thus, I decided to make it myself! It took me about two days to create this mod from the ground up (due to my lack of knowledge with Java), but I finally got it to work, and so I'm sharing it with everyone!

## Compiling

Clone the repository and run `./gradlew build`, as is standard procedure with any mod, or `gradlew build` on Windows.

## License

This project is licensed under the MIT License.

## To-Do List

- ~~Add Toast Notification maybe~~ DONE! as of v1.1
- ~~More precision~~ Insane Precision now available as of v1.2-beta1! 
- ~~Configuration file~~ and Mod Menu support
- Port to Forge
