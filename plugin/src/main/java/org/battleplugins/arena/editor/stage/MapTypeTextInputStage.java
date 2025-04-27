package org.battleplugins.arena.editor.stage;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import org.battleplugins.arena.BattleArena;
import org.battleplugins.arena.competition.map.MapType;
import org.battleplugins.arena.editor.EditorContext;
import org.battleplugins.arena.messages.Message;
import org.battleplugins.arena.messages.Messages;

public class MapTypeTextInputStage<E extends EditorContext<E>> extends TextInputStage<E> {

    public MapTypeTextInputStage(Message chatMessage, Function<E, Consumer<MapType>> inputConsumer) {
        super(
                chatMessage,
                Messages.INVALID_INPUT.withContext(String.join(", ", Arrays.stream(MapType.values()).map(e -> e.name().toLowerCase(Locale.ROOT)).toList())),
                (ctx, name) -> {
                    MapType[] constants = MapType.values();
                    for (MapType constant : constants) {
                        if (constant.name().equalsIgnoreCase(name)) {
                            if (BattleArena.isFolia() && constant == MapType.DYNAMIC) {
                                Messages.INVALID_TYPE.send(ctx.getPlayer(), constant.name().toLowerCase(Locale.ROOT) + " on Folia.");
                                return false;
                            }
                            return true;
                        }
                    }
                    return false;
                }, ctx -> str -> {
                    MapType[] constants = MapType.values();
                    for (MapType constant : constants) {
                        if (constant.name().equalsIgnoreCase(str)) {
                            inputConsumer.apply(ctx).accept(constant);
                            return;
                        }
                    }
                }
        );


    }

}
