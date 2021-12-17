package com.mycompany.models;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CommonGroup extends Group {

    private Table table;

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
