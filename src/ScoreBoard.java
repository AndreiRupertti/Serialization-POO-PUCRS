import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements Scores, Serializable {
    private List<GameEntry> scores;
    private final int MAX_SIZE = 10;

    public ScoreBoard(){
        this.scores = new ArrayList<>();
    }

    public ScoreBoard(List<GameEntry> scores){
        this.scores = scores;
    }

    public boolean add(GameEntry gameEntry) {
        if(gameEntry == null) throw new InvalidParameterException("invalid_game_entry");
        if(this.scores.size() == 0) {
            this.scores.add(gameEntry);
            return true;
        }
        if(this.scores.size() > MAX_SIZE && getLastScore().getScore() > gameEntry.getScore()) return false;


        int index = positionToAdd(gameEntry);
        int currentSize = this.scores.size();

        if(index >= MAX_SIZE | index < 0) return false;
        if(currentSize >= MAX_SIZE)
            removeScore(getLastScore());

        if(index == currentSize) {
            this.scores.add(gameEntry);
        }
        else {
            this.scores.add(index, gameEntry);
        }

        return true;
    }

    public GameEntry get(int i){
        return scores.get(i);
    }

    public GameEntry getLastScore(){
        return this.scores.size() > 0 ? this.scores.get(this.scores.size()-1) : null;
    }

    public void removeScore(GameEntry ge) {
        this.scores.remove(ge);
    }

    public int positionToAdd(GameEntry gameEntry) {
        int cont=0;
        for(GameEntry ge : this.scores) {
            if(ge.getScore() < gameEntry.getScore()) {
                return cont;
            }
            cont++;
        }

        return cont;
    }

    public List<GameEntry> getScores(){
        return this.scores;
    }

    public int getCapacity(){
        return this.MAX_SIZE;
    }

    public int getNumScores(){
        return scores.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int cont = 1;
        for(GameEntry gameEntry : this.scores){
            sb.append(gameEntry.toString());
            if(cont!=this.scores.size())
                sb.append(", ");
            cont++;
        }
        sb.append("]");

        return sb.toString();
    }

}
