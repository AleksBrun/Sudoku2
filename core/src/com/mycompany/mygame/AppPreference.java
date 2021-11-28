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
    private static final String PREF_MISSING_DIGITS = "missing_digits";
    private static final Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

    public static void saveSudoku(String sudoku){
        prefs.putString(PREF_SUDOKU, sudoku);
        prefs.flush();
    }

    public static void setMissingDigits(int missing_digits){
        prefs.putInteger(PREF_MISSING_DIGITS, missing_digits);
        prefs.flush();
    }

    public static int getMissingDigits(){
        return prefs.getInteger(PREF_MISSING_DIGITS, 25);
    }

    public static String loadSudoku(){
        return prefs.getString(PREF_SUDOKU);
    }

    public static boolean isContinuationEnabled(){
        return prefs.getBoolean(PREF_CONTINUATION_ENABLED, false);
    }

    public static void setContinuationEnabled(boolean continuationEnabled){
        prefs.putBoolean(PREF_CONTINUATION_ENABLED, continuationEnabled);
        prefs.flush();
    }

    public static int getTimeMinute(){
        return prefs.getInteger(PREF_MINUTE, 0);
    }

    public static int getTimeSecond(){
        return prefs.getInteger(PREF_SECOND, 0);
    }

    public static void setTimeMinute(int minute){
        prefs.putInteger(PREF_MINUTE, minute);
        prefs.flush();
    }

    public static void setTimeSecond(int second){
        prefs.putInteger(PREF_SECOND, second);
        prefs.flush();
    }

    public static boolean isSoundEffectsEnabled() {
        return prefs.getBoolean(PREF_SOUND_ENABLED, true);
    }

    public static void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        prefs.putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        prefs.flush();
    }

    public static boolean isMusicEnabled() {
        return prefs.getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public static void setMusicEnabled(boolean musicEnabled) {
        prefs.putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        prefs.flush();
    }

    public static float getMusicVolume() {
        return prefs.getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public static void setMusicVolume(float volume) {
        prefs.putFloat(PREF_MUSIC_VOLUME, volume);
        prefs.flush();
    }

    public static float getSoundVolume() {
        return prefs.getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public static void setSoundVolume(float volume) {
        prefs.putFloat(PREF_SOUND_VOL, volume);
        prefs.flush();
    }

    public static void setColorUI(int indexColor){
        prefs.putInteger(PREF_COLOR_INDEX, indexColor);
        prefs.flush();
    }

    public static int getColorUI(){
        return prefs.getInteger(PREF_COLOR_INDEX, 0);
    }

}
