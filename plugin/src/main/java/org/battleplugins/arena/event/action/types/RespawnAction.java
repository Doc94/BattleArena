package org.battleplugins.arena.event.action.types;

import org.battleplugins.arena.ArenaPlayer;
import org.battleplugins.arena.BattleArena;
import org.battleplugins.arena.event.action.EventAction;
import org.battleplugins.arena.resolver.Resolvable;

import java.util.Map;

public class RespawnAction extends EventAction {

    public RespawnAction(Map<String, String> params) {
        super(params);
    }

    @Override
    public void call(ArenaPlayer arenaPlayer, Resolvable resolvable) {
        arenaPlayer.getPlayer().getScheduler().runDelayed(BattleArena.getInstance(), scheduledTask -> arenaPlayer.getPlayer().spigot().respawn(), null, 1L);
    }
}
