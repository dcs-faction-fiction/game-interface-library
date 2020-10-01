package base.game.warehouse;

import java.util.Set;

public enum WarehouseItemCategory {
  FUEL,
  PLANES,
  HELICOPTERS,
  AMMO_AA,
  AMMO_AG,
  AMMO_AGU;

  public static Set<WarehouseItemCategory> AMMOS = Set.of(AMMO_AA, AMMO_AG, AMMO_AGU);
}
