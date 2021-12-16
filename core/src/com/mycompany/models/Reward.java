package com.mycompany.models;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mycompany.mygame.ResourceManager;
import com.mycompany.mygame.Setting;

public class Reward extends Group {

    private final Image[] cells;
    private Label[] names;

    public Reward(float x, float y, float width, float height,  ResourceManager manager){
        setBounds(x, y, width, height);

        cells = new Image[9];
        for (int i = 0; i < 9; i++){
            Image cell = new Image(manager.getTextureRegionAtlas(ResourceManager.mark4));
            cell.setSize(getWidth()/3, getWidth()/3);
            addActor(cell);
            cells[i] = cell;
        }
        names = new Label[9];

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);

        names = new Label[9];
        for (int i = 0; i < 9; i++){
            names[i] = new Label("", manager.getSkin(), ResourceManager.label_style_big);
        }
        int index = 0;
        int index1 = 0;
        table.top();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                table.add(cells[index]).colspan(2).width(getWidth()/3).height(getWidth()/3).pad(10);
                index++;
            }
            table.row();
            for (int j = 0; j < 3; j++){
                table.add(names[index1]).right();
                table.add(getIconStar(manager)).width(Setting.size_icon/2).height(Setting.size_icon/2).left();
                index1++;
            }
            table.row();
        }
    }

    private Image getIconStar(ResourceManager manager){
        return new Image(manager.getTextureAtlas(ResourceManager.ICON_STAR));
    }

    public Image[] getCells() {
        return cells;
    }

    public Label[] getNames() {
        return names;
    }
}
