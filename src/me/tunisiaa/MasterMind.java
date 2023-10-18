package me.tunisiaa;

import java.util.Random;
import java.util.Scanner;

public class MasterMind {
    private final int digits; // numero di cifre da indovinare
    private final int tries; // numero di tentativi
    private final int numberToGuess; // numero da indovinare
    public int currentTurn = 0; // numero del turno attuale (viene incrementato a 1 appena inizia la partita)

    private boolean[] correctNumbers;

    public MasterMind(int digits, int tries){ // costruttore con numero di cifre e tentativi come parametri
        this.digits = digits;
        this.tries = tries;
        Random r = new Random();
        numberToGuess = (int) Math.pow(10, digits - 1) + r.nextInt(9*(int) Math.pow(10, digits - 1)); // sceglie un numero casuale
        correctNumbers = new boolean[digits];
    }

    private int numbersCorrect(int guess){ // conta i numeri nella posizione corretta
        int n = 0;
//        int tempGuess = guess;
//        int tempNumberToGuess = this.numberToGuess;
        for(int i = 0; i < this.digits; i++){
            if(getDigitAtIndex(guess, i) == getDigitAtIndex(this.numberToGuess, i)){
                this.correctNumbers[i] = true;
                n++;
            }else{
                this.correctNumbers[i] = false;
            }
            /* versione vecchia
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

    private int numbersPresent(int guess){ // conta le cifre che sono in posizioni sbagliate
        int n = 0;
        for(int i = 0; i < this.digits; i++){ // itera tra tutte le cifre del primo numero
            if(this.correctNumbers[i]){ // evita che si contino più volte le cifre in posizione corretta
                continue;
            }
            for(int j = 0; j < this.digits; j++){ // confronta tutte le cifre
                int digitA = getDigitAtIndex(this.numberToGuess, i);
                int digitB = getDigitAtIndex(guess, j);
                if(digitA == digitB){
                    n++;
                    break;
                }
            }
        }
        return n;
    }

    private int getDigitAtIndex(int number, int index){ //dato un numero e un'indice restituisce la cifra a quell'indice, gli indici sono al contrario, se il numero è 1234 allora indice 0 è il 4, 1 è il 3 e così via
        return (number / (int) Math.pow(10, index)) % 10;
    }

    public void startGame(){
        do{
            this.currentTurn++; //incremento del numero del turno attuale
            int guess; // raccoglie la scelta dell'utente
            Scanner sc = new Scanner(System.in);
            do{
                System.out.printf("\nTentativo %s:\nScrivi un numero di %s cifre :", this.currentTurn, this.digits);
                guess = sc.nextInt(); //input
            }while(guess < (int) Math.pow(10, this.digits - 1) || guess >= (int) Math.pow(10, digits)); // evita che l'utente possa dare un numero sbagliato e che il programma dia errori inaspettati

            System.out.printf("%s delle %s cifre date sono nella posizione corretta e delle rimanenti %s sono presenti ma in posizioni sbagliate.\n", numbersCorrect(guess), this.digits, numbersPresent(guess));
            if(numbersCorrect(guess) == 4){ //controlla se si ha vinto
                System.out.printf("Complimenti! Il numero era %s, ti sono serviti %s turni per indovinare.\n", this.numberToGuess, this.currentTurn);
                return; // termina l'esecuzione del metodo
            }
        }while(this.currentTurn < this.tries); // continua finchè il turno attuale è minore (o uguale perchè è un do-while) del numero massimo di turni
        System.out.printf("Purtroppo i %s tentativi non ti sono bastati per giungere a %s.\n", this.currentTurn, this.numberToGuess); // Se non si è trovato il numero giusto si scrive quale era
    }

    public int getNumberToGuess(){ //così si può vedere la variabile numberToGuess anche se è privata
        return this.numberToGuess;
    }
}
