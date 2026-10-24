/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Cliente;
import java.util.List;


/**
 *
 * @author dnt_t
 */
public class ClienteDAO  extends DAO<Cliente> {
 
    public ClienteDAO() {
        super(Cliente.class);
    }
    
    public List<Cliente> buscarPorDocumento(Long documento) {
        return em.createQuery(
                "SELECT c FROM Cliente c WHERE c.documento = :documento", Cliente.class)
                .setParameter("documento", documento)
                .getResultList();
    }
        
        
}
