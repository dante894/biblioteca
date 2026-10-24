/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import entidades.Autor;
import entidades.Editorial;
import entidades.Libro;
import java.util.List;
import persistencia.LibroDAO;

/**
 *
 * @author dnt_t
 */
public class ServicioLibro {
        private LibroDAO libroDAO = new LibroDAO();

    public void crear(Long isbn, String titulo, Integer anio, Integer ejemplares,
                       Autor autor, Editorial editorial) {
        if (isbn == null) throw new IllegalArgumentException("El ISBN es obligatorio.");
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("El título es obligatorio.");
        if (autor == null) throw new IllegalArgumentException("Debe seleccionar un autor.");
        if (editorial == null) throw new IllegalArgumentException("Debe seleccionar una editorial.");
        if (libroDAO.buscarPorId(isbn) != null) {
            throw new IllegalArgumentException("Ya existe un libro con ese ISBN.");
        }

        Libro libro = new Libro(isbn, titulo, anio, ejemplares, autor, editorial);
        libroDAO.persistir(libro);
    }

    public List<Libro> listarTodos() {
        return libroDAO.listarTodos();
    }

    public Libro buscarPorIsbn(Long isbn) {
        return libroDAO.buscarPorId(isbn);
    }

    public Libro buscarPorTitulo(String titulo) {
        return libroDAO.buscarPorTitulo(titulo);
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        return libroDAO.buscarPorAutor(nombreAutor);
    }

    public List<Libro> buscarPorEditorial(String nombreEditorial) {
        return libroDAO.buscarPorEditorial(nombreEditorial);
    }

    public void darDeBaja(Long isbn) {
        Libro libro = libroDAO.buscarPorId(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("Libro no encontrado.");
        }
        libro.setAlta(false);
        libroDAO.actualizar(libro);
    }
}
