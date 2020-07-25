package base.game.units;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface MissionConfiguration {
  MissionTime time();
  MissionWeather weather();
  int missionDurationSeconds();
  int tacticalCommanderSlots();
  boolean gameMasterEnabled();
}
