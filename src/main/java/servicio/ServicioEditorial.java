/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import entidades.Editorial;
import java.util.List;
import persistencia.EditorialDAO;

/**
 *
 * @author dnt_t
 */
public class ServicioEditorial {
        private EditorialDAO editorialDAO = new EditorialDAO();

        public void crear(String nombre) {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre de la editorial es obligatorio.");
            }
            if (!editorialDAO.buscarPorNombre(nombre).isEmpty()) {
                throw new IllegalArgumentException("Ya existe una editorial con ese nombre.");
            }
            Editorial editorial = new Editorial(null, nombre, true);
            editorialDAO.persistir(editorial);
        }

        public List<Editorial> listarTodas() {
            return editorialDAO.listarTodos();
        }

        public void darDeBaja(Integer id) {
            Editorial editorial = editorialDAO.buscarPorId(id);
            if (editorial == null) {
                throw new IllegalArgumentException("Editorial no encontrada.");
            }
            editorial.setAlta(false);
            editorialDAO.actualizar(editorial);
        }
}
