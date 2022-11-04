package testes.testeImpl;

import arvores.ArvoreAvl;
import testes.TestaArvore;
import testes.testesAux.CriaVetor;

public class TesteArvoreAvl implements TestaArvore {
    
    @Override
    public void piorCaso() {

        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int vetor[] = CriaVetor.criaVetorCrescente(tamanhoVetor);
            ArvoreAvl<Integer> arvore = new ArvoreAvl<>();
            int quantidadeOperacoes;

            for (int valor : vetor) {
                arvore.adicionar(valor);
            }

            quantidadeOperacoes = arvore.getQuantidadeOperacoes();

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + quantidadeOperacoes + " para incluir e balancear.");
        }
    }

    @Override
    public void casoMedio() {
        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int soma = 0;
            
            for (int numeroExecucoes = 0; numeroExecucoes < 10; numeroExecucoes++) {
                int vetor[] = CriaVetor.criaVetorAleatorio(tamanhoVetor);
                ArvoreAvl<Integer> arvore = new ArvoreAvl<>();
    
                for (int valor : vetor) {
                    arvore.adicionar(valor);
                }
    
                soma += arvore.getQuantidadeOperacoes();
            }

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + (soma / 10) + " para incluir e balancear.");
        }
    }

}
