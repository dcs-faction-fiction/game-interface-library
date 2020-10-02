package base.game;

import static base.game.CampaignMap.CAUCASUS;
import java.util.Arrays;
import java.util.Optional;

public enum Airbases {

  ANAPA(12, "Anapa-Vityazevo", CAUCASUS, Location.of("45.0045166", "37.3466833", "141"), null, false, false),
  KRYMSK(15, "Krymsk", CAUCASUS, Location.of("44.96881666", "37.99353333", "66"), null, false, false),
  MAYKOP(16, "Maykop-Khanskaya", CAUCASUS, Location.of("44.67143", "40.18808", "180"), null, false, false),
  SOCHI(18, "Sochi-Adler", CAUCASUS, Location.of("43.44441666", "39.94143333", "98"), null, false, false),
  SUKHUMI(20, "Sukhumi-Babushara", CAUCASUS, Location.of("43.113816", "40.57033", "43"), null, false, false),
  GUDAUTA(21, "Gudauta", CAUCASUS, Location.of("43.113816", "40.57033", "69"), null, false, false),
  BATUMI(22, "Batumi", CAUCASUS, Location.of("41.60975", "41.5999", "33"), null, false, false),
  SENAKI(23, "Senaki-Kolkhi", CAUCASUS, Location.of("42.240266666", "42.0479", "43"), null, false, false),
  KOBULETI(24, "Kobuleti", CAUCASUS, Location.of("41.93015", "41.864233333", "59"), null, false, false),
  KUTAISI(25, "Kutaisi", CAUCASUS, Location.of("42.17915", "42.49568", "45"), null, false, false),
  MINVODY(26, "Mineralnye Vody", CAUCASUS, Location.of("44.22835", "43.08255", "1050"), null, false, false),
  NALCHIK(27, "Nalchik", CAUCASUS, Location.of("43.51345", "43.63318", "1411"), null, false, false),
  TBILISI(29, "Tbilisi-Lochini", CAUCASUS, Location.of("41.6704", "44.95535", "1575"), null, false, false),
  VAZIANI(31, "Vaziani", CAUCASUS, Location.of("41.6285666", "45.0278833", "1526"), null, false, false),
  BESLAN(32, "Beslan", CAUCASUS, Location.of("43.2058", "44.605783", "1742"), null, false, false),
  MOZDOK(28, "Mozdok", CAUCASUS, Location.of("43.7918", "44.60486", "509"), null, false, false),
  KRASNODAR_CENTER(13, "Krasnodar-Center", CAUCASUS, Location.of("45.08685", "38.939166", "98"), null, false, false),
  KRASNODAR_PASH(19, "Krasnodar-Pashkovsky", CAUCASUS, Location.of("45.016666", "39.195166", "112"), null, false, false),
  NOVOROSSIYSK(14, "Novorossiysk", CAUCASUS, Location.of("44.66838", "37.77855", "131"), null, false, false),
  GELENDZHIK(17, "Gelendzhik", CAUCASUS, Location.of("44.574033", "38.013233", "72"), null, false, false),
  // Farps
  FARP_KASPI(10_000, "FARP_Kaspi", CAUCASUS, Location.of("41.929666", "44.38475", "1742"), Location.of("845020.76607711", "-292996.10596527"), true, false),
  FARP_ZESTAFONI(10_001, "FARP_Zestafoni", CAUCASUS, Location.of("42.120816666", "43.018833333", "538"), Location.of("729058.71395477", "-286082.8441803"), true, false),
  FARP_KHASHURI(10_002, "FARP_Zestafoni", CAUCASUS, Location.of("41.9852166", "43.6325333", "2254"), Location.of("781760.20100281", "-294944.51849995"), true, false),
  FARP_GORI(10_003, "FARP_Gori", CAUCASUS, Location.of("42.0053333", "44.1032166", "1936"), Location.of("820716.94095054", "-288448.86308349"), true, false),
  // Carriers
  SUPERCARRIER_SNAKE(11_000, "SUPERCARRIER_SNAKE", CAUCASUS, Location.of("42.4886", "40.2774666", "0"), Location.of("498688.2452264", "-268238.84596592"), false, true),
  ;

  private final int warehouseId;
  private final String airbaseName;
  private final CampaignMap map;
  private final Location location;
  private final Location dcsLocation;
  private final boolean farp;
  private final boolean carrier;

  private Airbases(
    int warehouseId,
    String airbaseName,
    CampaignMap map,
    Location location,
    Location dcsLocation,
    boolean farp,
    boolean carrier) {

    this.warehouseId = warehouseId;
    this.airbaseName = airbaseName;
    this.map = map;
    this.location = location;
    this.dcsLocation = dcsLocation;
    this.farp = farp;
    this.carrier = carrier;
  }

  public int warehouseId() {
    return warehouseId;
  }

  public String airbaseName() {
    return airbaseName;
  }

  public Location location() {
    return location;
  }

  public Location dcsLocation() {
    return dcsLocation;
  }

  public CampaignMap map() {
    return map;
  }

  public boolean farp() {
    return farp;
  }

  public boolean carrier() {
    return carrier;
  }

  public static Optional<Airbases> fromName(String name) {
    return Arrays.asList(values()).stream()
      .filter(o -> o.airbaseName.equals(name))
      .findFirst();
  }

}
