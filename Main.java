package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FootballScoreTracker {
    private List<ScoreObserver> observers = new ArrayList<>();
    private String score;

    public void addObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ScoreObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (ScoreObserver observer : observers) {
            observer.update(score);
        }
    }

    public void setScore(String score) {
        this.score = score;
        notifyObservers();
    }
}

interface ScoreObserver {
    void update(String score);
}

class LiveResultDisplay implements ScoreObserver {
    @Override
    public void update(String score) {
        System.out.println("live result: " + score);
    }
}

public class Main {
    public static void main(String[] args) {
        FootballScoreTracker scoreTracker = new FootballScoreTracker();

        ScoreObserver observer1 = new LiveResultDisplay();
        ScoreObserver observer2 = new LiveResultDisplay();

        scoreTracker.addObserver(observer1);
        scoreTracker.addObserver(observer2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Score: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                break;
            }

            scoreTracker.setScore(input);
        }

        scanner.close();
    }
}
