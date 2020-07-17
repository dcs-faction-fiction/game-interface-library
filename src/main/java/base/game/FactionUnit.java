package base.game;

import base.game.units.Unit;
import java.util.Optional;
import java.util.UUID;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface FactionUnit {
  Optional<UUID> id();
  Unit type();
  Location location();
}
