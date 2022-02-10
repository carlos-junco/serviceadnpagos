package com.ceiba.pago.servicio.testdatabuilder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class CalcularFechaVencimiento {
    /**
     * Método que permite calcular la fecha del próximo pago, de acuerdo al día en que se realiza el
     * registro
     * @param nDiasParam
     * @return LocalDateTime
     */

    public static LocalDateTime calcularFecha(int nDiasParam) {

        int nDias = 0;
        LocalDateTime fechaActual = LocalDateTime.now();
        while (nDias < nDiasParam) {
            fechaActual = fechaActual.plusDays(1);
            if (DayOfWeek.SATURDAY != fechaActual.getDayOfWeek()
                    && DayOfWeek.SUNDAY != fechaActual.getDayOfWeek()) {
                nDias++;
            }
        }
        return fechaActual;
    }

    public static void main(String[] args) {
        LocalDateTime hoy = calcularFecha(20);
        System.out.println(hoy);
    }
}
