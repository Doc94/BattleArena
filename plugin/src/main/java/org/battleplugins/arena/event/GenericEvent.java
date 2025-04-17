package org.battleplugins.arena.event;

import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public abstract class GenericEvent extends Event {

    public void tryCallEvent() {
        if (this.isAsynchronous() && Bukkit.isPrimaryThread()) {
            CompletableFuture<Boolean> ret = CompletableFuture.supplyAsync(super::callEvent);
            return;
        }
        super.callEvent();
    }

}
