package base.game.units;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface MissionTime {
  int hours();
  int minutes();
  int seconds();
  int day();
  int month();
  int year();
}
