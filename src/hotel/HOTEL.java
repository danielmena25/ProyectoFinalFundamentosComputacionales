/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;
import java.time.LocalDate;
/**
 *
 * @author diegoleivaespin, lenin toledo, carlos escudero, jonathan sangurima
 */
public class HOTEL {
    
    

  //Private: Solo se puede acceder al miembro en su propia clase
    private String nombre; //Nombre del cliente para reservar la habitacion
    private int habita; //Numero de Habitaciones a reservar 
    private final Habitacion[] reg_habitaciones; //Registro de Habitaciones reservadas
    private Reserva[] reservas; //Habitaciones reservadas por el cliente

    /**
     * Constructor del hotel. 
     * Constructores: Funcion de la clase, es llamada
     * automaticamente cuando se crea un objeto de esa clase. 
     */
    public HOTEL(String nombre, int numerohab, Habitacion[] habitacions) {
        this.habita = numerohab;
        this.nombre = nombre;
        this.reg_habitaciones = habitacions;
        Reserva[] reservas = new Reserva[numerohab];

        for (int i = 0; i < habitacions.length; i++) {

            reservas[i] = null;

        }

        this.reservas = reservas;

    }

    //Realiza la reserva de una habitacion si la habitacion solicitada esta disponible
    //Devuelve la nueva reserva y si no se puede realizar devuelve un puntero nulo
    public Reserva reservaHabitacion(String nombre, String numeroCedula, int capacidad, LocalDate fecha) {

        int i = 0;
        boolean buscado = false;

        //Busca entre todas las habitaciones si la habitaci√≥n actual tiene la capacidad adecuada y esta desocupada
        //Si la encuentra entonces para la busqueda
        while (i < habita && buscado == false) {

            if (reg_habitaciones[i].getOcupada() == false && reg_habitaciones[i].getCapacidad() >= capacidad) {

                buscado = true; 

            }

            i++;

        }

        //Comprobamos si la habitacion fue encontrada. En caso afirmativo se procede a realizar la reserva
        if (buscado) {

            Reserva nueva = new Reserva(nombre, numeroCedula, reg_habitaciones[i - 1], fecha);
            reg_habitaciones[i - 1].reserva();
            int numero = reg_habitaciones[i - 1].getNumero() - 1;
            reservas[numero] = nueva;
            return nueva;

        }
        //Si no devuelve un puntero nulo que indica que la reserva no se realizar
        return null;

    }
    //Busca en el arreglo de las reservas si existe alguna del cliente (nombre y n¬∫ cedula)
    //Devuelve un booleano que nos indica si fu√© posible o no hacer la reserva

    //Metodo para cancelar habitacion
    public boolean cancelarReserva(String nombre, String numeroCedula, int numdias) {

        int i = 0;
        boolean buscado = false;

        //Busca en las habitaciones reservadas coincidencia entre el cliente y num cedula.
        while (i < habita && buscado == false) {
            //Si la encuentra el estado de buscado para a ser verdadero
            if (reservas[i] != null && reservas[i].getCliente().equals(nombre) && reservas[i].getCedula().equals(numeroCedula)) {
                buscado = true;
            }
            i++;

        }

        //Proceso para liberar la habitacion en caso de buscado == verdadero
        if (buscado) {

            reservas[i - 1] = null;
            reg_habitaciones[i - 1].cancelacion();

            //Segun el costo de la habitacion del cliente se multiplica al numero de dias (debe ser ingresado).
            double total = reg_habitaciones[i - 1].getCosto() * numdias;
            System.out.println("Total adeudado: " + total);
            return true;

        }

        return false;

    }

    //Para presentacion de Tablas: HABITACIONES RESERVADAS - HABITACIONES LIBRES
    public void reporteHabitaciones() {

        int contador = 0;
        System.out.println("HABITACIONES RESERVADAS:");
        System.out.printf("%-10s %-15s %-20s %-10s %-20s %-20s", "numero", "tipo", "capacidad", "costo", "ubicacion", "reserva");
        System.out.println(" ");

        //Se recorre el arreglo de las habitaciones, y busca aquella que el estado Ocupada == verdadero.
        for (int i = 0; i < habita; i++) {

            if (reg_habitaciones[i].getOcupada() == true) {
                String personas;
                if (reg_habitaciones[i].getCapacidad() == 1) {
                    personas = "persona";
                } else {
                    personas = "personas";
                }
                System.out.printf("%-10d %-15s %-2d %-17s %-10.1f %-20s %-20s %-12s", reg_habitaciones[i].getNumero(), reg_habitaciones[i].getTipo(), reg_habitaciones[i].getCapacidad(), personas, reg_habitaciones[i].getCosto(), reg_habitaciones[i].getUbicacion(), reservas[i].getCliente(), reservas[i].getCedula());
                System.out.println(" ");
                contador += 1;
            }

        }

        System.out.println("TOTAL: " + contador);

        //Contador se reinicia en 0 para empezar a contar las habitaciones libres
        contador = 0;
        System.out.println(" ");
        System.out.println("HABITACIONES LIBRES:");
        System.out.printf("%-10s %-15s %-20s %-10s %-20s", "numero", "tipo", "capacidad", "costo", "ubicacion");
        System.out.println(" ");

        for (int i = 0; i < habita; i++) {
            //Se recorre el arreglo de las habitaciones, y busca aquella que el estado Ocupada == falso
            if (reg_habitaciones[i].getOcupada() == false) {

                String personas;
                if (reg_habitaciones[i].getCapacidad() == 1) {
                    personas = "persona";
                } else {
                    personas = "personas";
                }
                System.out.printf("%-10d %-15s %-2d %-17s %-10.1f %-20s", reg_habitaciones[i].getNumero(), reg_habitaciones[i].getTipo(), reg_habitaciones[i].getCapacidad(), personas, reg_habitaciones[i].getCosto(), reg_habitaciones[i].getUbicacion());
                System.out.println(" ");
                contador += 1;

            }

        }

        System.out.println("TOTAL: " + contador);

    }

    public void registroHabitaciones() {

        //printf <- dar formato: Espaciado (%-10s....)
        System.out.printf("%-10s %-15s %-20s %-10s %-20s %-20s", "num", "tipo", "capacidad", "costo", "ubicacion", "ocupada");
        System.out.println(" ");

        for (int i = 0; i < habita; i++) {

            String personas; //Segun el numero de personas se a√±ade: persona o personas
            if (reg_habitaciones[i].getCapacidad() == 1) {
                personas = "persona";
            } else {
                personas = "personas";
            }
            System.out.printf("%-10d %-15s %-2d %-17s %-10.1f %-20s %-20s", reg_habitaciones[i].getNumero(), reg_habitaciones[i].getTipo(), reg_habitaciones[i].getCapacidad(), personas, reg_habitaciones[i].getCosto(), reg_habitaciones[i].getUbicacion(), reg_habitaciones[i].getOcupada());
            System.out.println(" ");

        }

        System.out.println(" ");

    }

    /**
     * Construccion de Getters y Setters 
     * Definen una propiedad, se accede como
     * propiedades situadas fuera de la clase, definidas de la clase como
     * metodos
     */
    public int getHabitaciones() {

        //Return: Sale de la secuencia, devuelve el valor de asignado a la variable "habitaciones"
        return habita;

    }

    public String getNombre() {

        //Return: Sale de la secuencia, devuelve el valor de asignado a la variable "nombre"
        return nombre;

    }
}
