package com.example.hostelscocora.test;
import com.example.hostelscocora.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class testSantiago {

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hola");
        Fecha fecha = new Fecha("2022-05-12","2022-05-20");
        Fecha fechaPrueba = new Fecha("2022-05-21","2022-06-26");
        Cama cama = new Cama();
        cama.setPeso((byte) 1);
        cama.setId("0");
        cama.setEstadoCama(ESTADO_CAMA.OPERACION);
        Cama cama2 = new Cama();
        cama2.setPeso((byte) 1);
        cama2.setId("1");
        cama2.setEstadoCama(ESTADO_CAMA.OPERACION);
        Habitacion habitacion = new Habitacion();
        habitacion.setCapacidad((byte) 2);
        DetalleReserva detalleReserva = new DetalleReserva();
        detalleReserva.setFecha(fecha);
        habitacion.getListaDetalleReserva().add(detalleReserva);
        detalleReserva.setHabitacion(habitacion);
        //hotel
        hotel.getListaHabitaciones().add(habitacion);
        hotel.getListaCamas().add(cama);
        hotel.getListaCamas().add(cama2);

        if(hotel.verificarCamasDisponibles((byte) 1,habitacion.getCapacidad(),fechaPrueba)){
            System.out.println("se añadio");
            hotel.aniadirCamas(habitacion,false,fechaPrueba);
            DetalleReserva detalleReserva1 = new DetalleReserva();
            detalleReserva1.setHabitacion(habitacion);
            detalleReserva1.setFecha(fechaPrueba);
            habitacion.getListaDetalleReserva().add(detalleReserva1);

        }

        //System.out.println(habitacion);
        System.out.println(hotel.verificarCamasDisponibles((byte) 1,habitacion.getCapacidad(),fechaPrueba));

        //System.out.println(hotel.verificarCamasDisponibles((byte)1, habitacion.getCapacidad(), fechaPrueba));
    }

    public void funcionesTest(){
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


