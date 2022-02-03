package Practica0;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EstadisticaTest {
    private static Estadistica estadistica;

    double[] conjuntoNulo = null;
    double[] conjuntoVacio = {};
    double[] conjuntoUnNumero = {5};
    double[] conjuntoVarios = {1,2,3,4,5};
    double[] conjuntoRepetidos = {4,4,4,4};

    @BeforeAll
    static void initAll(){
        estadistica = new Estadistica();
    }

    @Test
    @DisplayName("Test media conjunto nulo")
    void mediaNull(){
        assertThrows(NullPointerException.class, () -> estadistica.media(conjuntoNulo));
    }
    @Test
    @DisplayName("Test media conjunto vacio")
    void mediaVacia(){
        assertThrows(ArithmeticException.class, () -> estadistica.media(conjuntoVacio));
    }
    @Test
    @DisplayName("Test media un elemento")
    void mediaUnElemento(){
        assertEquals(5,estadistica.media(conjuntoUnNumero));
    }
    @Test
    @DisplayName("Test media con varios elemntos")
    void mediaVarios(){
        assertEquals(3,estadistica.media(conjuntoVarios));
    }
    @Test
    @DisplayName("Test media con elementos repetidos")
    void mediaRepetidos(){
        assertEquals(4,estadistica.media(conjuntoRepetidos));
    }

    @Test
    @DisplayName("Test varianza conjunto nulo")
    void varianzaNull(){
        assertThrows(NullPointerException.class, () -> estadistica.varianza(conjuntoNulo));
    }
    @Test
    @DisplayName("Test varianza conjunto vacio")
    void varianzaVacio(){
        assertThrows(ArithmeticException.class, () -> estadistica.media(conjuntoVacio));
    }
    @Test
    @DisplayName("Test varianza un elemento")
    void varianzaUnElemento(){
        assertEquals(0,estadistica.varianza(conjuntoUnNumero));
    }
    @Test
    @DisplayName("Test varianza varios elemtos")
    void varianzaVarios(){
        assertEquals(2,estadistica.varianza(conjuntoVarios));
    }
    @Test
    @DisplayName("Teste varianza elemento repetidos")
    void varianzaRepetidos(){
        assertEquals(0, estadistica.varianza(conjuntoRepetidos));
    }

    @Test
    @DisplayName("Desviacion nula")
    void desviacionEstandarNull(){
        assertThrows(NullPointerException.class, () -> estadistica.desviacionEstandar(conjuntoNulo));
    }
    @Test
    @DisplayName("Desviacion vacia")
    void desviacionEstandarVacio(){
        assertThrows(ArithmeticException.class, () -> estadistica.desviacionEstandar(conjuntoVacio));
    }
    @Test
    @DisplayName("Desviacion un elemento")
    void desviacionEstandarUnElemnto(){
        assertEquals(0,estadistica.desviacionEstandar(conjuntoUnNumero));

    }
    @Test
    @DisplayName("Desviacion varios elementos")
    void desviacionEstandarVarios(){
        assertEquals(Math.sqrt(2),estadistica.desviacionEstandar(conjuntoVarios));

    }

    @Test
    @DisplayName("Desviacion elementos repetidos")
    void desviacionEstandarRepetidos(){
        assertEquals(0, estadistica.desviacionEstandar(conjuntoRepetidos));
    }
}