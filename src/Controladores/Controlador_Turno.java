package Controladores;

public class Controlador_Turno {
    private static Controlador_Turno Instance = null;
    private String turnoActual = ""; //Rojo y Amarillo

    public String getTurnoActual() {
        return turnoActual;
    }

    private Controlador_Turno(String turnoActual) {
        this.turnoActual = turnoActual;
    }

    //Singleton
    public static Controlador_Turno getInstance(String _turnoActual){
        if(_turnoActual.equalsIgnoreCase("rojo") || _turnoActual.equalsIgnoreCase("amarillo"))
            if (Instance == null) {
                Instance = new Controlador_Turno(_turnoActual);
            }

            return Instance;
    }

    public void CambiarTurno(){

        if(turnoActual.equalsIgnoreCase("rojo")){
            turnoActual = "Amarillo";
        } else if (turnoActual.equalsIgnoreCase("amarillo")) {
            turnoActual = "Rojo";
        }
    }
}
