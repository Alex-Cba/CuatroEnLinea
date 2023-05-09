package Pruebas;

import Tablero_Virtual.virtual_IG;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuatroEnLineaTest
{
    @Disabled("Verifica si el tablero se llena correctamente")
    @Test
    public void CheckTablero_Lleno()
    {
        virtual_IG virtual = virtual_IG.getInstance();
        int contador=0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                virtual.AgregarFicha("Amarillo", i, j);
                contador++;
            }
        }

        assertEquals(42, contador, "El tablero no se lleno correctamente");
        assertTrue(virtual.Tablerolleno());
    }

    @Disabled("Prueba Agregar piezas en la matriz y fuera de esta")
    @Test
    public void CheckAgregarFicha()
    {
        virtual_IG virtual = virtual_IG.getInstance();

        assertTrue(virtual.AgregarFicha("Rojo", 0, 0));
        assertFalse(virtual.AgregarFicha("Prueba", 1,1));
        assertFalse(virtual.AgregarFicha("Rojo", -1,-1));
        assertTrue(virtual.AgregarFicha("Amarillo", 5, 6));
        assertFalse(virtual.AgregarFicha("Amarillo", 99,99));
    }

}
