package com.example;

public class Carta {

    private int numero;
    private Naipe naipe;
    private final String[] NAIPES = {"", "Ace", "2", "3", "5", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    //Construtores
    public Carta(int numero, Naipe naipe) {
        this.numero = numero;
        this.naipe = naipe;
    }

    //
    public String imagePath(){
        return "classic-cards/" + NAIPES[numero] + naipe + ".png";
    }

    //Getters
    public int getNumero() {
        return numero;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    
}
