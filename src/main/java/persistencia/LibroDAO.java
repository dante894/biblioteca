/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Libro;
import java.util.List;

/**
 *
 * @author dnt_t
 */
public class LibroDAO extends DAO<Libro> {
    
    public LibroDAO() {
        super(Libro.class);
    }
    
    public Libro buscarPorTitulo(String titulo){
        List<Libro> resultado = em.createQuery(
        "SELECT l FROM Libro l WHERE l.titulo = :titulo",Libro.class)
                .setParameter("titulo", titulo)
                .getResultList();
        return resultado.isEmpty() ? null : resultado.get(0);
    }
    
        public List<Libro> buscarPorAutor(String nombreAutor) {
        return em.createQuery(
                "SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre", Libro.class)
                .setParameter("nombre", "%" + nombreAutor + "%")
                .getResultList();
    }

    public List<Libro> buscarPorEditorial(String nombreEditorial) {
        return em.createQuery(
                "SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre", Libro.class)
                .setParameter("nombre", "%" + nombreEditorial + "%")
                .getResultList();
    
    }
}