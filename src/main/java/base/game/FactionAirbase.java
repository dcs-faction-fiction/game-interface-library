package base.game;

import base.game.warehouse.WarehouseItemCode;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface FactionAirbase {
  String name();
  Airbases code();
  default Location location() {return code().location(); }
  default CampaignCoalition coalition() {return code().coalition(); }
  default CampaignMap map() {return code().map(); }
  List<FactionWaypoint> waypoints();
  Map<WarehouseItemCode, Integer> warehouse();
}
