package base.game.units;

public class OptionsBuilder {

  private OptionsBuilder() {}

  public static String build() {
    // Options are empoty because makeOptions() is used in the forced options in the mission file instead.
    return "options = {}";
  }

  public static String makeOptions(MissionOptions options) {
return
"        [\"externalViews\"] = "+options.externalViews()+",\n" +
    makeExternalViewType(options) +
"        [\"geffect\"] = \"realistic\",\n" +
"        [\"padlock\"] = true,\n" +
"        [\"cockpitStatusBarAllowed\"] = false,\n" +
"        [\"wakeTurbulence\"] = false,\n" +
"        [\"map\"] = true,\n" +
"        [\"easyRadar\"] = false,\n" +
"        [\"fuel\"] = false,\n" +
"        [\"miniHUD\"] = false,\n" +
"        [\"controlsIndicator\"] = false,\n" +
"        [\"birds\"] = 0,\n" +
"        [\"permitCrash\"] = true,\n" +
"        [\"immortal\"] = false,\n" +
"        [\"easyCommunication\"] = false,\n" +
"        [\"cockpitVisualRM\"] = false,\n" +
"        [\"easyFlight\"] = false,\n" +
"        [\"reports\"] = true,\n" +
"        [\"hideStick\"] = true,\n" +
"        [\"radio\"] = false,\n" +
"        [\"userMarks\"] = true,\n" +
"        [\"unrestrictedSATNAV\"] = true,\n" +
"        [\"units\"] = \"imperial\",\n" +
"        [\"spectatorExternalViews\"] = true,\n" +
"        [\"tips\"] = true,\n" +
"        [\"userSnapView\"] = false,\n" +
"        [\"RBDAI\"] = false,\n" +
"        [\"iconsTheme\"] = \"russian\",\n" +
"        [\"avionicsLanguage\"] = \"english\",\n" +
"        [\"weapons\"] = false,\n" +
"        [\"setGlobal\"] = true,\n" +
"        [\"labels\"] = 0,\n";
  }

  static String makeExternalViewType(MissionOptions options) {
    return options.externalViewType().map(o ->
"        [\"optionsView\"] = \""+o.name()+"\",\n"
).orElse("");
  }
}
