package Practica0;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AritmeticaTest {
    @BeforeAll
    static void initAll(){

    }

    @BeforeEach
    void initTest(){

    }

    @AfterAll
    static void closeAll(){

    }

    @AfterEach
    void closeTest(){

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Prueba Suma")
    void suma() {
        Aritmetica aobj = new Aritmetica();
        assertEquals(5, aobj.suma(4, 1));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Prueba Resta")
    void resta() {
        Aritmetica aobj = new Aritmetica();
        assertEquals(3, aobj.resta(4, 1));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Prueba Mult")
    void multiplicacion() {
        Aritmetica aobj = new Aritmetica();
        assertEquals(6.0, aobj.multiplicacion(2, 3));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Prueba division")
    void division() {
        Aritmetica aobj = new Aritmetica();
        assertEquals(5.0, aobj.division(10, 2));
    }
}