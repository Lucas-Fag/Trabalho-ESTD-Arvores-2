import testes.testeImpl.TesteArvoreAvl;
import testes.testeImpl.TesteArvoreB;
import testes.testeImpl.TesteArvoreRubroNegra;

public class App {
    public static void main(String[] args) throws Exception {
        TesteArvoreAvl testeAvl = new TesteArvoreAvl();
        TesteArvoreRubroNegra testeRubroNegra = new TesteArvoreRubroNegra();
        TesteArvoreB testeArvoreB = new TesteArvoreB();
        
        // testeAvl.piorCaso();
        // testeAvl.casoMedio();

        // testeRubroNegra.piorCaso();
        // testeRubroNegra.casoMedio();

        // testeArvoreB.piorCaso(5);
        // testeArvoreB.casoMedio(5);
        
        // testeArvoreB.piorCaso(10);
        //testeArvoreB.casoMedio(10);
        
        // testeArvoreB.piorCaso(1);
        // testeArvoreB.casoMedio(1);
    }
}
