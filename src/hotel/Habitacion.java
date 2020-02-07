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
 * 
 */
public class Habitacion {
    
    //Creacion de variables a ocupar dentro del programa
    //Private: Solo se puede acceder al miembro en su propia clase
    private int nume; //Numero de Habitacion
    private String ubicacion; //Planta en la que se ubica la habitacion
    private String tipo; //Tipo de la Habitacion
    private double costo; //Costo de la Habitacion
    private int capacidad; //Numero de personas
    private boolean ocupada = false; //Asigna un valor para asignar luego a la lista de Libres o Reservadas

    /**
     * Constructor de habitacion.
     * Constructores: Funcion de la clase, es llamada
     * automaticamente cuando se crea un objeto de esa clase. Siempre tienen el
     * mismo nombre de la clase.
     */
    public Habitacion(int numero, String ubicacion, String tipo, double costo, int capacidad) { //numero, ubic, tipo, coste, capac: datos que recibe

        //this = Invocar un constructor
        this.nume = numero;
        this.capacidad = capacidad;
        this.costo = costo;
        this.tipo = tipo;
        this.ubicacion = ubicacion;

    }

    public void reserva() {

        this.ocupada = true;

    }

    public void cancelacion() {

        this.ocupada = false;

    }

    /**
     * Construccion de Getters y Setters Definen una propiedad, se accede como
     * propiedades situadas fuera de la clase, definidas de la clase como
     * metodos
     */
    public int getCapacidad() {
//Return: Sale de la secuencia, devuelve el valor de asignado a la variable "capacidad"
        return capacidad;

    }

    public double getCosto() {
//Return: Sale de la secuencia, devuelve el valor de asignado a la variable "coste"
        return costo;

    }

    public int getNumero() {
//Return: Sale de la secuencia, devuelve el valor de asignado a la variable "numero".
        return nume;

    }

    public String getTipo() {
//Return: Sale de la secuencia, devuelve el valor de asignado a la variable "tipo".
        return tipo;

    }

    public String getUbicacion() {
//Return: Sale de la secuencia, devuelve el valor de asignado a la variable "ubicacion".
        return ubicacion;

    }

    public boolean getOcupada() {
//Return: Sale de la secuencia, devuelve el valor de asignado a la variable "ocupada".
        return this.ocupada;

    }

}
