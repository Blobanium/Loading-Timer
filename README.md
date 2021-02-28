# Attention

This branch is still in a WIP state and is not yet for production use. If You Want A Production Ready Branch, Check out the Master branch.

## The Only Things That are needed for a production ready use

- Fix Mod not starting up caused by a NoSuchMethodException In The Main file (Line 22), Only thing we need to know to get this fixed is identifying what "class" in this situation is.

AutoConfig.register(LTConfig.ModConfig.class, GsonConfigSerializer::new);