public interface Scores {
    /** Retorna uma string representando o objeto
     * Formato: [(<name>, <score>), (<name>, <score>) …]
     * Exemplo: [(John, 10), (Carol, 5), (Dan, 3)]
     */
    String toString();
    /** Adiciona um novo score se ele for grande o suficiente */
    boolean add(GameEntry e);
    /** Retorna o score na posição i */
    GameEntry get(int i);
    /** Retorna a capacidade de coleção */
    int getCapacity();
/** Retorna o número de scores armazenados */
    int getNumScores();
}