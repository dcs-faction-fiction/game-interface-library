package base.game;

import static base.game.CampaignMap.CAUCASUS;
import java.util.Arrays;
import java.util.Optional;

public enum Airbases {

  ANAPA(12, "Anapa-Vityazevo", CAUCASUS, Location.of("45.0045166", "37.3466833", "141"), false),
  KRYMSK(15, "Krymsk", CAUCASUS, Location.of("44.96881666", "37.99353333", "66"), false),
  MAYKOP(16, "Maykop-Khanskaya", CAUCASUS, Location.of("44.67143", "40.18808", "180"), false),
  SOCHI(18, "Sochi-Adler", CAUCASUS, Location.of("43.44441666", "39.94143333", "98"), false),
  SUKHUMI(20, "Sukhumi-Babushara", CAUCASUS, Location.of("43.113816", "40.57033", "43"), false),
  GUDAUTA(21, "Gudauta", CAUCASUS, Location.of("43.113816", "40.57033", "69"), false),
  BATUMI(22, "Batumi", CAUCASUS, Location.of("41.60975", "41.5999", "33"), false),
  SENAKI(23, "Senaki-Kolkhi", CAUCASUS, Location.of("42.240266666", "42.0479", "43"), false),
  KOBULETI(24, "Kobuleti", CAUCASUS, Location.of("41.93015", "41.864233333", "59"), false),
  KUTAISI(25, "Kutaisi", CAUCASUS, Location.of("42.17915", "42.49568", "45"), false),
  MINVODY(26, "Mineralnye Vody", CAUCASUS, Location.of("44.22835", "43.08255", "1050"), false),
  NALCHIK(27, "Nalchik", CAUCASUS, Location.of("43.51345", "43.63318", "1411"), false),
  TBILISI(29, "Tbilisi-Lochini", CAUCASUS, Location.of("41.6704", "44.95535", "1575"), false),
  VAZIANI(31, "Vaziani", CAUCASUS, Location.of("41.6285666", "45.0278833", "1526"), false),
  BESLAN(32, "Beslan", CAUCASUS, Location.of("43.2058", "44.605783", "1742"), false),
  MOZDOK(28, "Mozdok", CAUCASUS, Location.of("43.7918", "44.60486", "509"), false),
  KRASNODAR_CENTER(13, "Krasnodar-Center", CAUCASUS, Location.of("45.08685", "38.939166", "98"), false),
  KRASNODAR_PASH(19, "Krasnodar-Pashkovsky", CAUCASUS, Location.of("45.016666", "39.195166", "112"), false),
  NOVOROSSIYSK(14, "Novorossiysk", CAUCASUS, Location.of("44.66838", "37.77855", "131"), false),
  GELENDZHIK(17, "Gelendzhik", CAUCASUS, Location.of("44.574033", "38.013233", "72"), false),
  // Farps
  FARP_KASPI(10_000, "FARP_Kaspi", CAUCASUS, Location.of("41.929666", "44.38475", "1742"), true),
  ;

  private final int warehouseId;
  private final String airbaseName;
  private final CampaignMap map;
  private final Location location;
  private final boolean farp;

  private Airbases(
    int warehouseId,
    String airbaseName,
    CampaignMap map,
    Location location,
    boolean farp) {

    this.warehouseId = warehouseId;
    this.airbaseName = airbaseName;
    this.map = map;
    this.location = location;
    this.farp = farp;
  }

  public int warehouseId() {
    return warehouseId;
  }

  public Location location() {
    return location;
  }

  public CampaignMap map() {
    return map;
  }

  public boolean farp() {
    return farp;
  }

  public static Optional<Airbases> fromName(String name) {
    return Arrays.asList(values()).stream()
      .filter(o -> o.airbaseName.equals(name))
      .findFirst();
  }

}
