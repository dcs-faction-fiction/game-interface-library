package base.game;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface FactionSituation {
  UUID id();
  String campaign();
  String faction();
  BigDecimal credits();
  int zoneSizeFt();
  List<FactionAirbase> airbases();
  List<FactionUnit> units();
}
