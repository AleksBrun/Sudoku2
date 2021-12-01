package com.mycompany.mygame;
import com.badlogic.gdx.Gdx;

public class Setting {

    public static float width_main_ui = 720;
    public final static float width_menu_ui = 420;
	public static float pad_cell = 10;
	public static float pad_mark= 2;

	public static String name_continuation_button = "   Продолжить ";
	public static String name_play_button = "   Новая Игра ";
	public static String name_menu_button = "   Меню ";
	public static String name_exit_button = "   Выход ";
	public static String name_setting_button = "    Настройка  ";
	public static String name_love_button = "   Любимой ";
    public static String name_reset_button = "  Сброс ";
	public static final String name_colorUi_button ="   Цвет ";
	public static final String level_1 = "  Легко ";
	public static final String level_2 = "  Нормально ";
	public static final String level_3 = "  Трудно ";
	public static final String level_random = " Создать ";
	public static float pad_ui_menu = 5;
	public static float pad_ui_menu_bottom = getHeight_Ui(420) / 12;

	public static float getHeight_Ui(float width) {
		return  width / ((float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight());
	}

	public static float size_icon = width_main_ui / 10;

	public static float getSizeGrid() {
		return Gdx.graphics.getWidth() - 20;
	}

	public static float getPositionGrid_X() {
		return (Gdx.graphics.getWidth() - getSizeGrid()) / 2;
	}
    public static float getPositionGrid_Y() {
		return (Gdx.graphics.getHeight() - getSizeGrid()) / 2;
    }

    public static float getWidthKeys() {
		return getSizeGrid();
    }

    public static float getHeightKeys() {
		return getWidthKeys() / 10;
    }
}
