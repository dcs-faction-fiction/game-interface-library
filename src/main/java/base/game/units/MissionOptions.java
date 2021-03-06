package base.game.units;

import java.util.Optional;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
@Value.Style(jdkOnly = true)
public interface MissionOptions {
  boolean externalViews();
  Optional<MissionOptionExternalView> externalViewType();
}
