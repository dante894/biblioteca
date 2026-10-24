/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author dnt_t
 */
@Entity
public class Prestamo {
         @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
         
         @Temporal(javax.persistence.TemporalType.DATE)
         private Date fechaPrestamo;
         
         @Temporal(javax.persistence.TemporalType.DATE)
         private Date fechaDevolucion;
         
         @ManyToOne
         private Libro libros;
         
         @ManyToOne
         private Cliente cliente;

    public Prestamo() {
    }

    public Prestamo(Date fechaPrestamo, Libro libros, Cliente cliente) {
        this.fechaPrestamo = fechaPrestamo;
        this.libros = libros;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        this.libros = libros;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
         
    @Override
    public String toString() {
        return "Préstamo{id=" + id + ", libro=" + libros.getTitulo()
                + ", cliente=" + cliente.getNombre() + " " + cliente.getApellido()
                + ", fechaPrestamo=" + fechaPrestamo
                + ", fechaDevolucion=" + (fechaDevolucion != null ? fechaDevolucion : "PENDIENTE") + '}';
    }
         
}
