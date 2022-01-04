package com.mycompany.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Setting {

    public static float width_main_ui = 1080;
	public static float pad_cell = 10;
	public static float pad_mark= 2;
	public static final int start_color = 5;
	public static final int start_color_font = 1;

	public static final String label_lvl = "Уровень сложности ";
	public static final String label_time_game = "Время игры     ";
	public static final String label_error = "Ошибки ";
	public static final String label_bonus = "Бонусы ";
	public static final String name_continuation_button = "    Продолжить  ";
	public static final String name_play_button = "   Новая Игра ";
	public static final String name_menu_button = "   Меню ";
    public static final String name_statistics_button = "    Статистика  ";
	public static final String name_exit_button = "   Выход ";
	public static final String name_setting_button = "    Настройка  ";
	public static final String name_love_button = "   Любимой ";
    public static final String name_reset_button = "  Сброс ";
	public static final String name_trophy_button = "   Награда   ";
	public static final String name_colorUi_button ="   Цвет ";
	public static final String name_colorFont_button = "    Цвет шрифта ";
    
    public static final String level_0 = "   Очень легко ";
	public static final String level_1 = "   Легко ";
	public static final String level_2 = "   Нормально ";
	public static final String level_3 = "   Трудно ";
    public static final String level_4 = "   Очень трудно ";
	public static final String level_random = " Создать ";
	public static final String load_sudoku = "   Загрузить  ";
    
	public static float pad_ui_menu = 5;
	public static float pad_ui_main = 5;
	public static float pad_ui_menu_bottom = getHeight_Ui(420) / 12;

	public static float getHeight_Ui(float width) {
		return  width / ((float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight());
	}
    
    public static float getWidth(float height){
        return  height*((float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight());
    }

	public static float size_icon = width_main_ui / 15;

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

	public static Color getColorTopic(int indexColor){
		switch (indexColor){
			case 0: return Color.BLACK;
			case 1: return Color.BLUE;
			case 2: return Color.RED;
			case 3: return Color.GREEN;
			default: return Color.WHITE;
		}
	}
}
