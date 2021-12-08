package com.mycompany.ui;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.screens.StatisticsScreen;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mycompany.mygame.Setting;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mycompany.mygame.MyGdxGame;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mycompany.mygame.AppPreference;
import com.badlogic.gdx.math.GridPoint2;
import com.mycompany.unils.TimeUtils;

public class StatisticsUi extends Stage{
    
    public StatisticsUi(Viewport port, ResourceManager manager, final StatisticsScreen statistic){
        super(port);
        
        Table table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(manager.getTextureRegion(ResourceManager.background)));
        addActor(table);
        
        Label title = new Label("Статистика", manager.getSkin(), ResourceManager.label_style_big);
        
        Label allStarLabel = new Label("Получено звезд: "+AppPreference.getAllStars(), manager.getSkin(), ResourceManager.label_style_normal);
        
        GridPoint2 allTime = TimeUtils.getTime(AppPreference.getAllTime());
        Label allTimeLabel = new Label("Общее время игры: "+allTime.x+":"+allTime.y, manager.getSkin(), ResourceManager.label_style_normal);
        
        Label allErrorLabel = new Label("Сделано ошибок: ", manager.getSkin(), ResourceManager.label_style_normal);
        
        TextButton menu = new TextButton(Setting.name_menu_button, manager.getSkin(), ResourceManager.button_style);
        
        table.add(title).row();
        table.add(allStarLabel).padTop(20).row();
        table.add(allTimeLabel).padTop(10).row();
        table.add(allErrorLabel).padTop(10).row();
        table.add(menu).padTop(10);
        
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                statistic.dispose();
                statistic.getGame().setStateScreen(MyGdxGame.State.MENU);
            }
        });
    }
    
}
