package base.game.units;

public class OptionsBuilder {

  private OptionsBuilder() {}

  public static String build(MissionOptions options) {
    // Options are empty because makeOptions() is used in the forced options in the mission file instead.
    return
"options = \n" +
"{\n" +
"    [\"playerName\"] = \"New callsign\",\n" +
"    [\"miscellaneous\"] = \n" +
"    {\n" +
"        [\"allow_server_screenshots\"] = true,\n" +
"        [\"headmove\"] = true,\n" +
"        [\"TrackIR_external_views\"] = false,\n" +
"        [\"f5_nearest_ac\"] = true,\n" +
"        [\"f11_free_camera\"] = true,\n" +
"        [\"F2_view_effects\"] = 0,\n" +
"        [\"f10_awacs\"] = true,\n" +
"        [\"Coordinate_Display\"] = \"Precise Lat Long\",\n" +
"        [\"accidental_failures\"] = false,\n" +
"        [\"autologin\"] = true,\n" +
"        [\"force_feedback_enabled\"] = false,\n" +
"        [\"collect_stat\"] = false,\n" +
"        [\"chat_window_at_start\"] = false,\n" +
"        [\"synchronize_controls\"] = true,\n" +
"        [\"show_pilot_body\"] = false,\n" +
"    }, -- end of [\"miscellaneous\"]\n" +
"    [\"difficulty\"] = \n" +
"    {\n" +
 makeOptions(options) +
"    }, -- end of [\"difficulty\"]\n" +
"    [\"VR\"] = \n" +
"    {\n" +
"        [\"enable\"] = true,\n" +
"        [\"box_mouse_cursor\"] = true,\n" +
"        [\"pixel_density\"] = 1.6,\n" +
"        [\"use_mouse\"] = true,\n" +
"        [\"msaaMaskSize\"] = 0.5,\n" +
"        [\"prefer_built_in_audio\"] = false,\n" +
"        [\"interaction_with_grip_only\"] = false,\n" +
"        [\"bloom\"] = false,\n" +
"        [\"custom_IPD_enable\"] = true,\n" +
"        [\"custom_IPD\"] = \"68\",\n" +
"        [\"hand_controllers\"] = false,\n" +
"    }, -- end of [\"VR\"]\n" +
"    [\"graphics\"] = \n" +
"    {\n" +
"        [\"messagesFontScale\"] = 1.25,\n" +
"        [\"rainDroplets\"] = false,\n" +
"        [\"preloadRadius\"] = 75000,\n" +
"        [\"heatBlr\"] = 0,\n" +
"        [\"anisotropy\"] = 2,\n" +
"        [\"water\"] = 1,\n" +
"        [\"motionBlur\"] = 0,\n" +
"        [\"outputGamma\"] = 1.9,\n" +
"        [\"treesVisibility\"] = 5000,\n" +
"        [\"aspect\"] = 1.6666666666667,\n" +
"        [\"textures\"] = 1,\n" +
"        [\"shadows\"] = 2,\n" +
"        [\"MSAA\"] = 0,\n" +
"        [\"SSAA\"] = 0,\n" +
"        [\"height\"] = 768,\n" +
"        [\"forestDistanceFactor\"] = 0.9,\n" +
"        [\"cockpitGI\"] = 0,\n" +
"        [\"terrainTextures\"] = \"min\",\n" +
"        [\"multiMonitorSetup\"] = \"1camera\",\n" +
"        [\"shadowTree\"] = false,\n" +
"        [\"chimneySmokeDensity\"] = 2,\n" +
"        [\"fullScreen\"] = false,\n" +
"        [\"DOF\"] = 0,\n" +
"        [\"clouds\"] = 1,\n" +
"        [\"flatTerrainShadows\"] = 1,\n" +
"        [\"useDeferredShading\"] = 1,\n" +
"        [\"width\"] = 1280,\n" +
"        [\"SSLR\"] = 0,\n" +
"        [\"effects\"] = 3,\n" +
"        [\"SSAO\"] = 0,\n" +
"        [\"lights\"] = 2,\n" +
"        [\"sync\"] = false,\n" +
"        [\"LensEffects\"] = 0,\n" +
"        [\"visibRange\"] = \"Ultra\",\n" +
"        [\"clutterMaxDistance\"] = 300,\n" +
"        [\"scaleGui\"] = 1.25,\n" +
"        [\"civTraffic\"] = \"\",\n" +
"    }, -- end of [\"graphics\"]\n" +
"    [\"plugins\"] = \n" +
"    {\n" +
"        [\"CA\"] = \n" +
"        {\n" +
"            [\"kompass_options\"] = 1,\n" +
"            [\"ground_target_info\"] = true,\n" +
"            [\"ground_aim_helper\"] = true,\n" +
"            [\"ground_platform_shake\"] = true,\n" +
"            [\"ground_automatic\"] = true,\n" +
"        }, -- end of [\"CA\"]\n" +
"        [\"JF-17\"] = \n" +
"        {\n" +
"            [\"PROG04FLBI\"] = 3,\n" +
"            [\"PROG04FLN\"] = 4,\n" +
"            [\"PROG01FLBI\"] = 3,\n" +
"            [\"PROG03CHBI\"] = 3,\n" +
"            [\"PROG04FLI\"] = 2,\n" +
"            [\"PROG01FLR\"] = 1,\n" +
"            [\"MFCD_WMD_TDC\"] = 1,\n" +
"            [\"PROG02CHI\"] = 2,\n" +
"            [\"PROG05FLN\"] = 4,\n" +
"            [\"PROG02CHN\"] = 3,\n" +
"            [\"PROG04CHR\"] = 1,\n" +
"            [\"PROG04CHBI\"] = 3,\n" +
"            [\"PROG02TYPE\"] = 0,\n" +
"            [\"KYBDPITCH\"] = 0,\n" +
"            [\"PROG02FLBI\"] = 3,\n" +
"            [\"PROG02CHR\"] = 3,\n" +
"            [\"CHAFFBINGO\"] = 10,\n" +
"            [\"PROG03TYPE\"] = 1,\n" +
"            [\"PROG03CHR\"] = 1,\n" +
"            [\"PROG01CHI\"] = 3,\n" +
"            [\"PROG05CHI\"] = 1,\n" +
"            [\"DMAPTYPE\"] = 0,\n" +
"            [\"PROG03FLR\"] = 2,\n" +
"            [\"PROG05FLR\"] = 3,\n" +
"            [\"PROG02FLR\"] = 1,\n" +
"            [\"PROG01TYPE\"] = 0,\n" +
"            [\"PROG03FLN\"] = 4,\n" +
"            [\"VOICELOCALE\"] = 0,\n" +
"            [\"PROG03CHN\"] = 1,\n" +
"            [\"PROG05FLI\"] = 1,\n" +
"            [\"PROG03CHI\"] = 2,\n" +
"            [\"PROG03FLBI\"] = 0,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"PROG05CHR\"] = 3,\n" +
"            [\"PROG02FLI\"] = 2,\n" +
"            [\"PROG05CHBI\"] = 0,\n" +
"            [\"FLAREBINGO\"] = 8,\n" +
"            [\"PROG04CHN\"] = 1,\n" +
"            [\"MFCD_TVIR_TDC\"] = 1,\n" +
"            [\"MFCD_RDR_TDC\"] = 1,\n" +
"            [\"PROG01FLI\"] = 2,\n" +
"            [\"PROG04TYPE\"] = 1,\n" +
"            [\"PROG05TYPE\"] = 2,\n" +
"            [\"PROG05FLBI\"] = 0,\n" +
"            [\"HiddenStick\"] = false,\n" +
"            [\"PROG03FLI\"] = 3,\n" +
"            [\"PROG01CHR\"] = 3,\n" +
"            [\"PROG02CHBI\"] = 3,\n" +
"            [\"PROG01CHBI\"] = 0,\n" +
"            [\"MUSICNUM_SLIDER\"] = 10,\n" +
"            [\"TDC_DEADZONE\"] = 0.02,\n" +
"            [\"PROG04FLR\"] = 3,\n" +
"            [\"PROG05CHN\"] = 4,\n" +
"            [\"MFCD_DLPOD_TDC\"] = 1,\n" +
"            [\"PROG02FLN\"] = 1,\n" +
"            [\"PROG04CHI\"] = 2,\n" +
"            [\"HUD_RDR_TDC\"] = 1,\n" +
"            [\"PROG01CHN\"] = 4,\n" +
"            [\"PROG01FLN\"] = 1,\n" +
"        }, -- end of [\"JF-17\"]\n" +
"        [\"F-16C\"] = \n" +
"        {\n" +
"            [\"abDetent\"] = 0,\n" +
"            [\"canopyReflections\"] = 1,\n" +
"            [\"hmdEye\"] = 2,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"canopyTint\"] = 1,\n" +
"            [\"mfdReflections\"] = 1,\n" +
"        }, -- end of [\"F-16C\"]\n" +
"        [\"Ka-50\"] = \n" +
"        {\n" +
"            [\"Ka50TrimmingMethod\"] = 0,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"Ka50RudderTrimmer\"] = false,\n" +
"            [\"helmetCircleDisplacement\"] = 11,\n" +
"        }, -- end of [\"Ka-50\"]\n" +
"        [\"F/A-18C\"] = \n" +
"        {\n" +
"            [\"abDetent\"] = 0,\n" +
"            [\"canopyReflections\"] = 1,\n" +
"            [\"hmdEye\"] = 2,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"F18RealisticTDC\"] = false,\n" +
"            [\"mfdReflections\"] = 1,\n" +
"        }, -- end of [\"F/A-18C\"]\n" +
"        [\"CaptoGlove\"] = \n" +
"        {\n" +
"            [\"shoulderJointZ_Right\"] = 23,\n" +
"            [\"armBending\"] = 60,\n" +
"            [\"shoulderJointX_Right\"] = -15,\n" +
"            [\"mouseClickSrc\"] = 0,\n" +
"            [\"shoulderJointZ_Left\"] = 23,\n" +
"            [\"shoulderJointX_Left\"] = -15,\n" +
"            [\"set_debug\"] = false,\n" +
"            [\"pitchOffsetGlove_Left\"] = 0,\n" +
"            [\"yawOffsetGlove_Left\"] = 0,\n" +
"            [\"yawOffsetShoulder_Right\"] = 0,\n" +
"            [\"useBending\"] = false,\n" +
"            [\"shoulderLength_Right\"] = 40,\n" +
"            [\"pitchOffsetGlove_Right\"] = 0,\n" +
"            [\"shoulderJointY_Left\"] = -23,\n" +
"            [\"forearmLength_Left\"] = 30,\n" +
"            [\"shoulderLength_Left\"] = 40,\n" +
"            [\"set_attach\"] = false,\n" +
"            [\"pitchOffsetShoulder_Right\"] = 0,\n" +
"            [\"forearmLength_Right\"] = 30,\n" +
"            [\"pitchOffsetShoulder_Left\"] = 0,\n" +
"            [\"set_symmetrically\"] = false,\n" +
"            [\"yawOffsetShoulder_Left\"] = 0,\n" +
"            [\"enable\"] = false,\n" +
"            [\"shoulderJointY_Right\"] = -23,\n" +
"            [\"yawOffsetGlove_Right\"] = 0,\n" +
"        }, -- end of [\"CaptoGlove\"]\n" +
"        [\"DCS-SRS\"] = \n" +
"        {\n" +
"            [\"srsOverlayEnabled\"] = false,\n" +
"            [\"srsAutoLaunchEnabled\"] = true,\n" +
"            [\"srsOverlayCompactModeEnabled\"] = false,\n" +
"        }, -- end of [\"DCS-SRS\"]\n" +
"        [\"Tacview\"] = \n" +
"        {\n" +
"            [\"tacviewProfilingPeriod\"] = 600,\n" +
"            [\"tacviewHostTelemetryPassword\"] = \"\",\n" +
"            [\"tacviewRemoteControlPort\"] = \"42675\",\n" +
"            [\"tacviewDataRecordingEnabled\"] = true,\n" +
"            [\"tacviewTerrainExport\"] = 0,\n" +
"            [\"tacviewRecordClientsSessionsEnabled\"] = false,\n" +
"            [\"tacviewPlaybackDelay\"] = 0,\n" +
"            [\"tacviewBookmarkShortcut\"] = 0,\n" +
"            [\"tacviewCompressionLevel\"] = 1,\n" +
"            [\"tacviewDebugModeEnabled\"] = false,\n" +
"            [\"tacviewRealTimeTelemetryPort\"] = \"42674\",\n" +
"            [\"tacviewRemoteControlPassword\"] = \"\",\n" +
"        }, -- end of [\"Tacview\"]\n" +
"        [\"AV8BNA\"] = \n" +
"        {\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"INS_Alignment\"] = 0,\n" +
"            [\"INS_GYROHasNAV\"] = false,\n" +
"            [\"MPCD_EXPORT\"] = false,\n" +
"        }, -- end of [\"AV8BNA\"]\n" +
"        [\"F-5E-3\"] = \n" +
"        {\n" +
"            [\"JoystickMode\"] = 0,\n" +
"            [\"SightCamera\"] = 0,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"aiHelper\"] = false,\n" +
"        }, -- end of [\"F-5E-3\"]\n" +
"        [\"Su-25T\"] = \n" +
"        {\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"        }, -- end of [\"Su-25T\"]\n" +
"        [\"F-14B\"] = \n" +
"        {\n" +
"            [\"JESTER_HeadMenu\"] = true,\n" +
"            [\"FFB_TRIM\"] = false,\n" +
"            [\"JESTER_LandingCallouts\"] = true,\n" +
"            [\"JESTER_SwitchToPSTT\"] = true,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"TID_A2G\"] = false,\n" +
"            [\"WEAP_OFF_GUN\"] = false,\n" +
"            [\"JESTER_Camera\"] = true,\n" +
"            [\"RadioMenuPttOptions\"] = 0,\n" +
"        }, -- end of [\"F-14B\"]\n" +
"        [\"TF-51D\"] = \n" +
"        {\n" +
"            [\"assistance\"] = 100,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"autoRudder\"] = false,\n" +
"        }, -- end of [\"TF-51D\"]\n" +
"        [\"UH-1H\"] = \n" +
"        {\n" +
"            [\"UHRudderTrimmer\"] = false,\n" +
"            [\"autoPilot\"] = true,\n" +
"            [\"UH1HCockpitShake\"] = 50,\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"            [\"weapTooltips\"] = true,\n" +
"            [\"UHTrimmingMethod\"] = 0,\n" +
"        }, -- end of [\"UH-1H\"]\n" +
"        [\"A-10C\"] = \n" +
"        {\n" +
"            [\"CPLocalList\"] = \"default\",\n" +
"        }, -- end of [\"A-10C\"]\n" +
"    }, -- end of [\"plugins\"]\n" +
"    [\"format\"] = 1,\n" +
"    [\"sound\"] = \n" +
"    {\n" +
"        [\"main_output\"] = \"\",\n" +
"        [\"FakeAfterburner\"] = true,\n" +
"        [\"volume\"] = 100,\n" +
"        [\"headphones_on_external_views\"] = true,\n" +
"        [\"subtitles\"] = false,\n" +
"        [\"world\"] = 10,\n" +
"        [\"hear_in_helmet\"] = true,\n" +
"        [\"radioSpeech\"] = true,\n" +
"        [\"hp_output\"] = \"\",\n" +
"        [\"cockpit\"] = 50,\n" +
"        [\"voice_chat_output\"] = \"0:{0.0.0.00000000}.{8c7ecfdc-3b34-4962-9dc5-48bf75624e68}\",\n" +
"        [\"voice_chat\"] = false,\n" +
"        [\"microphone_use\"] = 2,\n" +
"        [\"GBreathEffect\"] = true,\n" +
"        [\"switches\"] = 180,\n" +
"        [\"play_audio_while_minimized\"] = false,\n" +
"        [\"headphones\"] = 50,\n" +
"        [\"music\"] = 0,\n" +
"        [\"voice_chat_input\"] = \"0:{0.0.1.00000000}.{c9e2ba5e-0c73-4991-bfe2-a668e872718d}\",\n" +
"        [\"gui\"] = 100,\n" +
"    }, -- end of [\"sound\"]\n" +
"    [\"views\"] = \n" +
"    {\n" +
"        [\"cockpit\"] = \n" +
"        {\n" +
"            [\"mirrors\"] = false,\n" +
"            [\"reflections\"] = false,\n" +
"            [\"avionics\"] = 3,\n" +
"        }, -- end of [\"cockpit\"]\n" +
"    }, -- end of [\"views\"]\n" +
"} -- end of options";
  }

  public static String makeOptions(MissionOptions options) {
return
"        [\"externalViews\"] = "+options.externalViews()+",\n" +
    makeExternalViewType(options) +
"        [\"fuel\"] = false,\n" +
"        [\"easyRadar\"] = false,\n" +
"        [\"miniHUD\"] = false,\n" +
"        [\"accidental_failures\"] = false,\n" +
"        [\"setGlobal\"] = true,\n" +
"        [\"avionicsLanguage\"] = \"english\",\n" +
"        [\"cockpitVisualRM\"] = false,\n" +
"        [\"reports\"] = true,\n" +
"        [\"unrestrictedSATNAV\"] = true,\n" +
"        [\"userSnapView\"] = false,\n" +
"        [\"iconsTheme\"] = \"russian\",\n" +
"        [\"civTraffic\"] = \"\",\n" +
"        [\"weapons\"] = false,\n" +
"        [\"padlock\"] = false,\n" +
"        [\"birds\"] = 0,\n" +
"        [\"permitCrash\"] = false,\n" +
"        [\"immortal\"] = false,\n" +
"        [\"easyCommunication\"] = false,\n" +
"        [\"wakeTurbulence\"] = false,\n" +
"        [\"easyFlight\"] = false,\n" +
"        [\"hideStick\"] = true,\n" +
"        [\"radio\"] = false,\n" +
"        [\"labels\"] = 0,\n" +
"        [\"units\"] = \"imperial\",\n" +
"        [\"tips\"] = true,\n" +
"        [\"userMarks\"] = true,\n" +
"        [\"RBDAI\"] = false,\n" +
"        [\"controlsIndicator\"] = false,\n" +
"        [\"spectatorExternalViews\"] = true,\n" +
"        [\"cockpitStatusBarAllowed\"] = false,\n" +
"        [\"map\"] = true,\n" +
"        [\"geffect\"] = \"realistic\"";
  }

  static String makeExternalViewType(MissionOptions options) {
    return options.externalViewType().map(o ->
"        [\"optionsView\"] = \""+o.name()+"\",\n"
).orElse("");
  }
}
