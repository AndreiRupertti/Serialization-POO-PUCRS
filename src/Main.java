public class Main {
    public static void main(String[] args){
        GameEntry[] entries = new GameEntry[]{
                new GameEntry("play 1", 1),
                new GameEntry("play 2", 3),
                new GameEntry("play 3", 5),
                new GameEntry("play 4", 10),
                new GameEntry("play 5", 15),
                new GameEntry("play 6", 20),
                new GameEntry("play 7", 25),
                new GameEntry("play 8", 30),
                new GameEntry("play 9", 40),
                new GameEntry("play 10", 80)
        };

        ScoreBoard scoreBoard = new ScoreBoard();
        for(GameEntry ge : entries){
            scoreBoard.add(ge);
        }

        scoreBoard.add(new GameEntry("New best Player", 100));


        System.out.println("\nSCORE BOARD: "+scoreBoard);


        ScoresSerializacao ss = new ScoresSerializacao();

        ss.writeBinary("binaryFile.bin", scoreBoard);
        ss.readBinary("binaryFile.bin");

        ss.write("default.dat", scoreBoard);
        ss.read("default.dat");

        ss.writeCsv("csvFile.csv", scoreBoard);
        ss.readCsv("csvFile.csv");

        ss.writeJSON("jsonFile.json", scoreBoard);
        ss.readJSON("jsonFile.json");

    }
}
