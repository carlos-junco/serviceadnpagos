package com.ceiba.pago.servicio.testdatabuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CalcularFechaTestDataBuilder {

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











