package base.game;

import base.game.units.MissionBuilder;
import base.game.units.MissionConfiguration;
import base.game.units.OptionsBuilder;
import base.game.warehouse.WarehouseBuilder;
import base.game.warehouse.WarehouseItemCode;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FullMissionBuilder {

  public FullMissionBuilder() {

  }

  public void build(
    CampaignMap map,
    MissionConfiguration configuration,
    Map<CampaignCoalition, Map<Airbases, Map<WarehouseItemCode, BigDecimal>>> warehousesMap,
    List<FactionUnit> blueUnits,
    List<FactionUnit> redUnits,
    OutputStream out) {

    try {

      var mb = new MissionBuilder();

      var mission = mb.mission(map, configuration, warehousesMap, blueUnits, redUnits);
      var dictionary = mb.dict();
      var mapResources = mb.mapResource();
      var warehouses = WarehouseBuilder.build(warehousesMap);
      var options = OptionsBuilder.build(configuration.options());
      var theatre = map.dcsname();

      try (ZipOutputStream zipOut = new ZipOutputStream(out)) {
        ZipEntry zipEntry = new ZipEntry("mission");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(mission.getBytes(UTF_8));
        zipEntry = new ZipEntry("l10n/DEFAULT/dictionary");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(dictionary.getBytes(UTF_8));
        zipEntry = new ZipEntry("l10n/DEFAULT/mapResources");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(mapResources.getBytes(UTF_8));

        zipEntry = new ZipEntry("warehouses");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(warehouses.getBytes(UTF_8));

        zipEntry = new ZipEntry("options");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(options.getBytes(UTF_8));

        zipEntry = new ZipEntry("theatre");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(theatre.getBytes(UTF_8));
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }
}
