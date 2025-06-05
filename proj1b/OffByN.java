public class OffByN implements CharacterComparator{
    int N;
    public OffByN(int NPassedIn) {
        N = NPassedIn;
    }
    public boolean equalChars(char x, char y) {
        return x - y == N || x - y == -N;
    }
}
