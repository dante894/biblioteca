/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import entidades.Cliente;
import entidades.Libro;
import entidades.Prestamo;
import java.util.Date;
import java.util.List;
import persistencia.LibroDAO;
import persistencia.PrestamoDAO;

/**
 *
 * @author dnt_t
 */
public class ServicioPrestamo {
     private PrestamoDAO prestamoDAO = new PrestamoDAO();
     private LibroDAO libroDAO = new LibroDAO();

    public void registrarPrestamo(Long isbn, Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Debe indicar un cliente.");
        }
        if (!cliente.isAlta()) {
            throw new IllegalArgumentException("El cliente está dado de baja.");
        }

        Libro libro = libroDAO.buscarPorId(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("Libro no encontrado.");
        }
        if (!libro.isAlta()) {
            throw new IllegalArgumentException("El libro está dado de baja.");
        }
        if (libro.getEjemplaresRestantes() == null || libro.getEjemplaresRestantes() <= 0) {
            throw new IllegalArgumentException("No hay ejemplares disponibles de este libro.");
        }

        // Actualizar contadores del libro
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
        libroDAO.actualizar(libro);

        // Registrar el préstamo con fecha actual (nunca anterior, porque la fijamos acá mismo)
        Prestamo prestamo = new Prestamo(new Date(), libro, cliente);
        prestamoDAO.persistir(prestamo);
    }

    public void devolverLibro(Integer idPrestamo) {
        Prestamo prestamo = prestamoDAO.buscarPorId(idPrestamo);
        if (prestamo == null) {
            throw new IllegalArgumentException("Préstamo no encontrado.");
        }
        if (prestamo.getFechaDevolucion() != null) {
            throw new IllegalArgumentException("Este préstamo ya fue devuelto.");
        }

        Libro libro = prestamo.getLibros();
        if (libro.getEjemplaresPrestados() == null || libro.getEjemplaresPrestados() <= 0) {
            throw new IllegalArgumentException("Inconsistencia: no hay ejemplares prestados registrados para este libro.");
        }

        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
        libroDAO.actualizar(libro);

        prestamo.setFechaDevolucion(new Date());
        prestamoDAO.actualizar(prestamo);
    }

    public List<Prestamo> buscarPorCliente(Long documentoCliente) {
        return prestamoDAO.buscarPorCliente(documentoCliente);
    }

    public List<Prestamo> listarTodos() {
        return prestamoDAO.listarTodos();
    }
}
