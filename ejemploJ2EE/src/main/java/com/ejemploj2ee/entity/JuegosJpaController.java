/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemploj2ee.entity;

import com.ejemploj2ee.entity.exceptions.NonexistentEntityException;
import com.ejemploj2ee.entity.exceptions.PreexistingEntityException;
import com.ejemploj2ee.entity.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author oscar
 */
public class JuegosJpaController implements Serializable {

    public JuegosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Juegos juegos) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (juegos.getJuegosPK() == null) {
            juegos.setJuegosPK(new JuegosPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(juegos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJuegos(juegos.getJuegosPK()) != null) {
                throw new PreexistingEntityException("Juegos " + juegos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Juegos juegos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            juegos = em.merge(juegos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                JuegosPK id = juegos.getJuegosPK();
                if (findJuegos(id) == null) {
                    throw new NonexistentEntityException("The juegos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(JuegosPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Juegos juegos;
            try {
                juegos = em.getReference(Juegos.class, id);
                juegos.getJuegosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The juegos with id " + id + " no longer exists.", enfe);
            }
            em.remove(juegos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Juegos> findJuegosEntities() {
        return findJuegosEntities(true, -1, -1);
    }

    public List<Juegos> findJuegosEntities(int maxResults, int firstResult) {
        return findJuegosEntities(false, maxResults, firstResult);
    }

    private List<Juegos> findJuegosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Juegos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Juegos findJuegos(JuegosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Juegos.class, id);
        } finally {
            em.close();
        }
    }

    public int getJuegosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Juegos> rt = cq.from(Juegos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
