package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


import java.util.List;

public abstract class AbstractDataPersistence<T> {

    public abstract EntityManager getEntityManager();

    Class tipoDatos;

    public AbstractDataPersistence(Class tipoDatos) {
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

   public List<T> findRange(int first, int max) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (first < 0 || first > max) {
            throw new IllegalArgumentException("First no puede ser negativo ni mayor que max");
        }
        try{
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(tipoDatos);
            Root<T> root = cq.from(tipoDatos);
            cq.select(root);
            TypedQuery<T> q = em.createQuery(cq);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch(Exception e){
            throw new IllegalStateException("Error al acceder al repositorio", e);
        }
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
            em.remove(entity);
        } catch(Exception e){
            throw new IllegalStateException("Error al aaceder al repositorio", e);
        }

    }

    public Long count() throws IllegalStateException, IllegalArgumentException {
        EntityManager em = null;

        try{
            em = getEntityManager();

            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<T> raiz = cq.from(tipoDatos);
            cq.select(cb.count(raiz));

            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult();
        } catch(Exception e){
            throw new IllegalStateException("Error al aaceder al repositorio", e);
        }

    }


    public String imprimirCarnet() {
        return "AR23010";
    }


}

