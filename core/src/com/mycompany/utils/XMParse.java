package com.mycompany.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.mycompany.mygame.Parameter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class XMParse {

    private static final String sudoku = "Sudoku";
    private static final String appFolder = "MyApp/";
    private static final String nameFile = "parameter.xml";

    public static Array<Parameter>  load(){
        Array<Parameter> parameters = new Array<Parameter>();
        try {
            XmlReader xmlReader = new XmlReader();
            FileHandle fileHandler = Gdx.files.external(appFolder+nameFile);
            XmlReader.Element root = xmlReader.parse(fileHandler);

            for (int index = 0; index < root.getChildCount(); index++){
                XmlReader.Element element = root.getChild(index);
                Parameter parameter = new Parameter();
                parameter.index = element.getInt("index");
                parameter.sudokuFull = element.get("full");
                parameter.sudokuGame = element.get("game");
                parameter.sudokuSave = element.get("save");
                parameter.difficulty_level = element.getInt("level");
                parameter.data = element.get("data");
                parameter.time = element.getInt("time");
                parameter.bonus = element.getInt("bonus");
                parameter.max_bonus = element.getInt("max_bonus");
                parameter.error = element.getInt("error");
                parameter.max_error = element.getInt("max_error");
                parameter.progress = element.getInt("progress");
                parameter.start_progress = element.getInt("start_progress");
                parameter.coin = element.getInt("coin");
                parameters.add(parameter);
            }

        } catch (Exception exception){
            System.out.println("No Xml File!!!");
        }
        return parameters;
    }

    public static void save(Array<Parameter> parameters) {
        BufferedWriter out = null;
        try {

            FileHandle folderHandle = Gdx.files.external(appFolder);

            if(!folderHandle.exists()){
                folderHandle.mkdirs();
            }
            String resultsXml = appFolder + nameFile;

            out = new BufferedWriter(new OutputStreamWriter(Gdx.files.external(
                    resultsXml).write(false)));

            XmlWriter xml = new XmlWriter(out);
            int index = 0;
            xml.element(sudoku).attribute("date", new java.util.Date());
            for (Parameter parameter : parameters) {
                saveTaskResult(xml, parameter, index);
                index++;
            }
            xml.close();
        } catch (GdxRuntimeException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delete(){
        FileHandle folderHandle = Gdx.files.external(appFolder);
        if (folderHandle.exists()){
            folderHandle.emptyDirectory();
        }
    }

    private static void saveTaskResult(XmlWriter xml, Parameter parameter, int index)
            throws IOException {
        xml.element(sudoku+index).attribute("index", parameter.index)
                .attribute("full", parameter.sudokuFull)
                .attribute("game", parameter.sudokuGame)
                .attribute("save", parameter.sudokuSave)
                .attribute("level", parameter.difficulty_level)
                .attribute("data", parameter.data)
                .attribute("time", parameter.time)
                .attribute("bonus", parameter.bonus)
                .attribute("max_bonus", parameter.max_bonus)
                .attribute("error", parameter.error)
                .attribute("progress", parameter.progress)
                .attribute("start_progress", parameter.start_progress)
                .attribute("coin", parameter.coin)
                .attribute("max_error", parameter.max_error)
                .pop();
    }
}
