package com.example.hostelscocora.test;
import com.example.hostelscocora.model.Fecha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class testSantiago {

    public static void main(String[] args) {
        /*List<Fecha> ranges = new ArrayList<>();
        ranges.add(new Fecha("2022-05-12","2022-05-20"));
        ranges.add(new Fecha("2022-05-23","2022-06-26"));
        Fecha fecha = new Fecha("2022-05-21", "2022-05-23");

        boolean overlaps = verificarSobrepuesto(ranges, fecha);

        if (overlaps) {
            System.out.println("Si esta en el rango");
        } else {
            System.out.println("no esta en el rango");
        }*/

        /*LocalDate localDate = LocalDate.of(2023, 8, 23);
        LocalDate localDate1 = LocalDate.of(2023, 8, 25);

        Fecha fecha = new Fecha();
        fecha.crearFecha(localDate, localDate1);
        System.out.println(fecha.getFechaInicio());
        System.out.println(fecha.getFechaFinal());*/


        LocalDate localDate = LocalDate.from(LocalDateTime.now());
        System.out.println(localDate);
    }

    private static boolean verificarSobrepuesto(List<Fecha> ranges, Fecha fechaPrueba){
        for (Fecha fecha: ranges) {
            if (fecha.isSobrepuesto(fechaPrueba)){
                return true;
            }
        }
        return false;
    }
}


