/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Autor;
import java.util.List;

/**
 *
 * @author dnt_t
 */
public class AutorDAO extends DAO<Autor> {
    
         public AutorDAO() {
                  super(Autor.class);
         }

         public List<Autor> buscarPorNombre(String nombre) {
                return em.createQuery(
                        "SELECT a FROM Autor a WHERE a.nombre LIKE :nombre", Autor.class)
                        .setParameter("nombre", "%" + nombre + "%")
                        .getResultList();
         }
    
}
