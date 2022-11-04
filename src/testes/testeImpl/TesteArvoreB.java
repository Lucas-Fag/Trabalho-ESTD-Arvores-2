package testes.testeImpl;

import arvores.ArvoreB;
import testes.TestaArvoreB;
import testes.testesAux.CriaVetor;

public class TesteArvoreB implements TestaArvoreB {

    @Override
    public void piorCaso(int ordem) {
        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int vetor[] = CriaVetor.criaVetorCrescente(tamanhoVetor);
            ArvoreB<Integer> arvore = new ArvoreB<>(ordem);
            int quantidadeOperacoes;

            for (int valor : vetor) {
                arvore.adicionaChave(valor);
            }

            quantidadeOperacoes = arvore.getQuantidadeOperacoes();

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + quantidadeOperacoes + " para incluir e balancear.");
        }
        
    }

    @Override
    public void casoMedio(int ordem) {
        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int soma = 0;

            for (int numeroExecucoes = 1; numeroExecucoes <= 10; numeroExecucoes++) {
                int vetor[] = CriaVetor.criaVetorAleatorio(tamanhoVetor);
                ArvoreB<Integer> arvore = new ArvoreB<>(ordem);
                
                for (int valor : vetor) {
                    arvore.adicionaChave(valor);
                }
                
                soma += arvore.getQuantidadeOperacoes();
            }

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + (soma / 10) + " para incluir e balancear.");
        } 
    }
    
}
