package org.battleplugins.arena.event.player;

import java.util.concurrent.CompletableFuture;
import org.battleplugins.arena.Arena;
import org.battleplugins.arena.ArenaPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public abstract class BukkitArenaPlayerEvent extends PlayerEvent implements ArenaPlayerEvent {
    private final Arena arena;
    private final ArenaPlayer player;

    public BukkitArenaPlayerEvent(@NotNull Arena arena, @NotNull ArenaPlayer player, boolean async) {
        super(player.getPlayer(), async);

        this.arena = arena;
        this.player = player;
    }

    public BukkitArenaPlayerEvent(@NotNull Arena arena, @NotNull ArenaPlayer player) {
        this(arena, player, true);
    }

    @Override
    public Arena getArena() {
        return this.arena;
    }

    @Override
    public ArenaPlayer getArenaPlayer() {
        return this.player;
    }

    public void tryCallEvent() {
        if (this.isAsynchronous() && Bukkit.isPrimaryThread()) {
            CompletableFuture<Boolean> ret = CompletableFuture.supplyAsync(super::callEvent);
            return;
        }
        super.callEvent();
    }
}
