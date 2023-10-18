package me.tunisiaa;

import java.util.Random;
import java.util.Scanner;

public class MasterMind {
    private final int digits;
    private final int tries;
    private final int numberToGuess;
    public int currentTurn = 1;

    private boolean[] correctNumbers;

    public MasterMind(int digits, int tries){
        this.digits = digits;
        this.tries = tries;
        Random r = new Random();
        numberToGuess = (int) Math.pow(10, digits - 1) + r.nextInt(9*(int) Math.pow(10, digits - 1));
        correctNumbers = new boolean[digits];
    }

    private int numbersCorrect(int guess){
        int n = 0;
        int tempGuess = guess;
        int tempNumberToGuess = this.numberToGuess;
        for(int i = 0; i < this.digits; i++){
            if(getDigitAtIndex(guess, i) == getDigitAtIndex(this.numberToGuess, i)){
                n++;
            }
            /*
            int digitA = tempGuess % 10;
            int digitB = tempNumberToGuess % 10;
            if(digitA == digitB){
                this.correctNumbers[i] = true;
                n++;
            }else{
                this.correctNumbers[i] = false;
            }
            tempGuess /= 10;
            tempNumberToGuess /= 10;*/
        }
        return n;
    }

    private int numbersPresent(int guess){
        int n = 0;
        for(int i = 0; i < this.digits; i++){
            if(this.correctNumbers[i]){
                continue;
            }
            for(int j = 0; j < this.digits; j++){
                int digitA = getDigitAtIndex(guess, j);
                int digitB = getDigitAtIndex(this.numberToGuess, j);
                if(digitA == digitB){
                    n++;
                    break;
                }
            }
        }
        return n;
    }

    private int getDigitAtIndex(int number, int index){
        return (number / (int) Math.pow(10, index)) % 10;
    }

    public void startGame(){
        for(; this.currentTurn <= this.tries; this.currentTurn++){
            int guess;
            Scanner sc = new Scanner(System.in);
            do{
                System.out.printf("\nTentativo %s:\nScrivi un numero di %s cifre :", this.currentTurn, this.digits);
                guess = sc.nextInt();
            }while(guess < (int) Math.pow(10, this.digits - 1) || guess >= (int) Math.pow(10, digits));

            System.out.printf("%s delle %s cifre date sono nella posizione corretta e delle rimanenti %s sono presenti ma in posizioni sbagliate.\n", numbersCorrect(guess), this.digits, numbersPresent(guess));
            if(numbersCorrect(guess) == 4){
                System.out.printf("Complimenti! Il numero era %s, ti sono serviti %s turni per indovinare.\n", this.numberToGuess, this.currentTurn);
                return;
            }
        }
        System.out.printf("Purtroppo i %s tentativi non ti sono bastati per giungere a %s.\n", this.currentTurn, this.numberToGuess);
    }

    public int getNumberToGuess(){
        return this.numberToGuess;
    }
}
