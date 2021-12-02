package com.mycompany.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mycompany.unils.Puzzle;

public class AppPreference {
    private static final String PREF_ERROR_GAME = "error.game";
    private static final String PREF_ALL_STARS = "all.stars";
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
    private static final String PREF_DIFFICULTY_LEVEL = "difficulty_level";
    private static final Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);


    // Сохранить колличество ошибок за игру
    public static void setErrorGame(int errorGame){
        prefs.putInteger(PREF_ERROR_GAME, errorGame);
        prefs.flush();
    }
    // Получить колличество ошибок за игру
    public static int getErrorGame(){
        return prefs.getInteger(PREF_ERROR_GAME, 0);
    }
    // сочранить общее число звезд(ОЧКОВ)
    public static void setAllStars(int stars){
        prefs.putInteger(PREF_ALL_STARS, stars);
        prefs.flush();
    }
    // Получить общее число звезд
    public static int getAllStars(){
        return prefs.getInteger(PREF_ALL_STARS, 0);
    }
    // Сохранить ровень сложности
    public static void setDifficultyLevel(int level){
        prefs.putInteger(PREF_DIFFICULTY_LEVEL, level);
        prefs.flush();
    }
    // Получить уровень сложности
    public static int getDifficultyLevel(){
        return prefs.getInteger(PREF_DIFFICULTY_LEVEL);
    }
    // Записать сетку судоку в строке
    public static void saveSudoku(String sudoku) {
        prefs.putString(PREF_SUDOKU, sudoku);
        prefs.flush();
    }
    // Записать колличество пустых ячеек
    public static void setMissingDigits(int missing_digits) {
        prefs.putInteger(PREF_MISSING_DIGITS, missing_digits);
        prefs.flush();
    }
    // Получить колличество пустых ячек
    public static int getMissingDigits() {
        return prefs.getInteger(PREF_MISSING_DIGITS, 25);
    }
    // Получить строку судоку
    public static String loadSudoku() {
        return prefs.getString(PREF_SUDOKU);
    }
    // Проверка возможности продолжить игру
    public static boolean isContinuationEnabled() {
        return prefs.getBoolean(PREF_CONTINUATION_ENABLED, false);
    }
    // Записаить сосояие продолжить игпу
    public static void setContinuationEnabled(boolean continuationEnabled) {
        prefs.putBoolean(PREF_CONTINUATION_ENABLED, continuationEnabled);
        prefs.flush();
    }

    public static int getTimeMinute() {
        return prefs.getInteger(PREF_MINUTE, 0);
    }

    public static int getTimeSecond() {
        return prefs.getInteger(PREF_SECOND, 0);
    }

    public static void setTimeMinute(int minute) {
        prefs.putInteger(PREF_MINUTE, minute);
        prefs.flush();
    }

    public static void setTimeSecond(int second) {
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

    public static void setColorUI(int indexColor) {
        prefs.putInteger(PREF_COLOR_INDEX, indexColor);
        prefs.flush();
    }

    public static int getColorUI() {
        return prefs.getInteger(PREF_COLOR_INDEX, 0);
    }

}
