package Tablero_Virtual;

public class virtual_IG {
    private static virtual_IG Instance = null;
    private final String[][] Matriz = new String[6][7];

    public String[][] getMatriz() {
        return Matriz;
    }

    private virtual_IG()
    {
        //Vacio, no tiene nada la matriz
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                Matriz[i][j] = "";
            }
        }
    }

    //Singleton
    public static virtual_IG getInstance(){

        if(Instance == null){
            Instance = new virtual_IG();
        }

        return Instance;
    }

    public boolean AgregarFicha(String _color, int fila, int columna) {
        try
        {
            if (_color.equalsIgnoreCase("Amarillo")
                    || _color.equalsIgnoreCase("Rojo"))
            {
                Matriz[fila][columna] = _color;
                return true;
            }

        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return false;
    }

    public boolean Movimiento_Valido(int fila, int columna){
        boolean retorno = false;

        int _fila = fila +1;
        if(_fila > 5){
            retorno = true;
        }
        else if(Matriz[_fila][columna] != ""){
            retorno = true;
        }

        return retorno;
    }

    public String WinConditionHorizontal()
    {
        String Ganador= "";
        int contadorRojo = 0;
        int contadorAmarillo = 0;


        //Horizontal
        for (int i = 0; i < 6; i++) //Recorrer filas
        {
            for (int j = 0; j < 7; j++) //Recorrer columnas
            {
                if(!Matriz[i][j].equalsIgnoreCase(""))
                {
                    if (Matriz[i][j].equalsIgnoreCase("Rojo")) {
                        contadorRojo++;
                        contadorAmarillo = 0;
                    } else if (Matriz[i][j].equalsIgnoreCase("Amarillo")) {
                        contadorAmarillo++;
                        contadorRojo = 0;
                    }

                    if (contadorRojo >= 4) {
                        Ganador = "Rojo";
                        break;
                    } else if (contadorAmarillo >= 4) {
                        Ganador = "Amarillo";
                        break;
                    }
                }
                else
                {
                    contadorAmarillo = 0;
                    contadorRojo = 0;
                }
            }
            if (!Ganador.equals("")) {
                break;
            }
        }


        return Ganador;
    }

    public String WinConditionVertical()
    {
        String Ganador= "";
        int contadorRojo = 0;
        int contadorAmarillo = 0;


        //Vertical
        for (int j = 0; j < 7; j++) //Recorrer columnas
        {
            for (int i = 0; i < 6; i++) //Recorrer filas
            {
                if (Matriz[i][j].equalsIgnoreCase("Rojo")) {
                    contadorRojo++;
                    contadorAmarillo = 0;
                } else if (Matriz[i][j].equalsIgnoreCase("Amarillo")) {
                    contadorAmarillo++;
                    contadorRojo = 0;
                }

                if (contadorRojo >= 4) {
                    Ganador = "Rojo";
                    break;
                } else if (contadorAmarillo >= 4) {
                    Ganador = "Amarillo";
                    break;
                }
            }
            contadorAmarillo = 0;
            contadorRojo = 0;
        }

        return Ganador;
    }

    public String WinConditionDiagonal() {
        String Ganador = "";

        try {
            // Diagonal hacia abajo y hacia la derecha
            for (int i = 0; i < 3; i++)//fila
            {
                for (int j = 0; j < 4; j++)//columna
                {
                    if (Matriz[i][j].equalsIgnoreCase("Rojo")
                            && Matriz[i + 1][j + 1].equalsIgnoreCase("Rojo")
                            && Matriz[i + 2][j + 2].equalsIgnoreCase("Rojo")
                            && Matriz[i + 3][j + 3].equalsIgnoreCase("Rojo")) {
                        Ganador = "Rojo";
                        break;

                    } else if (Matriz[i][j].equalsIgnoreCase("Amarillo")
                            && Matriz[i + 1][j + 1].equalsIgnoreCase("Amarillo")
                            && Matriz[i + 2][j + 2].equalsIgnoreCase("Amarillo")
                            && Matriz[i + 3][j + 3].equalsIgnoreCase("Amarillo")) {
                        Ganador = "Amarillo";
                        break;
                    }
                }
            }

            // Diagonal hacia arriba y hacia la derecha
            for (int i = 3; i < 6; i++)//fila
            {
                for (int j = 0; j < 4; j++)//columna
                {
                    if (Matriz[i][j].equalsIgnoreCase("Rojo")
                            && Matriz[i - 1][j + 1].equalsIgnoreCase("Rojo")
                            && Matriz[i - 2][j + 2].equalsIgnoreCase("Rojo")
                            && Matriz[i - 3][j + 3].equalsIgnoreCase("Rojo")) {
                        Ganador = "Rojo";
                        break;
                    } else if (Matriz[i][j].equalsIgnoreCase("Amarillo")
                            && Matriz[i - 1][j + 1].equalsIgnoreCase("Amarillo")
                            && Matriz[i - 2][j + 2].equalsIgnoreCase("Amarillo")
                            && Matriz[i - 3][j + 3].equalsIgnoreCase("Amarillo")) {
                        Ganador = "Amarillo";
                        break;
                    }
                }
            }
        }
        catch (Error e){

        }

        return Ganador;
    }

    public boolean Tablerolleno()
    {
        boolean retorno = false;
        boolean salir = false;
        int contador = 0;
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if(Matriz[i][j] != "")
                {
                    contador++;
                }
                else{
                    salir = true;
                    break;
                }

                if(contador >= 42)
                {
                    retorno = true;
                    salir = true;
                    break;
                }
            }
            if(salir)
            {
                break;
            }
        }

        return retorno;
    }

    public void ReiniciarMatriz(){
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                Matriz[i][j] = "";
            }
        }
    }
}
