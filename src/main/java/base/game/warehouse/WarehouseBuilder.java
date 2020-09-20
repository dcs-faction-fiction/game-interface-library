package base.game.warehouse;

import base.game.Airbases;
import base.game.CampaignCoalition;
import static base.game.warehouse.WarehouseItemCategory.AMMO;
import static base.game.warehouse.WarehouseItemCategory.HELICOPTERS;
import static base.game.warehouse.WarehouseItemCategory.PLANES;
import static base.game.warehouse.WarehouseItemCode.AVIATION_GASOLINE_TONS;
import static base.game.warehouse.WarehouseItemCode.DIESEL_TONS;
import static base.game.warehouse.WarehouseItemCode.JET_FUEL_TONS;
import static base.game.warehouse.WarehouseItemCode.MW50_TONS;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.Map;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class WarehouseBuilder {

  private WarehouseBuilder() {
  }

  public static String build(
    Map<CampaignCoalition, Map<Airbases, Map<WarehouseItemCode, BigDecimal>>> warehouses) {

    var builtAirbases = new StringBuilder();
    var builtMobileAirbases = new StringBuilder();
    for (var e1: warehouses.entrySet())
      for (var e2: e1.getValue().entrySet())
        if (e2.getKey().farp())
          builtMobileAirbases.append(buildAirbase(e2.getKey(), e1.getKey(), e2.getValue()));
        else
          builtAirbases.append(buildAirbase(e2.getKey(), e1.getKey(), e2.getValue()));

    return
"warehouses = \n"+
"{\n" +
"    [\"airports\"] = \n" +
"    {\n" +
         builtAirbases.toString() +
"    }, -- end of [\"airports\"]\n" +
"    [\"warehouses\"] = \n" +
"    {\n" +
         builtMobileAirbases.toString() +
"    }, -- end of [\"warehouses\"]\n" +
"} -- end of warehouses\n";
  }

  private static String buildAirbase(Airbases airbase, CampaignCoalition coalition, Map<WarehouseItemCode, BigDecimal> quantities) {
    var helicopters = quantities.entrySet().stream()
      .filter(e -> e.getKey().category() == HELICOPTERS)
      .map(e -> {
        var k = e.getKey();
        var v = e.getValue();
        return
"                    [\""+k.dcsname()+"\"] = \n" +
"                    {\n" +
"                        [\"wsType\"] = \n" +
"                        {\n" +
                             k.buildWsType() +
"                        }, -- end of [\"wsType\"]\n" +
"                        [\"initialAmount\"] = "+v+",\n" +
"                    }, -- end of [\""+k.dcsname()+"\"]\n";
      }).collect(joining(""));
    var aircrafts = quantities.entrySet().stream()
      .filter(e -> e.getKey().category() == PLANES)
      .map(e -> {
        var k = e.getKey();
        var v = e.getValue();
        return
"                    [\""+k.dcsname()+"\"] = \n" +
"                    {\n" +
"                        [\"wsType\"] = \n" +
"                        {\n" +
                             k.buildWsType() +
"                        }, -- end of [\"wsType\"]\n" +
"                        [\"initialAmount\"] = "+v+",\n" +
"                    }, -- end of [\""+k.dcsname()+"\"]\n";
      }).collect(joining(""));
    var weapons = "";
    var whWeapons = quantities.entrySet().stream()
      .filter(e -> e.getKey().category() == AMMO)
      .collect(toList());
    int ct=0;
    for (var wp: whWeapons) {
      ct++;
      weapons +=
"                    ["+ct+"] = \n" +
"                    {\n" +
"                        [\"wsType\"] = \n" +
"                        {\n" +
                             wp.getKey().buildWsType() +
"                        }, -- end of [\"wsType\"]\n" +
"                        [\"initialAmount\"] = "+wp.getValue()+",\n" +
"                    }, -- end of ["+ct+"]\n";
    }

    for (var item: WarehouseFreeItems.values()) {
      for (var freebie: item.wsType()) {
        ct++;
        weapons +=
"                    ["+ct+"] = \n" +
"                    {\n" +
"                        [\"wsType\"] = \n" +
"                        {\n" +
                            WarehouseFreeItems.buildWsType(freebie) +
"                        }, -- end of [\"wsType\"]\n" +
"                        [\"initialAmount\"] = "+item.amount()+",\n" +
"                    }, -- end of ["+ct+"]\n";
      }
    }

    return
"        ["+airbase.warehouseId()+"] = \n" +
"        {\n" +
"            [\"gasoline\"] = \n" +
"            {\n" +
"                [\"InitFuel\"] = "+quantities.getOrDefault(AVIATION_GASOLINE_TONS, ZERO)+",\n" +
"            }, -- end of [\"gasoline\"]\n" +
"            [\"unlimitedMunitions\"] = false,\n" +
"            [\"methanol_mixture\"] = \n" +
"            {\n" +
"                [\"InitFuel\"] = "+quantities.getOrDefault(MW50_TONS, ZERO)+",\n" +
"            }, -- end of [\"methanol_mixture\"]\n" +
"            [\"OperatingLevel_Air\"] = 10,\n" +
"            [\"diesel\"] = \n" +
"            {\n" +
"                [\"InitFuel\"] = "+quantities.getOrDefault(DIESEL_TONS, ZERO)+",\n" +
"            }, -- end of [\"diesel\"]\n" +
"            [\"speed\"] = 16.666666,\n" +
"            [\"size\"] = 100,\n" +
"            [\"periodicity\"] = 30,\n" +
"            [\"suppliers\"] = \n" +
"            {\n" +
"            }, -- end of [\"suppliers\"]\n" +
"            [\"coalition\"] = \""+coalition+"\",\n" +
"            [\"jet_fuel\"] = \n" +
"            {\n" +
"                [\"InitFuel\"] = "+quantities.getOrDefault(JET_FUEL_TONS, ZERO)+",\n" +
"            }, -- end of [\"jet_fuel\"]\n" +
"            [\"OperatingLevel_Eqp\"] = 10,\n" +
"            [\"unlimitedFuel\"] = false,\n" +
"            [\"aircrafts\"] = \n" +
"            {\n" +
"                [\"helicopters\"] = \n" +
"                {\n" +
helicopters+
"                }, -- end of [\"helicopters\"]\n" +
"                [\"planes\"] = \n" +
"                {\n" +
aircrafts+
"                }, -- end of [\"planes\"]\n" +
"            }, -- end of [\"aircrafts\"]\n" +
"            [\"weapons\"] = \n" +
"            {\n" +
weapons+
"            }, -- end of [\"weapons\"]\n" +
"            [\"OperatingLevel_Fuel\"] = 10,\n" +
"            [\"unlimitedAircrafts\"] = false,\n" +
"        }, -- end of ["+airbase.warehouseId()+"]\n";
  }

}
