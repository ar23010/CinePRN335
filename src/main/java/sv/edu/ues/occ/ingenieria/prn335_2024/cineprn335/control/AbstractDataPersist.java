package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.enterprise.inject.Typed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractDataPersist<T> {

    public abstract EntityManager getEntityManager();

    Class tipoDatos;

    public AbstractDataPersist(Class tipoDatos) {
        this.tipoDatos = tipoDatos;
    }

    public void create(T entity) throws IllegalStateException, IllegalArgumentException {
        EntityManager em = null;
        if(entity == null){
            throw new IllegalArgumentException("La entidad a persistir no puede ser nula");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.persist(entity);
        } catch(Exception e){
            throw new IllegalStateException("Error al aaceder al repositorio", e);
        }
    }


    public T findById(final Object id) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (id == null) {
            throw new IllegalArgumentException("Parametro no válido: idTipoSala");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error al acceder al repositorio");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error al acceder al repositorio", ex);
        }
        return (T) em.find(tipoDatos, id);
    }

    public List<T> findRange(int first, int max) throws IllegalStateException, IllegalArgumentException {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(tipoDatos);
        Root<T> raíz = q.from(tipoDatos);
        CriteriaQuery cq = q.select(raíz);
        TypedQuery query = getEntityManager().createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(max);
        return query.getResultList();
    }


    public T update(T entity) throws IllegalStateException, IllegalArgumentException {
        EntityManager em = null;
        if (entity == null) {
            throw new IllegalArgumentException("La entidad a persistir no puede ser nula");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
         return  em.merge(entity);
        } catch(Exception e){
            throw new IllegalStateException("Error al aaceder al repositorio", e);
        }


    }


    public void delete(T entity) throws IllegalStateException, IllegalArgumentException {
        EntityManager em = null;
        if (entity == null) {
            throw new IllegalArgumentException("La entidad a persistir no puede ser nula");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.remove(em.merge(entity));
        } catch(Exception e){
            throw new IllegalStateException("Error al aaceder al repositorio", e);
        }

    }


}

