package kickerbreaker.controller;

/**
 * Created by karina on 18-10-2017.
 */

import kickerbreaker.model.Enemy;
import kickerbreaker.model.Level;
import kickerbreaker.model.Model;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLReader {
    File fileToOpen;
    public ArrayList<Level> addedLevels = new ArrayList<Level>();
    Enemy addedEnemy;
    Model model;

    public XMLReader(Model model) {
        this.model = model;
        fileToOpen = new File("src/kickerbreaker/levels.xml");
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean levelEndBoolean = false;
                boolean levelBoolean = false;
                boolean xBoolean = false;
                boolean yBoolean = false;
                boolean hpBoolean = false;


                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("level")) {
                        levelBoolean = true;
                    }

                    if (qName.equalsIgnoreCase("/level")) {
                        levelEndBoolean = true;
                    }

                    if (qName.equalsIgnoreCase("x")) {
                        xBoolean = true;
                    }

                    if (qName.equalsIgnoreCase("y")) {
                        yBoolean = true;
                    }
                    if (qName.equalsIgnoreCase("hp")) {
                        hpBoolean = true;
                    }
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    String x;
                    String y;
                    String hp;

                    if (levelBoolean) {
                        model.levels.add(new Level());
                        levelBoolean = false;
                    }

                    if (xBoolean) {
                        addedEnemy = new Enemy();
                        x = new String(ch, start, length);
                        addedEnemy.setX(Integer.parseInt(x));
                        xBoolean = false;
                    }

                    if (yBoolean) {
                        y = new String(ch, start, length);
                        addedEnemy.setY(Integer.parseInt(y));
                        yBoolean = false;
                    }

                    if (hpBoolean) {
                        hp = new String(ch, start, length);
                        addedEnemy.setHP(Integer.parseInt(hp));
                        hpBoolean = false;
                        model.levels.get(model.levels.size() - 1).enemyList.add(addedEnemy);
                    }
                }
            };
            saxParser.parse(fileToOpen, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
