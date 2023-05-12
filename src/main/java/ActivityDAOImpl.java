import javax.persistence.EntityManager;

public class ActivityDAOImpl implements ActivityDAO {
    public ActivityDAOImpl() {
    }

    @Override
    public Activity getActivityById(EntityManager entityManager, int id) {
            Activity activity = null;
            entityManager.getTransaction().begin();
            activity = entityManager.find(Activity.class, new Integer(id));
            entityManager.getTransaction().commit();
            return activity;
    }
    @Override
    public void createActivity(EntityManager entityManager) {
        Activity activity = new Activity();
        activity.setActivityRole("Junior разработчик");
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
    }
}
