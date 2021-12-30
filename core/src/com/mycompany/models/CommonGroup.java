package com.mycompany.models;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CommonGroup extends Group {

    private final Table table;

    public CommonGroup(float _width, float _height) {
        setSize(_width, _height);
        table = new Table();
        table.setFillParent(true);
        addActor(table);

    }

    public Table getTable() {
        return this.table;
    }
}
