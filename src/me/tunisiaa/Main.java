package me.tunisiaa;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MasterMind mm = new MasterMind(4, 10);
        System.out.println(mm.getNumberToGuess());
        mm.startGame();
    }
}
