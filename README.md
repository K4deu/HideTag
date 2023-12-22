# HideTags Plugin for Minecraft

**HideTags** is a Bukkit plugin designed to enhance player interaction in Minecraft servers. It offers the ability to hide name tags and introduces a unique handshake feature that adds a new layer of immersion to player interactions.

## Features

- **Name Tag Hiding:** Automatically conceals the name tags of players upon joining the server. Online players are added to the "HideTags" team by default.
  
- **Handshake Feature:** Players can initiate a handshake by right-clicking on each other with an empty hand. This triggers a variety of visual and audio effects for an immersive experience.
  
- **Visual and Audio Effects:** Golden name tags, explosion particles, action bar messages, and chat messages are displayed during handshakes, creating a memorable interaction.

## Usage

### Name Tag Hiding

The plugin automatically hides the name tags of players on the server. Players are added to the "HideTags" team upon joining.

### Handshake

Initiate a handshake by right-clicking on another player with an empty hand. Various effects will accompany the handshake. A cooldown period is in place to prevent spamming.

## Configuration

Currently, the plugin does not have a configuration file. You can customize its behavior by modifying the source code to suit your preferences.

## Permissions

- `hidetags.bypass`: Players with this permission can see the name tags of others even if they are hidden.

## Developer Information

If you're a developer interested in contributing or modifying the plugin, consider the following:

- The plugin utilizes a scoreboard team named "HideTags" to manage players with hidden name tags.
  
- The `PlayerJoinEvent` is used to add online players to the team upon joining.
  
- The `PlayerInteractEntityEvent` handles the handshake mechanism.

## License

This plugin is open-source and distributed under the MIT License. Contributions and issue reports are welcome on the [GitHub repository](link-to-your-github-repo).

Enjoy the enhanced player interactions with the HideTags plugin!
