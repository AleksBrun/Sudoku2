package com.mycompany.mygame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreference {

    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "b2dtut";
    private static final String PREF_COLOR_INDEX = "color.ui";

    private static Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public static boolean isSoundEffectsEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public static void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }

    public static boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public static void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }

    public static float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public static void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume);
        getPrefs().flush();
    }

    public static float getSoundVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public static void setSoundVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOL, volume);
        getPrefs().flush();
    }

    public static void setColorUI(int indexColor){
        getPrefs().putInteger(PREF_COLOR_INDEX, indexColor);
        getPrefs().flush();
    }

    public static int getColorUI(){
        return getPrefs().getInteger(PREF_COLOR_INDEX, 0);
    }

}
