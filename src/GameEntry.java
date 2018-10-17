import java.io.Serializable;
import java.security.InvalidParameterException;

public class GameEntry  implements Serializable {
    private String name;
    private int score;

    public GameEntry(String name, int score){
        if(name == null || name.isEmpty() || name.replaceAll("\\s","").isEmpty())
            throw new InvalidParameterException("invalid_name");
        if(score < 0) throw new InvalidParameterException("invalid_score");

        this.name = name;
        this.score = score;
    }
    public String getName(){
        return this.name;
    }
    public int getScore(){
        return this.score;
    }
    /** Retorna uma string representando o objeto
     * Formato: (<name>, <score>)
     */
    public String toString(){
        return "("+this.name+", "+this.score+")";
    }
}