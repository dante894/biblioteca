/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import entidades.Cliente;
import java.util.List;
import persistencia.ClienteDAO;


/**
 *
 * @author dnt_t
 */
public class ServicioCliente {
        private ClienteDAO clienteDAO = new ClienteDAO();
        
        public void crear(Long documento, String nombre, String apellido, String telefono){
                  if (documento == null) {
                           throw new IllegalArgumentException("El documento es obligatorio.");
                  }
                  
                  if (nombre == null || nombre.isBlank()) {
                        throw new IllegalArgumentException("El nombre es obligatorio.");
                  }
                  
                  if (apellido == null || apellido.isBlank()) {
                        throw new IllegalArgumentException("El apellido es obligatorio.");
                  }
                  
                  if (!clienteDAO.buscarPorDocumento(documento).isEmpty()) {
                        throw new IllegalArgumentException("Ya existe un cliente con ese documento.");
                  }
                  
                  Cliente cliente = new Cliente(documento, nombre, apellido,telefono);
                  clienteDAO.persistir(cliente);
        }
        
        public List <Cliente> listarTodos(){
                  return clienteDAO.listarTodos();
        }
        
        public List <Cliente>buscarPorDocumento(Long documento){
                 return clienteDAO.buscarPorDocumento(documento);
        }
        
        public void darBaja(Integer id){
                    Cliente cliente = clienteDAO.buscarPorId(id);
                    if(cliente == null){
                                   throw new IllegalArgumentException("Cliente no encontrado.");
                                   
                    }
                    else{
                                   cliente.setAlta(false);
                                   clienteDAO.actualizar(cliente);
                    }
        }
}
