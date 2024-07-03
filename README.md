# Vault Hunters Copiously Fix

![Logo](copiouslyfix.png)

This mod is a simple fix to the copiously bug, where copiously is capped at 100% unless standing very close to the drops
or using ender anchor.

The issue is that Minecraft will try to collect piles of similar drops together, but deletes some dropped gems from
copiously. This happens very quickly after the gems drop on the floor.

The fix is to create new versions of all the drops, so Minecraft knows they are separate entities. Exactly the same
drops will be produced as in Vault Hunters, but created anew so they don't get tidied by Minecraft.

## Building

To build the mod, check it out and run the `./gradlew build` command.
The resulting jar will be in `./build/libs`.
