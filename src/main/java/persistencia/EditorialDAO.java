/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Editorial;
import java.util.List;

/**
 *
 * @author dnt_t
 */
public class EditorialDAO extends DAO<Editorial> {

    public EditorialDAO() {
            super(Editorial.class);
    }

    public List<Editorial>buscarPorNombre(String nombre){
            return em.createQuery(
            "SELECT e FROM  Editorial e WHERE e.nombre LIKE :nombre", Editorial.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
    }
    
}
