/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import entidades.Autor;
import java.util.List;
import persistencia.AutorDAO;

/**
 *
 * @author dnt_t
 */
public class ServicioAutor {
        
            private AutorDAO autorDAO = new AutorDAO();
            
            public void crear(String nombre){
                            if(nombre == null || nombre.isBlank()){
                                            throw new IllegalArgumentException("El nombre del autor es obligatorio");
                            }
                            if(!autorDAO.buscarPorNombre(nombre).isEmpty()){
                                                    throw new IllegalArgumentException("Ya existe un autor con ese nombre");
                            }
                            
                            Autor autor = new Autor(null,nombre, true);
                            autorDAO.persistir(autor);
            }
            
            public List<Autor> listarTodos() {
                            return autorDAO.listarTodos();
            }

            public List<Autor> buscarPorNombre(String nombre) {
                return autorDAO.buscarPorNombre(nombre);
            }

            public void darDeBaja(Integer id) {
                Autor autor = autorDAO.buscarPorId(id);
                if (autor == null) {
                    throw new IllegalArgumentException("Autor no encontrado.");
                }
                autor.setAlta(false);
                autorDAO.actualizar(autor);
            }
}
