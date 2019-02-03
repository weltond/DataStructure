public interface BeatModelInterface {
    void initialize();
    void on();
    void off();

    void setBPM(int bpm);

    int getBPM();

    void registerObserver(BeatObserver observer);

    void removeObserver(BeatObserver observer);

    void registerObserver(BPMObserver observer);

    void removeObserver(BPMObserver observer);
}
