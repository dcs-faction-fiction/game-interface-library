package base.game.units;

import base.game.Airbases;
import base.game.CampaignCoalition;
import static base.game.CampaignCoalition.BLUE;
import static base.game.CampaignCoalition.RED;
import base.game.CampaignMap;
import base.game.FactionUnit;
import base.game.Parkings;
import static base.game.warehouse.WarehouseItemCategory.HELICOPTERS;
import static base.game.warehouse.WarehouseItemCategory.PLANES;
import base.game.warehouse.WarehouseItemCode;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MissionBuilder {

  private int nextId = 1;
  private final Set<String> dict = new LinkedHashSet<>();

  public String dict() {
    var strdict = dict.stream()
      .map(s -> "[\""+s+"\"] = \""+s+"\",\n")
      .collect(joining());
return
"dictionary = \n" +
"{\n" +
strdict +
"} -- end of dictionary";
  }

  public String mapResource() {
return
"mapResource = \n" +
"{\n" +
"} -- end of mapResource";
  }

  public String mission(CampaignMap map,
    MissionConfiguration configuration,
    Map<CampaignCoalition, Map<Airbases, Map<WarehouseItemCode, BigDecimal>>> warehouses,
    List<FactionUnit> blueUnitsGround,
    List<FactionUnit> redUnitsGround) {

    // TODO
    // - set date and time
    // - set weather
    // - add ground routes

    // 2h mission, alert 5 minutes before end.
    var missionDurationSeconds = configuration.missionDurationSeconds();
    var missionEndWarningSeconds = 300;

    var blueUnits = "";
    var redUnits = "";

    var blueWarehouses = warehouses.computeIfAbsent(BLUE, k -> new EnumMap<>(Airbases.class));
    var redWarehouses = warehouses.computeIfAbsent(RED, k -> new EnumMap<>(Airbases.class));
    var blueFarps = blueWarehouses.keySet().stream().filter(Airbases::farp).collect(toList());
    var redFarps = redWarehouses.keySet().stream().filter(Airbases::farp).collect(toList());
    var blueCarriers = blueWarehouses.keySet().stream().filter(Airbases::carrier).collect(toList());
    var redCarriers = redWarehouses.keySet().stream().filter(Airbases::carrier).collect(toList());

    blueUnits += addAirbaseAircrafts(blueWarehouses);
    redUnits += addAirbaseAircrafts(redWarehouses);

    blueUnits += addGroundUnits(blueCarriers, blueUnitsGround);
    redUnits += addGroundUnits(redCarriers, redUnitsGround);

    var clock = configuration.time().seconds()
      + configuration.time().minutes() * 60
      + configuration.time().hours() * 3600;

    var weather = makeWeather(configuration.weather());

    return
"mission = \n" +
"{\n" +
"    [\"requiredModules\"] = {},\n" +
"    [\"theatre\"] = \""+map.dcsname()+"\",\n" +
"    [\"start_time\"] = "+clock+",\n" +
"    [\"date\"] = \n" +
"    {\n" +
"        [\"Day\"] = "+configuration.time().day()+",\n" +
"        [\"Month\"] = "+configuration.time().month()+",\n" +
"        [\"Year\"] = "+configuration.time().year()+",\n" +
"    }, -- end of [\"date\"]\n" +
"    [\"trig\"] = \n" +
"    {\n" +
"        [\"actions\"] = \n" +
"        {\n" +
"            [1] = \"a_end_mission(\\\"\\\", \\\"\\\", "+missionEndWarningSeconds+"); mission.trig.func[1]=nil;\",\n" +
"        }, -- end of [\"actions\"]\n" +
"        [\"events\"] = \n" +
"        {\n" +
"        }, -- end of [\"events\"]\n" +
"        [\"custom\"] = \n" +
"        {\n" +
"        }, -- end of [\"custom\"]\n" +
"        [\"func\"] = \n" +
"        {\n" +
"            [1] = \"if mission.trig.conditions[1]() then mission.trig.actions[1]() end\",\n" +
"        }, -- end of [\"func\"]\n" +
"        [\"flag\"] = \n" +
"        {\n" +
"            [1] = true,\n" +
"        }, -- end of [\"flag\"]\n" +
"        [\"conditions\"] = \n" +
"        {\n" +
"            [1] = \"return(c_time_after("+missionDurationSeconds+") )\",\n" +
"        }, -- end of [\"conditions\"]\n" +
"        [\"customStartup\"] = \n" +
"        {\n" +
"        }, -- end of [\"customStartup\"]\n" +
"        [\"funcStartup\"] = \n" +
"        {\n" +
"        }, -- end of [\"funcStartup\"]\n" +
"    }, -- end of [\"trig\"]\n" +
"    [\"result\"] = \n" +
"    {\n" +
"        [\"offline\"] = \n" +
"        {\n" +
"            [\"conditions\"] = \n" +
"            {\n" +
"            }, -- end of [\"conditions\"]\n" +
"            [\"actions\"] = \n" +
"            {\n" +
"            }, -- end of [\"actions\"]\n" +
"            [\"func\"] = \n" +
"            {\n" +
"            }, -- end of [\"func\"]\n" +
"        }, -- end of [\"offline\"]\n" +
"        [\"total\"] = 0,\n" +
"        [\"blue\"] = \n" +
"        {\n" +
"            [\"conditions\"] = \n" +
"            {\n" +
"            }, -- end of [\"conditions\"]\n" +
"            [\"actions\"] = \n" +
"            {\n" +
"            }, -- end of [\"actions\"]\n" +
"            [\"func\"] = \n" +
"            {\n" +
"            }, -- end of [\"func\"]\n" +
"        }, -- end of [\"blue\"]\n" +
"        [\"red\"] = \n" +
"        {\n" +
"            [\"conditions\"] = \n" +
"            {\n" +
"            }, -- end of [\"conditions\"]\n" +
"            [\"actions\"] = \n" +
"            {\n" +
"            }, -- end of [\"actions\"]\n" +
"            [\"func\"] = \n" +
"            {\n" +
"            }, -- end of [\"func\"]\n" +
"        }, -- end of [\"red\"]\n" +
"    }, -- end of [\"result\"]\n" +
"    [\"maxDictId\"] = 1,\n" +
"    [\"pictureFileNameN\"] = \n" +
"    {\n" +
"    }, -- end of [\"pictureFileNameN\"]\n" +
"    [\"groundControl\"] = \n" +
"    {\n" +
"        [\"isPilotControlVehicles\"] = false,\n" +
"        [\"roles\"] = \n" +
"        {\n" +
"            [\"artillery_commander\"] = \n" +
"            {\n" +
"                [\"neutrals\"] = 0,\n" +
"                [\"blue\"] = "+configuration.tacticalCommanderSlots()+",\n" +
"                [\"red\"] = "+configuration.tacticalCommanderSlots()+",\n" +
"            }, -- end of [\"artillery_commander\"]\n" +
"            [\"instructor\"] = \n" +
"            {\n" +
"                [\"neutrals\"] = 0,\n" +
"                [\"blue\"] = "+ (configuration.gameMasterEnabled() ? 1 : 0) +",\n" +
"                [\"red\"] = "+ (configuration.gameMasterEnabled() ? 1 : 0) +",\n" +
"            }, -- end of [\"instructor\"]\n" +
"            [\"observer\"] = \n" +
"            {\n" +
"                [\"neutrals\"] = 0,\n" +
"                [\"blue\"] = 0,\n" +
"                [\"red\"] = 0,\n" +
"            }, -- end of [\"observer\"]\n" +
"            [\"forward_observer\"] = \n" +
"            {\n" +
"                [\"neutrals\"] = 0,\n" +
"                [\"blue\"] = 0,\n" +
"                [\"red\"] = 0,\n" +
"            }, -- end of [\"forward_observer\"]\n" +
"        }, -- end of [\"roles\"]\n" +
"    }, -- end of [\"groundControl\"]\n" +
"    [\"goals\"] = \n" +
"    {\n" +
"    }, -- end of [\"goals\"]\n" +
"    [\"weather\"] = \n" +
weather +
"    [\"triggers\"] = \n" +
"    {\n" +
"        [\"zones\"] = \n" +
"        {\n" +
"        }, -- end of [\"zones\"]\n" +
"    }, -- end of [\"triggers\"]\n" +
"    [\"map\"] = \n" +
"    {\n" +
"        [\"centerY\"] = 638429.14857143,\n" +
"        [\"zoom\"] = 131220,\n" +
"        [\"centerX\"] = -298572.99714286,\n" +
"    }, -- end of [\"map\"]\n" +
"    [\"coalitions\"] = \n" +
"    {\n" +
"        [\"neutrals\"] = \n" +
"        {\n" +
"        }, -- end of [\"neutrals\"]\n" +
"        [\"blue\"] = \n" +
"        {\n" +
"            [1] = 2,\n" +
"        }, -- end of [\"blue\"]\n" +
"        [\"red\"] = \n" +
"        {\n" +
"            [1] = 7,\n" +
"        }, -- end of [\"red\"]\n" +
"    }, -- end of [\"coalitions\"]\n" +
"    [\"descriptionText\"] = \"\",\n" +
"    [\"pictureFileNameR\"] = {},\n" +
"    [\"descriptionNeutralsTask\"] = \"\",\n" +
"    [\"descriptionBlueTask\"] = \"\",\n" +
"    [\"descriptionRedTask\"] = \"\",\n" +
"    [\"pictureFileNameB\"] = {},\n" +
"    [\"coalition\"] = \n" +
"    {\n" +
"        [\"blue\"] = \n" +
"        {\n" +
"            [\"bullseye\"] = \n" +
"            {\n" +
"                [\"y\"] = 617414,\n" +
"                [\"x\"] = -291014,\n" +
"            }, -- end of [\"bullseye\"]\n" +
"            [\"nav_points\"] = {},\n" +
"            [\"name\"] = \"blue\",\n" +
"            [\"country\"] = \n" +
"            {\n" +
"                [1] = \n" +
"                {\n" +
"                    [\"id\"] = 2,\n" +
"                    [\"name\"] = \"USA\",\n" +
blueUnits+
makeFarps(blueFarps)+
makeCarriers(blueCarriers)+
"                }, -- end of [8]\n" +
"            }, -- end of [\"country\"]\n" +
"        }, -- end of [\"blue\"]\n" +
"        [\"red\"] = \n" +
"        {\n" +
"            [\"bullseye\"] = \n" +
"            {\n" +
"                [\"y\"] = 371700,\n" +
"                [\"x\"] = 11557,\n" +
"            }, -- end of [\"bullseye\"]\n" +
"            [\"nav_points\"] = {},\n" +
"            [\"name\"] = \"red\",\n" +
"            [\"country\"] = \n" +
"            {\n" +
"                [1] = \n" +
"                {\n" +
"                    [\"id\"] = 7,\n" +
"                    [\"name\"] = \"USAF Aggressors\",\n" +
redUnits+
makeFarps(redFarps)+
makeCarriers(redCarriers)+
"                }, -- end of [2]\n" +
"            }, -- end of [\"country\"]\n" +
"        }, -- end of [\"red\"]\n" +
"    }, -- end of [\"coalition\"]\n" +
"    [\"sortie\"] = \"\",\n" +
"    [\"version\"] = 16,\n" +
"    [\"trigrules\"] = \n" +
"    {\n" +
"        [1] = \n" +
"        {\n" +
"            [\"rules\"] = \n" +
"            {\n" +
"                [1] = \n" +
"                {\n" +
"                    [\"seconds\"] = "+missionDurationSeconds+",\n" +
"                    [\"predicate\"] = \"c_time_after\",\n" +
"                    [\"zone\"] = \"\",\n" +
"                }, -- end of [1]\n" +
"            }, -- end of [\"rules\"]\n" +
"            [\"comment\"] = \"END\",\n" +
"            [\"eventlist\"] = \"\",\n" +
"            [\"actions\"] = \n" +
"            {\n" +
"                [1] = \n" +
"                {\n" +
"                    [\"text\"] = \"\",\n" +
"                    [\"start_delay\"] = "+missionEndWarningSeconds+",\n" +
"                    [\"zone\"] = \"\",\n" +
"                    [\"predicate\"] = \"a_end_mission\",\n" +
"                    [\"winner\"] = \"\",\n" +
"                    [\"meters\"] = 1000,\n" +
"                }, -- end of [1]\n" +
"            }, -- end of [\"actions\"]\n" +
"            [\"predicate\"] = \"triggerOnce\",\n" +
"        }, -- end of [1]\n" +
"    }, -- end of [\"trigrules\"]\n" +
"    [\"currentKey\"] = 54,\n" +
"    [\"forcedOptions\"] = {" +
OptionsBuilder.makeOptions(configuration.options()) +
"    },\n" +
"    [\"failures\"] = {},\n" +
"} -- end of mission\n"
;
  }

  public String makeWeather(MissionWeather weather) {
return
"    {\n" +
"        [\"wind\"] = \n" +
"        {\n" +
"            [\"at8000\"] = \n" +
"            {\n" +
"                [\"speed\"] = 0,\n" +
"                [\"dir\"] = 93,\n" +
"            }, -- end of [\"at8000\"]\n" +
"            [\"at2000\"] = \n" +
"            {\n" +
"                [\"speed\"] = 0,\n" +
"                [\"dir\"] = 314,\n" +
"            }, -- end of [\"at2000\"]\n" +
"            [\"atGround\"] = \n" +
"            {\n" +
"                [\"speed\"] = 2,\n" +
"                [\"dir\"] = 144,\n" +
"            }, -- end of [\"atGround\"]\n" +
"        }, -- end of [\"wind\"]\n" +
"        [\"enable_fog\"] = false,\n" +
"        [\"season\"] = \n" +
"        {\n" +
"            [\"temperature\"] = 15,\n" +
"        }, -- end of [\"season\"]\n" +
"        [\"qnh\"] = 720,\n" +
"        [\"cyclones\"] = \n" +
"        {\n" +
"            [1] = \n" +
"            {\n" +
"                [\"pressure_spread\"] = 941533.32375135,\n" +
"                [\"centerZ\"] = 299313.43999019,\n" +
"                [\"ellipticity\"] = 1.0470800392607,\n" +
"                [\"rotation\"] = 0.7880779124333,\n" +
"                [\"pressure_excess\"] = -1500,\n" +
"                [\"centerX\"] = -122771.02538112,\n" +
"            }, -- end of [1]\n" +
"            [2] = \n" +
"            {\n" +
"                [\"pressure_spread\"] = 1088787.8352334,\n" +
"                [\"centerZ\"] = -358796.94062436,\n" +
"                [\"ellipticity\"] = 1.0470800392607,\n" +
"                [\"rotation\"] = 0.7880779124333,\n" +
"                [\"pressure_excess\"] = 2000,\n" +
"                [\"centerX\"] = 464421.45805866,\n" +
"            }, -- end of [2]\n" +
"        }, -- end of [\"cyclones\"]\n" +
"        [\"name_de\"] = \"Frühling, Klar\",\n" +
"        [\"dust_density\"] = 0,\n" +
"        [\"enable_dust\"] = false,\n" +
"        [\"clouds\"] = \n" +
"        {\n" +
"            [\"thickness\"] = 2000,\n" +
"            [\"density\"] = 3,\n" +
"            [\"base\"] = 2700,\n" +
"            [\"iprecptns\"] = 0,\n" +
"        }, -- end of [\"clouds\"]\n" +
"        [\"atmosphere_type\"] = 1,\n" +
"        [\"groundTurbulence\"] = 18.288,\n" +
"        [\"type_weather\"] = 0,\n" +
"        [\"name\"] = \"Spring, Clear sky\",\n" +
"        [\"fog\"] = \n" +
"        {\n" +
"            [\"thickness\"] = 0,\n" +
"            [\"visibility\"] = 0,\n" +
"        }, -- end of [\"fog\"]\n" +
"        [\"visibility\"] = \n" +
"        {\n" +
"            [\"distance\"] = 80000,\n" +
"        }, -- end of [\"visibility\"]\n" +
"    },";
  }

  public String addAirbaseAircrafts(Map<Airbases, Map<WarehouseItemCode, BigDecimal>> fullWarehouse) {
    String builtPlanes = "";
    String builtHelos = "";

    int luactplanes = 1;
    int luacthelos = 1;
    for (var we: fullWarehouse.entrySet()) {
      var airbase = we.getKey();
      var warehouse = we.getValue();

      int parkingCt = 0;
      var parkings = Parkings.getParkings(airbase.map(), airbase.warehouseId());

      var planes = warehouse.entrySet().stream()
        .filter(e -> e.getKey().category() == PLANES)
        .collect(toList());
      var helos = warehouse.entrySet().stream()
        .filter(e ->  e.getKey().category() == HELICOPTERS)
        .collect(toList());

      for (int i = 0; i < planes.size(); i++) {
        var e = planes.get(i);
        var k = e.getKey();
        var v = Integer.valueOf(e.getValue().toBigInteger().toString());
        for (int ct = 0; ct < v; ct++) {
          if (airbase.farp())
            continue;
          if (airbase.carrier()) {
            builtPlanes +=
"                            ["+(luactplanes)+"] = \n" +
"                            {\n" +
buildParkedUnitCarrier(nextId++, k.dcsname(), airbase.warehouseId(), parkings.get(0))+
"                            }, -- end of ["+(luactplanes)+"]\n"
;
            luactplanes++;
          } else {
            var parking = parkings.get(parkingCt++);
            builtPlanes +=
"                            ["+(luactplanes)+"] = \n" +
"                            {\n" +
buildParkedUnit(nextId++, k.dcsname(), airbase.warehouseId(), parking)+
"                            }, -- end of ["+(luactplanes)+"]\n"
;
            luactplanes++;
          }
        }
      }

      for (int i = 0; i < helos.size(); i++) {
        var e = helos.get(i);
        var k = e.getKey();
        var v = Integer.valueOf(e.getValue().toBigInteger().toString());
        for (int ct = 0; ct < v; ct++) {
          if (airbase.farp() || airbase.carrier()) {
            builtHelos +=
"                            ["+(luacthelos)+"] = \n" +
"                            {\n" +
buildParkedUnitInFarp(nextId++, k.dcsname(), airbase.warehouseId(), parkings.get(0))+
"                            }, -- end of ["+(luacthelos)+"]\n"
;
          } else {
            var parking = parkings.get(parkingCt++);
            builtHelos +=
"                            ["+(luacthelos)+"] = \n" +
"                            {\n" +
buildParkedUnit(nextId++, k.dcsname(), airbase.warehouseId(), parking)+
"                            }, -- end of ["+(luacthelos)+"]\n"
;
          }
          luacthelos++;
        }
      }
    }

    return
"                    [\"helicopter\"] = \n" +
"                    {\n" +
"                        [\"group\"] = \n" +
"                        {\n" +
builtHelos+
"                        }, -- end of [\"group\"]\n" +
"                    }, -- end of [\"helicopter\"]\n" +
"                    [\"plane\"] = \n" +
"                    {\n" +
"                        [\"group\"] = \n" +
"                        {\n" +
builtPlanes+
"                        }, -- end of [\"group\"]\n" +
"                    }, -- end of [\"plane\"]\n"
;
  }

  public String buildParkedUnit(int id, String type, int airdromeId, List<String> parking) {
    var name = "ParkedUnit "+id;
    var parkingNumber = parking.get(0);
    var parkingId = parking.get(1);
    var x = parking.get(2);
    var y = parking.get(3);
    dict.add(name);
    return
"                                [\"modulation\"] = 0,\n" +
"                                [\"tasks\"] = \n" +
"                                {\n" +
"                                }, -- end of [\"tasks\"]\n" +
"                                [\"radioSet\"] = false,\n" +
"                                [\"task\"] = \"Nothing\",\n" +
"                                [\"uncontrolled\"] = false,\n" +
"                                [\"taskSelected\"] = true,\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = 10,\n" +
"                                            [\"action\"] = \"From Parking Area\",\n" +
"                                            [\"alt_type\"] = \"BARO\",\n" +
"                                            [\"speed\"] = 138.88888888889,\n" +
"                                            [\"task\"] = \n" +
"                                            {\n" +
"                                                [\"id\"] = \"ComboTask\",\n" +
"                                                [\"params\"] = \n" +
"                                                {\n" +
"                                                    [\"tasks\"] = \n" +
"                                                    {\n" +
"                                                    }, -- end of [\"tasks\"]\n" +
"                                                }, -- end of [\"params\"]\n" +
"                                            }, -- end of [\"task\"]\n" +
"                                            [\"type\"] = \"TakeOffParking\",\n" +
"                                            [\"ETA\"] = 0,\n" +
"                                            [\"ETA_locked\"] = true,\n" +
"                                            [\"y\"] = "+y+",\n" +
"                                            [\"x\"] = "+x+",\n" +
"                                            [\"name\"] = \"0\",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"airdromeId\"] = "+airdromeId+",\n" +
"                                            [\"speed_locked\"] = true,\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"groupId\"] = "+id+",\n" +
"                                [\"hidden\"] = false,\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"alt\"] = 10,\n" +
"                                        [\"alt_type\"] = \"BARO\",\n" +
"                                        [\"livery_id\"] = \"36STV Camouflage\",\n" +
"                                        [\"skill\"] = \"Client\",\n" +
"                                        [\"parking\"] = "+parkingNumber+",\n" +
"                                        [\"speed\"] = 138.88888888889,\n" +
"                                        [\"AddPropAircraft\"] = \n" +
"                                        {\n" +
makeAircraftProps(type)+
"                                        }, -- end of [\"AddPropAircraft\"]\n" +
"                                        [\"type\"] = \""+type+"\",\n" +
"                                        [\"Radio\"] = \n" +
"                                        {\n" +
"                                        }, -- end of [\"Radio\"]\n" +
"                                        [\"unitId\"] = "+id+",\n" +
"                                        [\"psi\"] = 0,\n" +
"                                        [\"parking_id\"] = \""+parkingId+"\",\n" +
"                                        [\"x\"] = "+x+",\n" +
"                                        [\"y\"] = "+y+",\n" +
"                                        [\"name\"] = \""+name+"\",\n" +
"                                        [\"payload\"] = \n" +
"                                        {\n" +
"                                            [\"pylons\"] = \n" +
"                                            {\n" +
"                                            }, -- end of [\"pylons\"]\n" +
"                                            [\"fuel\"] = 0,\n" +
"                                            [\"flare\"] = 0,\n" +
"                                            [\"ammo_type\"] = 0,\n" +
"                                            [\"chaff\"] = 0,\n" +
"                                            [\"gun\"] = 0,\n" +
"                                        }, -- end of [\"payload\"]\n" +
"                                        [\"heading\"] = 0,\n" +
"                                        [\"callsign\"] = \n" +
"                                        {\n" +
"                                            [1] = 1,\n" +
"                                            [2] = 1,\n" +
"                                            [3] = 1,\n" +
"                                            [\"name\"] = \"Enfield11\",\n" +
"                                        }, -- end of [\"callsign\"]\n" +
"                                        [\"onboard_num\"] = \"010\",\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"x\"] = "+x+",\n" +
"                                [\"y\"] = "+y+",\n" +
"                                [\"name\"] = \""+name+"\",\n" +
"                                [\"communication\"] = true,\n" +
"                                [\"start_time\"] = 0,\n" +
"                                [\"frequency\"] = 305,\n";
  }

  public String buildParkedUnitInFarp(int id, String type, int airdromeId, List<String> parking) {
    var name = "ParkedUnit "+id;
    var x = parking.get(2);
    var y = parking.get(3);
    dict.add(name);
    return
"                                [\"modulation\"] = 0,\n" +
"                                [\"tasks\"] = \n" +
"                                {\n" +
"                                }, -- end of [\"tasks\"]\n" +
"                                [\"task\"] = \"CAS\",\n" +
"                                [\"uncontrolled\"] = false,\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = 531,\n" +
"                                            [\"action\"] = \"From Parking Area\",\n" +
"                                            [\"alt_type\"] = \"BARO\",\n" +
"                                            [\"linkUnit\"] = "+airdromeId+",\n" +
"                                            [\"helipadId\"] = "+airdromeId+",\n" +
"                                            [\"speed\"] = 41.666666666667,\n" +
"                                            [\"task\"] = \n" +
"                                            {\n" +
"                                                [\"id\"] = \"ComboTask\",\n" +
"                                                [\"params\"] = \n" +
"                                                {\n" +
"                                                    [\"tasks\"] = \n" +
"                                                    {\n" +
"                                                    }, -- end of [\"tasks\"]\n" +
"                                                }, -- end of [\"params\"]\n" +
"                                            }, -- end of [\"task\"]\n" +
"                                            [\"type\"] = \"TakeOffParking\",\n" +
"                                            [\"ETA\"] = 0,\n" +
"                                            [\"ETA_locked\"] = true,\n" +
"                                            [\"y\"] = "+y+",\n" +
"                                            [\"x\"] = "+x+",\n" +
"                                            [\"name\"] = \""+name+"\",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"speed_locked\"] = true,\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"groupId\"] = "+id+",\n" +
"                                [\"hidden\"] = false,\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"alt\"] = 531,\n" +
"                                        [\"alt_type\"] = \"BARO\",\n" +
"                                        [\"livery_id\"] = \"36STV_386_Addestramento\",\n" +
"                                        [\"skill\"] = \"Client\",\n" +
"                                        [\"parking\"] = \"1\",\n" +
"                                        [\"ropeLength\"] = 15,\n" +
"                                        [\"speed\"] = 41.666666666667,\n" +
"                                        [\"type\"] = \""+type+"\",\n" +
"                                        [\"unitId\"] = "+id+",\n" +
"                                        [\"psi\"] = 0,\n" +
"                                        [\"parking_id\"] = \"1\",\n" +
"                                        [\"x\"] = "+x+",\n" +
"                                        [\"name\"] = \""+name+"\",\n" +
"                                        [\"payload\"] = \n" +
"                                        {\n" +
"                                            [\"pylons\"] = \n" +
"                                            {\n" +
"                                            }, -- end of [\"pylons\"]\n" +
"                                            [\"fuel\"] = \"0\",\n" +
"                                            [\"flare\"] = 0,\n" +
"                                            [\"chaff\"] = 0,\n" +
"                                            [\"gun\"] = 0,\n" +
"                                        }, -- end of [\"payload\"]\n" +
"                                        [\"y\"] = "+y+",\n" +
"                                        [\"heading\"] = 0,\n" +
"                                        [\"callsign\"] = \n" +
"                                        {\n" +
"                                            [1] = 1,\n" +
"                                            [2] = 1,\n" +
"                                            [3] = 1,\n" +
"                                            [\"name\"] = \"Enfield11\",\n" +
"                                        }, -- end of [\"callsign\"]\n" +
"                                        [\"onboard_num\"] = \"050\",\n" +
"                                        [\"AddPropAircraft\"] = \n" +
"                                        {\n" +
makeAircraftProps(type)+
"                                        }, -- end of [\"AddPropAircraft\"]\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"y\"] = "+y+",\n" +
"                                [\"x\"] = "+x+",\n" +
"                                [\"name\"] = \""+name+"\",\n" +
"                                [\"communication\"] = true,\n" +
"                                [\"start_time\"] = 0,\n" +
"                                [\"frequency\"] = 124,\n";
  }

  public String buildParkedUnitCarrier(int id, String type, int airdromeId, List<String> parking) {
    var name = "ParkedUnit "+id;
    var x = parking.get(2);
    var y = parking.get(3);
    dict.add(name);
    return
"                                [\"modulation\"] = 0,\n" +
"                                [\"tasks\"] = \n" +
"                                {\n" +
"                                }, -- end of [\"tasks\"]\n" +
"                                [\"task\"] = \"CAS\",\n" +
"                                [\"uncontrolled\"] = false,\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = 531,\n" +
"                                            [\"action\"] = \"From Parking Area\",\n" +
"                                            [\"alt_type\"] = \"BARO\",\n" +
"                                            [\"linkUnit\"] = "+airdromeId+",\n" +
"                                            [\"helipadId\"] = "+airdromeId+",\n" +
"                                            [\"speed\"] = 41.666666666667,\n" +
"                                            [\"task\"] = \n" +
"                                            {\n" +
"                                                [\"id\"] = \"ComboTask\",\n" +
"                                                [\"params\"] = \n" +
"                                                {\n" +
"                                                    [\"tasks\"] = \n" +
"                                                    {\n" +
"                                                    }, -- end of [\"tasks\"]\n" +
"                                                }, -- end of [\"params\"]\n" +
"                                            }, -- end of [\"task\"]\n" +
"                                            [\"type\"] = \"TakeOffParking\",\n" +
"                                            [\"ETA\"] = 0,\n" +
"                                            [\"ETA_locked\"] = true,\n" +
"                                            [\"y\"] = "+y+",\n" +
"                                            [\"x\"] = "+x+",\n" +
"                                            [\"name\"] = \""+name+"\",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"speed_locked\"] = true,\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"groupId\"] = "+id+",\n" +
"                                [\"hidden\"] = false,\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"alt\"] = 531,\n" +
"                                        [\"alt_type\"] = \"BARO\",\n" +
"                                        [\"livery_id\"] = \"36STV_386_Addestramento\",\n" +
"                                        [\"skill\"] = \"Client\",\n" +
"                                        [\"parking\"] = \"1\",\n" +
"                                        [\"ropeLength\"] = 15,\n" +
"                                        [\"speed\"] = 41.666666666667,\n" +
"                                        [\"type\"] = \""+type+"\",\n" +
"                                        [\"unitId\"] = "+id+",\n" +
"                                        [\"psi\"] = 0,\n" +
"                                        [\"parking_id\"] = \"1\",\n" +
"                                        [\"x\"] = "+x+",\n" +
"                                        [\"name\"] = \""+name+"\",\n" +
"                                        [\"payload\"] = \n" +
"                                        {\n" +
"                                            [\"pylons\"] = \n" +
"                                            {\n" +
"                                            }, -- end of [\"pylons\"]\n" +
"                                            [\"fuel\"] = \"0\",\n" +
"                                            [\"flare\"] = 0,\n" +
"                                            [\"chaff\"] = 0,\n" +
"                                            [\"gun\"] = 0,\n" +
"                                        }, -- end of [\"payload\"]\n" +
"                                        [\"y\"] = "+y+",\n" +
"                                        [\"heading\"] = 0,\n" +
"                                        [\"callsign\"] = \n" +
"                                        {\n" +
"                                            [1] = 1,\n" +
"                                            [2] = 1,\n" +
"                                            [3] = 1,\n" +
"                                            [\"name\"] = \"Enfield11\",\n" +
"                                        }, -- end of [\"callsign\"]\n" +
"                                        [\"onboard_num\"] = \"050\",\n" +
"                                        [\"AddPropAircraft\"] = \n" +
"                                        {\n" +
makeAircraftProps(type)+
"                                        }, -- end of [\"AddPropAircraft\"]\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"y\"] = "+y+",\n" +
"                                [\"x\"] = "+x+",\n" +
"                                [\"name\"] = \""+name+"\",\n" +
"                                [\"communication\"] = true,\n" +
"                                [\"start_time\"] = 0,\n" +
"                                [\"frequency\"] = 124,\n";
  }

  public String addGroundUnits(List<Airbases> carriers, List<FactionUnit> units) {
    String builtUnits = "";
    int luact = 1;
    for (var unit: units) {
      builtUnits +=
"                            ["+(luact)+"] = \n" +
"                            {\n" +
buildGroundUnit(nextId++, unit)+
"                            }, -- end of ["+(luact)+"]\n"
;
      luact++;
    }

    return
"                    [\"vehicle\"] = \n" +
"                    {\n" +
"                        [\"group\"] = \n" +
"                        {\n" +
builtUnits+
"                        }, -- end of [\"group\"]\n" +
"                    }, -- end of [\"vehicle\"]\n";
  }

  public String buildGroundUnit(int id, FactionUnit unit) {
    var name = "[UUID:"+unit.id().get()+"] "
             + "[grouptype:"+unit.type().name()+"]"
             + "[lat:"+unit.location().latitude()+"] "
             + "[lon:"+unit.location().longitude()+"]";
    dict.add(name);
    return
"                                [\"visible\"] = false,\n" +
"                                [\"hiddenOnMFD\"] = true,\n" +
"                                [\"lateActivation\"] = true,\n" +
"                                [\"tasks\"] = \n" +
"                                {\n" +
"                                }, -- end of [\"tasks\"]\n" +
"                                [\"uncontrollable\"] = false,\n" +
"                                [\"task\"] = \"Ground Nothing\",\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"spans\"] = \n" +
"                                    {\n" +
"                                    }, -- end of [\"spans\"]\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = 88,\n" +
"                                            [\"type\"] = \"Turning Point\",\n" +
"                                            [\"ETA\"] = 0,\n" +
"                                            [\"alt_type\"] = \"BARO\",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"y\"] = 0,\n" +
"                                            [\"x\"] = 0,\n" +
"                                            [\"name\"] = \"\",\n" +
"                                            [\"ETA_locked\"] = true,\n" +
"                                            [\"speed\"] = 0,\n" +
"                                            [\"action\"] = \"Off Road\",\n" +
"                                            [\"task\"] = \n" +
"                                            {\n" +
"                                                [\"id\"] = \"ComboTask\",\n" +
"                                                [\"params\"] = \n" +
"                                                {\n" +
"                                                    [\"tasks\"] = \n" +
"                                                    {\n" +
"                                                    }, -- end of [\"tasks\"]\n" +
"                                                }, -- end of [\"params\"]\n" +
"                                            }, -- end of [\"task\"]\n" +
"                                            [\"speed_locked\"] = true,\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"groupId\"] = "+id+",\n" +
"                                [\"hidden\"] = false,\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"type\"] = \""+unit.type().dcstype()+"\",\n" +
"                                        [\"transportable\"] = \n" +
"                                        {\n" +
"                                            [\"randomTransportable\"] = false,\n" +
"                                        }, -- end of [\"transportable\"]\n" +
"                                        [\"unitId\"] = "+id+",\n" +
"                                        [\"skill\"] = \"Excellent\",\n" +
"                                        [\"y\"] = 0,\n" +
"                                        [\"x\"] = 0,\n" +
"                                        [\"name\"] = \""+name+"\",\n" +
"                                        [\"heading\"] = 0,\n" +
"                                        [\"playerCanDrive\"] = true,\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"y\"] = 0,\n" +
"                                [\"x\"] = 0,\n" +
"                                [\"name\"] = \""+name+"\",\n" +
"                                [\"start_time\"] = 0,\n";
  }

  private String makeFarps(List<Airbases> farps) {
    long ct = 1;
    String farpsStr = "";
    for (Airbases farp: farps) {
      farpsStr += "["+ ct++ +"] = \n" + makeFarp(farp);
      var units = makeFarpUnits(farp.dcsLocation().longitude(), farp.dcsLocation().latitude());
      for (var unit: units)
        farpsStr += "["+ ct++ +"] = \n" + unit;
    }
return
"                    [\"static\"] = \n" +
"                    {\n" +
"                        [\"group\"] = \n" +
"                        {\n" +
farpsStr+
"                        }, -- end of [\"group\"]\n" +
"                    }, -- end of [\"static\"]\n";
  }

  public static String makeFarp(Airbases farp) {
    return
"                            {\n" +
"                                [\"groupId\"] = "+farp.warehouseId()+",\n" +
"                                [\"heading\"] = 0,\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = 0,\n" +
"                                            [\"type\"] = \"\",\n" +
"                                            [\"name\"] = \"\",\n" +
"                                            [\"y\"] = "+farp.dcsLocation().latitude()+",\n" +
"                                            [\"speed\"] = 0,\n" +
"                                            [\"x\"] = "+farp.dcsLocation().longitude()+",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"action\"] = \"\",\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"category\"] = \"Heliports\",\n" +
"                                        [\"shape_name\"] = \"FARPS\",\n" +
"                                        [\"type\"] = \"FARP\",\n" +
"                                        [\"unitId\"] = "+farp.warehouseId()+",\n" +
"                                        [\"heliport_frequency\"] = 127.5,\n" +
"                                        [\"y\"] = "+farp.dcsLocation().latitude()+",\n" +
"                                        [\"x\"] = "+farp.dcsLocation().longitude()+",\n" +
"                                        [\"name\"] = \""+farp.airbaseName()+"\",\n" +
"                                        [\"heliport_modulation\"] = 0,\n" +
"                                        [\"heliport_callsign_id\"] = 1,\n" +
"                                        [\"heading\"] = 0,\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"y\"] = "+farp.dcsLocation().latitude()+",\n" +
"                                [\"x\"] = "+farp.dcsLocation().longitude()+",\n" +
"                                [\"name\"] = \""+farp.airbaseName()+"\",\n" +
"                                [\"dead\"] = false,\n" +
"                            },\n";
  }

  public List<String> makeFarpUnits(BigDecimal x, BigDecimal y) {
    var correctedy = y.add(new BigDecimal(-60d));
    var correctedx = x.add(new BigDecimal(  0d));
    var ct = new int[]{0};
    return Map.of(
      "kp_ug", "FARP CP Blindage",
      "GSM Rus", "FARP Fuel Depot",
      "SetkaKP", "FARP Ammo Dump Coating",
      "PalatkaB", "FARP Tent")
      .entrySet()
      .stream().map(t -> {
        var id = nextId++;
        var newx = correctedx.add(new BigDecimal(20d * ct[0]));
        ct[0] = ct[0] + 1;
        return
"                            {\n" +
"                                [\"heading\"] = 0,\n" +
"                                [\"groupId\"] = "+id+",\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = 0,\n" +
"                                            [\"type\"] = \"\",\n" +
"                                            [\"name\"] = \"\",\n" +
"                                            [\"y\"] = "+correctedy+",\n" +
"                                            [\"speed\"] = 0,\n" +
"                                            [\"x\"] = "+newx+",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"action\"] = \"\",\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"category\"] = \"Fortifications\",\n" +
"                                        [\"shape_name\"] = \""+t.getKey()+"\",\n" +
"                                        [\"type\"] = \""+t.getValue()+"\",\n" +
"                                        [\"unitId\"] = "+id+",\n" +
"                                        [\"y\"] = "+correctedy+",\n" +
"                                        [\"x\"] = "+newx+",\n" +
"                                        [\"name\"] = \"\",\n" +
"                                        [\"heading\"] = 0,\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"y\"] = "+correctedy+",\n" +
"                                [\"x\"] = "+newx+",\n" +
"                                [\"name\"] = \"\",\n" +
"                                [\"dead\"] = false,\n" +
"                            },\n";
      }).collect(toList());
  }

  private String makeCarriers(List<Airbases> carriers) {
    long ct = 1;
    String carrierStr = "";
    for (Airbases carrier: carriers) {
      carrierStr +=
"                            ["+ ct++ +"] = \n" +
"                            {\n"+
        makeCarrier(carrier)+
"                            }\n";
    }
return
"                    [\"ship\"] = \n" +
"                    {\n" +
"                        [\"group\"] = \n" +
"                        {\n" +
carrierStr+
"                        }, -- end of [\"group\"]\n" +
"                    }, -- end of [\"ship\"]\n";
  }

  String makeCarrier(Airbases carrier) {
    return
"                                [\"visible\"] = false,\n" +
"                                [\"tasks\"] = \n" +
"                                {\n" +
"                                }, -- end of [\"tasks\"]\n" +
"                                [\"uncontrollable\"] = false,\n" +
"                                [\"route\"] = \n" +
"                                {\n" +
"                                    [\"points\"] = \n" +
"                                    {\n" +
"                                        [1] = \n" +
"                                        {\n" +
"                                            [\"alt\"] = -0,\n" +
"                                            [\"type\"] = \"Turning Point\",\n" +
"                                            [\"ETA\"] = 0,\n" +
"                                            [\"alt_type\"] = \"BARO\",\n" +
"                                            [\"formation_template\"] = \"\",\n" +
"                                            [\"y\"] = "+carrier.dcsLocation().latitude()+",\n" +
"                                            [\"x\"] = "+carrier.dcsLocation().longitude()+",\n" +
"                                            [\"name\"] = \""+carrier.airbaseName()+"\",\n" +
"                                            [\"ETA_locked\"] = true,\n" +
"                                            [\"speed\"] = 0,\n" +
"                                            [\"action\"] = \"Turning Point\",\n" +
"                                            [\"task\"] = \n" +
"                                            {\n" +
"                                                [\"id\"] = \"ComboTask\",\n" +
"                                                [\"params\"] = \n" +
"                                                {\n" +
"                                                    [\"tasks\"] = \n" +
"                                                    {\n" +
"                                                        [1] = \n" +
"                                                        {\n" +
"                                                            [\"enabled\"] = true,\n" +
"                                                            [\"auto\"] = true,\n" +
"                                                            [\"id\"] = \"WrappedAction\",\n" +
"                                                            [\"number\"] = 1,\n" +
"                                                            [\"params\"] = \n" +
"                                                            {\n" +
"                                                                [\"action\"] = \n" +
"                                                                {\n" +
"                                                                    [\"id\"] = \"ActivateBeacon\",\n" +
"                                                                    [\"params\"] = \n" +
"                                                                    {\n" +
"                                                                        [\"type\"] = 4,\n" +
"                                                                        [\"frequency\"] = 1088000000,\n" +
"                                                                        [\"callsign\"] = \"TKR\",\n" +
"                                                                        [\"channel\"] = 1,\n" +
"                                                                        [\"modeChannel\"] = \"X\",\n" +
"                                                                        [\"bearing\"] = true,\n" +
"                                                                        [\"system\"] = 4,\n" +
"                                                                    }, -- end of [\"params\"]\n" +
"                                                                }, -- end of [\"action\"]\n" +
"                                                            }, -- end of [\"params\"]\n" +
"                                                        }, -- end of [1]\n" +
"                                                    }, -- end of [\"tasks\"]\n" +
"                                                }, -- end of [\"params\"]\n" +
"                                            }, -- end of [\"task\"]\n" +
"                                            [\"speed_locked\"] = true,\n" +
"                                        }, -- end of [1]\n" +
"                                    }, -- end of [\"points\"]\n" +
"                                }, -- end of [\"route\"]\n" +
"                                [\"groupId\"] = "+carrier.warehouseId()+",\n" +
"                                [\"hidden\"] = false,\n" +
"                                [\"units\"] = \n" +
"                                {\n" +
"                                    [1] = \n" +
"                                    {\n" +
"                                        [\"transportable\"] = \n" +
"                                        {\n" +
"                                            [\"randomTransportable\"] = false,\n" +
"                                        }, -- end of [\"transportable\"]\n" +
"                                        [\"skill\"] = \"Average\",\n" +
"                                        [\"type\"] = \"CVN_71\",\n" +
"                                        [\"unitId\"] = "+carrier.warehouseId()+",\n" +
"                                        [\"y\"] = "+carrier.dcsLocation().latitude()+",\n" +
"                                        [\"x\"] = "+carrier.dcsLocation().longitude()+",\n" +
"                                        [\"name\"] = \""+carrier.airbaseName()+"\",\n" +
"                                        [\"heading\"] = 0,\n" +
"                                        [\"modulation\"] = 0,\n" +
"                                        [\"frequency\"] = 127500000,\n" +
"                                    }, -- end of [1]\n" +
"                                }, -- end of [\"units\"]\n" +
"                                [\"y\"] = "+carrier.dcsLocation().latitude()+",\n" +
"                                [\"x\"] = "+carrier.dcsLocation().longitude()+",\n" +
"                                [\"name\"] = \""+carrier.airbaseName()+"\",\n" +
"                                [\"start_time\"] = 0,";
  }

  private String makeAircraftProps(String type) {
    if (WarehouseItemCode.F_14_B.name().equals(type))
    return
"                                                [\"INSAlignmentStored\"] = true,";
    else
      return "";
  }
}
