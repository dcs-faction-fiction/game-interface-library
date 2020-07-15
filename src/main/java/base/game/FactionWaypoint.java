package base.game;

import java.util.UUID;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface FactionWaypoint {
  UUID id();
  String name();
  Integer number();
  Location location();
}
