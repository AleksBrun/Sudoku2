package com.mycompany.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreference {
    private static final String BONUS = "bonus";
    private static final String COLOR_TOPIC = "color.topic";
    private static final String STARS_GAME = "stars.game";
    private static final String ALL_ERROR = "all.error";
    private static final String ALL_MINUTE = "all.minute";
    private static final String ERROR_GAME = "error.game";
    private static final String ALL_STARS = "all.stars";
    private static final String MUSIC_VOLUME = "volume";
    private static final String MUSIC_ENABLED = "music.enabled";
    private static final String PREFS_NAME = "b2dtut";
    private static final String COLOR_INDEX = "color.ui";
    private static final String COLOR_FONT = "color.font";
    private static final String SUDOKU = "sudoku";
    private static final String CONTINUATION_ENABLED = "continuation.enabled";
    private static final String MINUTE = "time_minute";
    private static final String SECOND = "time_second";
    private static final String MISSING_DIGITS = "missing_digits";
    private static final String DIFFICULTY_LEVEL = "difficulty_level";
    private static final Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

    public static void setColorTopic(int colorTopic){
        prefs.putInteger(COLOR_TOPIC, colorTopic);
        prefs.flush();
    }
    public static int getColorTopic(){
        return prefs.getInteger(COLOR_TOPIC, 0);
    }
    // записать индекс цвета шрифта
    public static void setColorFont(int index_color){
        prefs.putInteger(COLOR_FONT, index_color);
        prefs.flush();
    }
    // Получить индекс цвета шрифта
    public static int getColorFont(){
        return prefs.getInteger(COLOR_FONT, Setting.start_color_font);
    }
    // Сохранить общее время игры
    public static void setAllTime(int minute){
        prefs.putInteger(ALL_MINUTE, minute);
        prefs.flush();
    }
    // получить общее время игры
    public static int getAllTime(){
        return prefs.getInteger(ALL_MINUTE, 0);
    }
    // Сохранить общее колличество ошибок
    public static void setAllError(int errors){
        prefs.putInteger(ALL_ERROR, errors);
        prefs.flush();
    }
    // Получить общее колличество ошибок
    public static int getAllError(){
        return prefs.getInteger(ALL_ERROR, 0);
    }

    // сочранить общее число звезд(ОЧКОВ)
    public static void setAllStars(int stars){
        prefs.putInteger(ALL_STARS, stars);
        prefs.flush();
    }
    // Получить общее число звезд
    public static int getAllStars(){
        return prefs.getInteger(ALL_STARS, 0);
    }
    // Сохранить ровень сложности
    public static void setDifficultyLevel(int level){
        prefs.putInteger(DIFFICULTY_LEVEL, level);
        prefs.flush();
    }
    // Получить уровень сложности
    public static int getDifficultyLevel(){
        return prefs.getInteger(DIFFICULTY_LEVEL);
    }
    // Записать колличество пустых ячеек
    public static void setMissingDigits(int missing_digits) {
        prefs.putInteger(MISSING_DIGITS, missing_digits);
        prefs.flush();
    }
    // Проверка возможности продолжить игру
    public static boolean isContinuationEnabled() {
        return prefs.getBoolean(CONTINUATION_ENABLED, false);
    }
    // Записаить сосояие продолжить игпу
    public static void setContinuationEnabled(boolean continuationEnabled) {
        prefs.putBoolean(CONTINUATION_ENABLED, continuationEnabled);
        prefs.flush();
    }

    public static boolean isMusicEnabled() {
        return prefs.getBoolean(MUSIC_ENABLED, true);
    }

    public static void setMusicEnabled(boolean musicEnabled) {
        prefs.putBoolean(MUSIC_ENABLED, musicEnabled);
        prefs.flush();
    }

    public static float getMusicVolume() {
        return prefs.getFloat(MUSIC_VOLUME, 0.5f);
    }

    public static void setMusicVolume(float volume) {
        prefs.putFloat(MUSIC_VOLUME, volume);
        prefs.flush();
    }

    public static void setColorUI(int indexColor) {
        prefs.putInteger(COLOR_INDEX, indexColor);
        prefs.flush();
    }

    public static int getColorUI() {
        return prefs.getInteger(COLOR_INDEX, Setting.start_color);
    }
}
