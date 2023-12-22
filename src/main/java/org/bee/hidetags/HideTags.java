package org.bee.hidetags;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Set;

public class HideTags extends JavaPlugin implements Listener {

    private Team team;
    private Set<Player> handshakeCooldown = new HashSet<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

        this.team = board.getTeam("HideTags");
        if (this.team == null) {
            this.team = board.registerNewTeam("HideTags");
        }

        this.team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

        // adding existing players to the team
        for (Player player : Bukkit.getOnlinePlayers()) {
            addPlayerToTeamWithDelay(player);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // check if the player has already been added to the team
        if (!team.hasEntry(event.getPlayer().getName())) {
            addPlayerToTeamWithDelay(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        // check that the event is caused by a right click and is not in cooldown
        if (event.getRightClicked() instanceof Player &&
                event.getPlayer().getInventory().getItemInMainHand().getType().isAir() &&
                handshakeCooldown.add(event.getPlayer())) {

            Player clickedPlayer = (Player) event.getRightClicked();
            String actionBarMessage = ChatColor.GOLD + clickedPlayer.getName();
            String chatMessage = ChatColor.GOLD + "Вы пожали руку " + clickedPlayer.getName();
            event.getPlayer().sendActionBar(actionBarMessage);
            event.getPlayer().sendMessage(chatMessage);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 1.0f);
            event.getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, clickedPlayer.getLocation(), 5);

            Bukkit.getScheduler().runTaskLater(this, () -> handshakeCooldown.remove(event.getPlayer()), 20L);
        }
    }

    private void addPlayerToTeamWithDelay(Player player) {
        Bukkit.getScheduler().runTaskLater(this, () -> team.addEntry(player.getName()), 20L);
    }
}
