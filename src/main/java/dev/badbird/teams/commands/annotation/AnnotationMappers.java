package dev.badbird.teams.commands.annotation;

import dev.badbird.teams.commands.provider.PlayerDataParser;
import dev.badbird.teams.object.PlayerData;
import io.leangen.geantyref.TypeToken;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.units.qual.A;
import org.incendo.cloud.annotations.AnnotationMapper;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.parser.ParserParameter;
import org.incendo.cloud.parser.ParserParameters;
import org.incendo.cloud.parser.ParserRegistry;
import org.jetbrains.annotations.NotNull;

public class AnnotationMappers {
    private static final ParserParameter<Boolean> ALLOW_OFFLINE = new ParserParameter<>("allow_offline", TypeToken.get(boolean.class));
    private static final ParserParameter<Boolean> SENDER = new ParserParameter<>("sender", TypeToken.get(boolean.class));

    public static void register(AnnotationParser<CommandSourceStack> parser, PaperCommandManager.Bootstrapped<CommandSourceStack> mgr) {
        mgr.parserRegistry().registerAnnotationMapper(
                AllowOffline.class,
                new AllowOfflineMapper()
        );
        mgr.parserRegistry().registerParserSupplier(
                TypeToken.get(PlayerData.class),
                options -> new PlayerDataParser(options.has(ALLOW_OFFLINE))
        );
        parser.registerAnnotationMapper(
                Sender.class,
                annotation -> ParserParameters.single(
                        SENDER,
                        true
                )
        );

    }

    private static final class AllowOfflineMapper implements ParserRegistry.AnnotationMapper<AllowOffline> {

        @Override
        public @NonNull ParserParameters mapAnnotation(@NonNull AllowOffline annotation, @NonNull TypeToken<?> parsedType) {
            return ParserParameters.single(
                    ALLOW_OFFLINE,
                    true
            );
        }
    }
}
