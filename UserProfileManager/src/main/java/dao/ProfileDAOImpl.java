package dao;

import entities.Profile;
import exceptions.ProfileException;
import jakarta.persistence.EntityManager;
import utilities.EMUtil;

public class ProfileDAOImpl implements ProfileDAO{

	@Override
	public void addProfile(Profile profile) throws ProfileException {
		// TODO Auto-generated method stub
		
		EntityManager em = EMUtil.getEntityManager();
		
		
		Profile p = em.find(Profile.class,profile.getId());
		
		if(p != null) {
			throw new ProfileException("Profile is already present with this id "+profile.getId());
		}else {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			em.close();
		}
		
		System.out.println("Profile addded successfully");
		
	}

	@Override
	public Profile getProfileById(int id) throws ProfileException {
		// TODO Auto-generated method stub
		
        EntityManager em = EMUtil.getEntityManager();
		
        Profile p = em.find(Profile.class,id);
		
		if(p != null) {
			return p;
		}else {
			throw new ProfileException("Profile is already present with this id "+id);
		}
		
		
	}

	@Override
	public void updateProfile(Profile profile) throws ProfileException {
		// TODO Auto-generated method stub
		  EntityManager em = EMUtil.getEntityManager();
        try {
            em.getTransaction().begin();;
            

            em.merge(profile); 

            em.getTransaction().commit();
        } catch (Exception e) {
            if ( em.getTransaction() != null) {
            	 em.getTransaction().rollback();
            }
            throw new ProfileException("failed to update");
        } finally {
        	em.close();
        }
    }
	

	@Override
	public void deleteProfile(Profile profile) throws ProfileException {
		// TODO Auto-generated method stub
		
		EntityManager em = EMUtil.getEntityManager();
		Profile p = new Profile();
		
		p = em.find(Profile.class, profile.getId());
		
		
		if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }else {
        	System.out.println("Failed to delete");
        	
        }
        em.getTransaction().begin();
		
		em.remove(p);
		
		em.getTransaction().commit();
		em.close();
		
		
		
		System.out.println("Profile deleted successfully");
		
	}

}
