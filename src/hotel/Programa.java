/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.Scanner;
import java.time.LocalDate;

/**
 *
 * @author diegoleiva, lenin toledo, carlos escudero, jonathan sangurima
 */
public class Programa {

    public static void main(String[] args) {

        int numerohab;
        Scanner entrada = new Scanner(System.in);

        //El usuario asigna el numero de habitaciones existentes del hotel
        System.out.println("Ingrese el nombre del hotel a reservar:");
        String nombreho = entrada.nextLine();
        System.out.println("Introduce el numero de habitaciones del hotel a reservar:");
        numerohab = entrada.nextInt();

        //Se crea un arreglo para ingresar los datos de cada habitacion
        Habitacion[] habitaciones = new Habitacion[numerohab]; //numerohab, limita el tamaño del arreglo segun el numero de habitaciones ireservadas por el usuario

        for (int i = 0; i < numerohab; i++) {

            int actual = i + 1; //Valor i + 1 <- Numero habitacion sera a partir de 1
            System.out.println("Habitacion " + actual);
            entrada.nextLine();

            String ubicacion; //Se ingresa el piso en el que se ubica la habitacion reservada
            System.out.println("Introduce el piso en el que se ubica  la habitacion: ");
            //Scanner leer1 = new Scanner(System.in);
            ubicacion = entrada.nextLine();

            String tipo; //Se define el tipo de habitacion que va a reservar, por ejemplo: sencilla, doble.
            System.out.println("Introduce el tipo de la habitacion a reservar:\nSencilla o Doble ");
            //Scanner leer2 = new Scanner(System.in);
            tipo = entrada.nextLine();

            double costo; //Se define el costo, double: en caso de que el cobro sea por ejemplo $80,75
            System.out.println("Introduce el costo de la habitacion a reservar: ");
            //Scanner leer3 = new Scanner(System.in);
            costo = entrada.nextDouble();

            int capacidad; //Numero de personas maximas para la habitacion
            System.out.println("Introduce la capacidad de la habitacion a reservar: ");
            //Scanner leer4 = new Scanner(System.in);
            capacidad = entrada.nextInt();

            habitaciones[i] = new Habitacion(i + 1, ubicacion, tipo, costo, capacidad);

        }

        HOTEL hotel = new HOTEL("N&H", numerohab, habitaciones); //Hotel recibe: nombre, numhab, habitaciones

        boolean salir = false;
        int opcion;

        while (salir == false) { //Si salir = true, sale de la estructura repetitiva

            System.out.println(" ");
            System.out.printf("SISTEMA DE RESERVACION DEL HOTEL ''%s''\n", nombreho);
            System.out.println("_____________________________________________________");
            System.out.println("     ");
            System.out.println("MENU:");
            System.out.println("1. Mostrar registro de habitaciones");
            System.out.println("2. Reservacion de Habitaciones");
            System.out.println("3. Liberacion de Habitaciones(emicion de factura)");
            System.out.println("4. Reporte Habitaciones reservadas y libres");
            System.out.println("5. SALIR");
            System.out.println("_____________________________________________________");

            Scanner menu = new Scanner(System.in);
            opcion = menu.nextInt();

            switch (opcion) { //Se presenta el registro de habitaciones reservadas
                case 1: {

                    hotel.registroHabitaciones(); //Se llama al objeto creado previamente: hotel <- nombre, numhab y habitaciones ()
                    //Se emplea el metodo: registroHabitaciones
                    break;

                }

                case 2: { //Proceso para reservar habitaciones 

                    //Ingreso de datos del cliente: nombre, cedula, capacidad de la habitacion que necesita, y la fecha de ingreso
                    String nombre;
                    System.out.println("Introduce el nombre del cliente: ");
                    Scanner menu1 = new Scanner(System.in);
                    nombre = menu1.nextLine();

                    String cedula;
                    System.out.println("Introduce el numero de cedula del cliente: ");
                    Scanner menu0 = new Scanner(System.in);
                    cedula = menu0.nextLine();

                    int capacidad;
                    System.out.println("Introduce la capacidad de la habitacion: ");
                    Scanner menu2 = new Scanner(System.in);
                    capacidad = menu2.nextInt();

                    System.out.println("Introduce la fecha (dia - mes - anio) ");
                    int dia;
                    int mes;
                    int anio;

                    Scanner menu3 = new Scanner(System.in);
                    Scanner menu4 = new Scanner(System.in);
                    Scanner menu5 = new Scanner(System.in);
                    dia = menu3.nextInt();
                    mes = menu4.nextInt();
                    anio = menu5.nextInt();

                    //Compara la fecha ingresada con la fecha actual
                    if (LocalDate.of(anio, mes, dia).isBefore(LocalDate.now())) { //Si la fecha ingresada es previa a la actual no sera posible reservar

                        System.out.println("Fecha no valida: La fecha debe ser igual o mayor a la fecha actual");

                    } else { //Si la fecha es valida comienza a almacenar los datos ingresados a una habitacion asignada automaticamente por el sistema segun la capacidad que necesite el cliente

                        Reserva reserva = hotel.reservaHabitacion(nombre, cedula, capacidad, LocalDate.of(anio, mes, dia));

                        if (reserva != null) {
                            System.out.println("Reserva Realizada");
                            System.out.println("Nombre : " + nombre);
                            System.out.println("Nro Habitacion asignada: " + reserva.getHabitacion().getNumero()); //Se asigna la habitacion
                            System.out.printf("FECHA: %d/%d/%d \n", dia, mes, anio);

                        } else { //Si no hay habitacion con la capacidad solicita en estado Libre, no sera posible reservar

                            System.out.println("El tipo de de habitacion que desea reservar se encuentra agotado");

                        }
                    }

                    break;
                }

                case 3: { //Liberar habitacion

                    //Ingreso de los datos del cliente: nombre, cedula, cantidad de dias de su estancia
                    String cancelacion;
                    System.out.println("Introduzca el nombre del cliente ");
                    Scanner menu6 = new Scanner(System.in);
                    cancelacion = menu6.nextLine();

                    String cancelacionced;
                    System.out.println("Introduzca el numero de cedula del cliente ");
                    Scanner menu10 = new Scanner(System.in);
                    cancelacionced = menu10.nextLine();

                    int numdias;
                    System.out.println("Introduzca el numero de dias de estancia en el hotel ");
                    Scanner menu11 = new Scanner(System.in);
                    numdias = menu11.nextInt();

                    //Accede al metodo cancelarReserva.
                    if (hotel.cancelarReserva(cancelacion, cancelacionced, numdias)) {

                        System.out.println("Habitacion liberada");

                    } else {

                        System.out.println("Datos incorrectos: La reserva que trata de cancelar no es valida o no existe");

                    }

                    break;
                }

                case 4: //Reporte de habitaciones

                    //Invoca el metodo reporteHabitaciones para presentar listado de Habitaciones Reservadas y Libre por separado
                    hotel.reporteHabitaciones();
                    break;

                case 5:

                    //Opcion para salir del programa
                    salir = true;
                    break;

                default:

                    //En caso de ingresar una opcion no reconocida.
                    System.out.println("Opcion no valida");

            }
        }

    }
}
