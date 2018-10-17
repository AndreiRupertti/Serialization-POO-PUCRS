import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import sun.misc.IOUtils;


public class ScoresSerializacao {
    private final String CSV_SEPARATOR = "; ";

    public void writeBinary(String path, ScoreBoard scoreBoard)
    {
        Path binaryPath = Paths.get(path);

        try(ObjectOutputStream outStream = new ObjectOutputStream(Files.newOutputStream(binaryPath))){
            outStream.writeObject(scoreBoard);
            System.out.println("Arquivo escrito com sucesso.");
        } catch (IOException e) {
            System.out.print("Error: couldn't create input stream ");
            e.printStackTrace();
        }
    }

    public void readBinary(String path){
        Path binaryPath = Paths.get(path);

        try (ObjectInputStream inStream = new ObjectInputStream(Files.newInputStream(binaryPath))) {
            ScoreBoard scoreBoard = (ScoreBoard) inStream.readObject();

            System.out.print("SCORE BOARD: "+scoreBoard.toString());

        } catch (IOException | ClassNotFoundException e) {
            System.out.print("Error: couldn't read file ");
            e.printStackTrace();
        }
    }

    public void writeCsv(String path, ScoreBoard scoreBoard)
    {
        try(BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))){
            List<GameEntry> scores = scoreBoard.getScores();
            for(GameEntry ge : scores){
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(ge.toString());
                oneLine.append(CSV_SEPARATOR);

                buffer.write(oneLine.toString());
                buffer.newLine();
            }

            buffer.flush();
            buffer.close();

            System.out.println("Arquivo escrito com sucesso.");
        } catch (IOException e) {
            System.out.print("Error: couldn't create input stream ");
            e.printStackTrace();
        }
    }

    public void readCsv(String path){
        String line;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            int position = 1;

            while ((line = buffer.readLine()) != null) {

                String[] score = line.split(CSV_SEPARATOR);

                System.out.println(position+". Score: " + score[0]);

                position++;
            }
        } catch (IOException e) {
            System.out.print("Error: couldn't read file ");
            e.printStackTrace();
        }
    }

    public void writeJSON(String path, ScoreBoard scoreBoard){
        JSONObject jsonScore = new JSONObject();
        jsonScore.put("scoreBoard", scoreBoard.getScores());

        try(FileWriter file = new FileWriter(path)){
            file.write(jsonScore.toString(2));
            file.flush();
        } catch (IOException e) {
            System.out.print("Error: couldn't write file ");
            e.printStackTrace();
        }

    }

    public void readJSON(String path) {
        String line;
        StringBuffer stringBuffer = new StringBuffer();

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {

            while ((line = buffer.readLine()) != null) {
                stringBuffer.append(line);
            }

            JSONObject objt = new JSONObject(stringBuffer.toString());
            JSONArray jsonBoard = (JSONArray) objt.get("scoreBoard");

            List<GameEntry> scores = new ArrayList<>();

            for (int i = 0; i < jsonBoard.length(); i++)
            {
                String name = jsonBoard.getJSONObject(i).getString("name");
                int score = jsonBoard.getJSONObject(i).getInt("score");

                scores.add(new GameEntry(name, score));
            }
            ScoreBoard scoreBoard = new ScoreBoard(scores);
            System.out.println(">>>>>>>>."+scoreBoard.toString());
        } catch (IOException e) {
            System.out.print("Error: couldn't read file ");
            e.printStackTrace();
        }
    }
}
