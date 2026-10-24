/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dnt_t
 * @param <T>
 */
public class DAO <T> {
        protected EntityManager em;
        Class<T> clase;

        public DAO() {
        }
        
        
        public DAO(Class<T> clase){
                this.clase = clase;
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreria");
                em = emf.createEntityManager();
        }
        
        
        public void persistir(T entidad){
                em.getTransaction().begin();
                em.persist(entidad);
                em.getTransaction().commit();
        }
        
        public void actualizar(T entidad){
                em.getTransaction().begin();
                em.merge(entidad);
                em.getTransaction().commit();
        }
        
        public T buscarPorId(Object id){
                return em.find(clase, id);
        }
        
        @SuppressWarnings("unchecked")
          public List<T> listarTodos() {
                Query query = em.createQuery("SELECT o FROM " + clase.getSimpleName() + " o");
                return query.getResultList();
        }
}
