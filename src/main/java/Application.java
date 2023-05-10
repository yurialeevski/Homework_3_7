import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        task_HomeWork_3_7();

    }
    public static void task_HomeWork_3_7() {
        EntityManager entityManager = JPAUtility.getEntityManager();

        entityManager.close();
        JPAUtility.close();
    }








}
