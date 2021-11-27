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
    private static final String PREF_SUDOKU = "sudoku";
    private static final String PREF_CONTINUATION_ENABLED = "continuation.enabled";
    private static final String PREF_MINUTE = "time_minute";
    private static final String PREF_SECOND = "time_second";

    private static Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public static void saveSudoku(String sudoku){
        getPrefs().putString(PREF_SUDOKU, sudoku);
        getPrefs().flush();
    }
    public static String loadSudoku(){
        return getPrefs().getString(PREF_SUDOKU);
    }

    public static boolean isContinuationEnabled(){
        return getPrefs().getBoolean(PREF_CONTINUATION_ENABLED, false);
    }

    public static void setContinuationEnabled(boolean continuationEnabled){
        getPrefs().putBoolean(PREF_CONTINUATION_ENABLED, continuationEnabled);
        getPrefs().flush();
    }

    public static int getTimeMinute(){
        return getPrefs().getInteger(PREF_MINUTE, 0);
    }

    public static int getTimeSecond(){
        return getPrefs().getInteger(PREF_SECOND, 0);
    }

    public static void setTimeMinute(int minute){
        getPrefs().putInteger(PREF_MINUTE, minute);
        getPrefs().flush();
    }

    public static void setTimeSecond(int second){
        getPrefs().putInteger(PREF_SECOND, second);
        getPrefs().flush();
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
