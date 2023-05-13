import javax.persistence.EntityManager;
import java.util.List;

public interface LoginfoDAO {
    List<Loginfo> getAllLogInfo(EntityManager entityManager);
    Loginfo getLogInfoById(EntityManager entityManager, int id);
    public void createLogInfo(EntityManager entityManager, Loginfo loginfo);
    public void updateLogInfoById(EntityManager entityManager, int id, Loginfo loginfo);
    public void deleteLogInfoById(EntityManager entityManager, int id);
}
