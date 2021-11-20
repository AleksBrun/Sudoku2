package com.mycompany.mygame;
import com.badlogic.gdx.Gdx;

public class Setting
{
	public static String rus_white_big ="rus-white-big";
	public static String font_white_big = "font-white-big";
	public static float width_Ui = 420;
	public static float pad_cell = 10;
	public static float pad_mark= 2;
	
	public static float getHeight_Ui(){
		return  width_Ui /((float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight());
	}

	public static float size_icon = width_Ui /15;

	public static float getSizeGrid(){
		return Gdx.graphics.getWidth()-20;
	}

	public static float getPositionGrid_X(){
		return (Gdx.graphics.getWidth()-getSizeGrid())/2;
	}
	 public static float getPositionGrid_Y(){
		return (Gdx.graphics.getHeight()-getSizeGrid())/2;
	 }

	 public static float getWidthKeys(){
		return getSizeGrid();
	 }

	 public static float getHeightKeys(){
		return getWidthKeys()/10;
	 }
}
