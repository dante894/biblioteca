/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Prestamo;
import java.util.List;

/**
 *
 * @author dnt_t
 */
public class PrestamoDAO extends DAO <Prestamo>{

    public PrestamoDAO() {
        super(Prestamo.class);
    }
    
    public List<Prestamo> buscarPorCliente(Long documentoCliente) {
        return em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.cliente.documento = :documento", Prestamo.class)
                .setParameter("documento", documentoCliente)
                .getResultList();
    }
            
}
