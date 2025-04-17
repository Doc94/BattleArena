package org.battleplugins.arena.event;

import org.battleplugins.arena.BattleArena;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public abstract class GenericEvent extends Event {

    public void tryCallEvent() {
        if (this.isAsynchronous() && Bukkit.getServer().isPrimaryThread()) {
            Bukkit.getServer().getGlobalRegionScheduler().run(BattleArena.getInstance(), scheduledTask -> super.callEvent());
            return;
        }
        super.callEvent();
    }

}
