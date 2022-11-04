package testes.testeImpl;

import arvores.ArvoreRubroNegra;
import testes.TestaArvore;
import testes.testesAux.CriaVetor;

public class TesteArvoreRubroNegra implements TestaArvore {
    
    @Override
    public void piorCaso() {
        
        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) { 
            int vetor[] = CriaVetor.criaVetorCrescente(tamanhoVetor);
            ArvoreRubroNegra<Integer> arvore = new ArvoreRubroNegra<>();
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

            for (int numeroExecucoes = 1; numeroExecucoes <= 10; numeroExecucoes++) {
                int vetor[] = CriaVetor.criaVetorAleatorio(tamanhoVetor);
                ArvoreRubroNegra<Integer> arvore = new ArvoreRubroNegra<>();
                
                for (int valor : vetor) {
                    arvore.adicionar(valor);
                }
                
                soma += arvore.getQuantidadeOperacoes();
            }

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + (soma / 10) + " para incluir e balancear.");
        }
    }
}
