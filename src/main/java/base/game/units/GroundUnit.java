package base.game.units;

import static base.game.units.GroundUnitType.AAA;
import static base.game.units.GroundUnitType.AIR;
import static base.game.units.GroundUnitType.IFV;
import static base.game.units.GroundUnitType.MBT;
import static base.game.units.GroundUnitType.SAM;

public enum GroundUnit {

  // AWACS AND TANKER ARE PARSED BY THE DCS SCRIPTS JUST LIKE SAMS
  AWACS        (AIR, "M 818"),
  TANKER       (AIR, "M 818"),

  // TANKS
  ABRAMS       (MBT, "M-1 Abrams"),
  T_80         (MBT, "T-80UD"),
  // IFV
  BRADLEY      (IFV, "M-2 Bradley"),
  BMP_3        (IFV, "BMP-3"),
  // AAA
  SHILKA       (AAA, "ZSU-23-4 Shilka"),
  // Small IR SAMS
  SA_9         (SAM, "Strela-1 9P31"),
  SA_13        (SAM, "Strela-10M3"),
  SA_15        (SAM, "Tor 9A331"),
  SA_19        (SAM, "2S6 Tunguska"),
  SA_6         (SAM, "M 818"),
  SA_11        (SAM, "M 818"),
  SA_HAWK      (SAM, "M 818"),
  SA_10        (SAM, "M 818"),
  SA_PATRIOT   (SAM, "M 818"),
  ;

  private final GroundUnitType type;
  private final String dcstype;

  private GroundUnit(GroundUnitType type, String dcstype) {
    this.type = type;
    this.dcstype = dcstype;
  }

  public String dcstype() {
    return dcstype;
  }

}
