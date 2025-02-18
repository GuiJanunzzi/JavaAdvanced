package com.example;

public class Jogo {

    protected Monte monte = new Monte();
    protected Jogador jogador = new Jogador();
    protected Computador computador = new Computador();
    
    public Jogo() {
        monte.embaralhar();
    }

    public Carta distribuiCartaParaJogador(Jogador jogador) {
        if (jogador.parou()) 
            return null;
        Carta carta = monte.virar();
        jogador.receberCarta(carta);
        return carta;
    }

    public boolean acabou(){
        return alguemEstourou() || computador.parou() && jogador.parou();
    }

    public String resultado(){
        // Empatou
        // Você ganhou
        // Você Perdeu

        if (jogadorEstourou() && computadorEstourou() || jogador.getPontos() == computador.getPontos()) {
            return "Empatou";            
        }

        if (computadorEstourou() || !jogadorEstourou() && jogador.getPontos() > computador.getPontos()) {
            return "Você ganhou";
        }

        return "Você Perdeu";
    }
    
    private boolean jogadorEstourou(){
        return jogador.getPontos() > 21;
    }

    private boolean computadorEstourou(){
        return computador.getPontos() > 21;
    }

    private boolean alguemEstourou(){
        return jogadorEstourou() || computadorEstourou();
    }
}
