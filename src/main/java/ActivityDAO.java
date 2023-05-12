import javax.persistence.EntityManager;

public interface ActivityDAO {
    public Activity getActivityById(EntityManager entityManager, int id);
    public void createActivity(EntityManager entityManager);
}
