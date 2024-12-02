import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class SaveLoadManager {


    public static void saveGame(Connect4 game, String filename) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();


            Element rootElement = doc.createElement("Connect4Game");
            doc.appendChild(rootElement);


            Element currentPlayer = doc.createElement("CurrentPlayer");
            currentPlayer.appendChild(doc.createTextNode(String.valueOf(game.getCurrentPlayer())));
            rootElement.appendChild(currentPlayer);


            Element boardElement = doc.createElement("Board");
            for (int i = 0; i < Connect4.ROWS; i++) {
                Element row = doc.createElement("Row");
                for (int j = 0; j < Connect4.COLUMNS; j++) {
                    Element cell = doc.createElement("Cell");
                    cell.appendChild(doc.createTextNode(String.valueOf(game.getBoardCell(i, j))));
                    row.appendChild(cell);
                }
                boardElement.appendChild(row);
            }
            rootElement.appendChild(boardElement);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

            System.out.println("A játékállás sikeresen elmentve: " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadGame(Connect4 game, String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();


            String currentPlayer = doc.getElementsByTagName("CurrentPlayer").item(0).getTextContent();
            game.setCurrentPlayer(currentPlayer.charAt(0));


            for (int i = 0; i < Connect4.ROWS; i++) {
                for (int j = 0; j < Connect4.COLUMNS; j++) {
                    String cellValue = doc.getElementsByTagName("Row").item(i).getChildNodes().item(j).getTextContent();
                    game.setBoardCell(i, j, cellValue.charAt(0));
                }
            }

            System.out.println("A játékállás sikeresen betöltve: " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
