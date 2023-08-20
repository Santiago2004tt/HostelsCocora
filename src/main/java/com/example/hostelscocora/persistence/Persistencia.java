package com.example.hostelscocora.persistence;

import com.example.hostelscocora.model.Hotel;

public class Persistencia {

    public static final String RUTA_ARCHIVO_LOG = "C:/td/persistencia/log/MarketPlaceLog.txt";
    public static final String RUTA_ARCHIVO_MODELO_HOTEL_BINARIO = "C:/td/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_HOTEL_XML = "C:/td/persistencia/model.xml";


    //    ----------------------LOADS------------------------

    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    //------------------------------------SERIALIZACIÓN  y XML


    public static Hotel cargarRecursoMarketPlaceBinario() {

        Hotel hotel = new Hotel();

        try {
            hotel = (Hotel) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_HOTEL_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hotel;
    }

    public static void guardarRecursoMarketPlaceBinario(Hotel hotel) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_HOTEL_BINARIO, hotel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Hotel cargarRecursoMarketPlaceXML() {

        Hotel marketPlaceVendedores = null;

        try {
            marketPlaceVendedores = (Hotel) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_HOTEL_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return marketPlaceVendedores;

    }

    public static void guardarRecursoMarketPlaceXML(Hotel hotel) {

                try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_HOTEL_XML, hotel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
