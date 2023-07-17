package dao;

import entities.Profile;
import entities.User;
import exceptions.ProfileException;
import exceptions.UserException;
import jakarta.persistence.EntityManager;
import utilities.EMUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public void addUser(User user) throws UserException {
		// TODO Auto-generated method stub
		
        EntityManager em = EMUtil.getEntityManager();
		
		
        User users = em.find(User.class,user.getId());
		
		if(users != null) {
			throw new UserException("User is already present with this id "+user.getId());
		}else {
			em.getTransaction().begin();
			em.persist(users);
			em.getTransaction().commit();
			em.close();
		}
		
		System.out.println("User addded successfully");
		
	}

	@Override
	public User getUserById(int id) throws UserException {
		// TODO Auto-generated method stub
		
EntityManager em = EMUtil.getEntityManager();
		
User users = em.find(User.class,id);
		
		if(users != null) {
			return users;
		}else {
			throw new UserException("User is already present with this id "+id);
		}
		
	}

	@Override
	public void updateUser(User user) throws UserException {
		// TODO Auto-generated method stub
		
		EntityManager em = EMUtil.getEntityManager();
        try {
            em.getTransaction().begin();;
            

            em.merge(user); 

            em.getTransaction().commit();
            
        } catch (Exception e) {
            if ( em.getTransaction() != null) {
            	 em.getTransaction().rollback();
            }
            throw new UserException("failed to update");
        } finally {
        	em.close();
        }
		
	}

	@Override
	public void deleteUser(User user) throws UserException {
		// TODO Auto-generated method stub
		EntityManager em = EMUtil.getEntityManager();
		User users = new User();
		
		users = em.find(User.class, user.getId());
		
		
		if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }else {
        	System.out.println("Failed to delete");
        	
        }
        em.getTransaction().begin();
		
		em.remove(users);
		
		em.getTransaction().commit();
		em.close();
		
		
		
		System.out.println("User deleted successfully");
		
	}
	}


