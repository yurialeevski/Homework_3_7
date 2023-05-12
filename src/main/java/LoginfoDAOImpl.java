import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class LoginfoDAOImpl implements LoginfoDAO {
    public LoginfoDAOImpl() {
    }

    @Override
    public List<Loginfo> getAllLogInfo(EntityManager entityManager) {
        List<Loginfo> loginfos = new ArrayList<>();
        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT l FROM Loginfo l";
        TypedQuery<Loginfo> query = entityManager.createQuery(jpqlQuery, Loginfo.class);
        loginfos = query.getResultList();
        entityManager.getTransaction().commit();
        return loginfos;
    }

    @Override
    public Loginfo getLogInfoById(EntityManager entityManager, int id) {
        Loginfo log = null;
        entityManager.getTransaction().begin();
        log = entityManager.find(Loginfo.class, new Integer(id));
        entityManager.getTransaction().commit();
        return log;
    }

    @Override
    public void createLogInfo(EntityManager entityManager, Loginfo loginfo) {
        entityManager.getTransaction().begin();
        entityManager.persist(loginfo);
        entityManager.getTransaction().commit();
    }
    @Override
    public void updateLogInfoById(EntityManager entityManager, int id, Loginfo loginfo) {
        entityManager.getTransaction().begin();
        Loginfo logInfoCheck = entityManager.find(Loginfo.class, new Integer(id));
        if(entityManager.contains(logInfoCheck)) {
            loginfo.setCreateDate(logInfoCheck.getCreateDate());
            entityManager.merge(loginfo);
            entityManager.flush();
            System.out.println("Пользователь ID: " + id + " обновлен");
        } else {
            System.out.println("Пользователь с ID: " + id + " не найден в БД");
        }
        entityManager.getTransaction().commit();
    }
    @Override
    public void deleteLogInfoById(EntityManager entityManager, int id) {
        entityManager.getTransaction().begin();
        Loginfo logInfoCheck = entityManager.find(Loginfo.class, new Integer(id));
        if(entityManager.contains(logInfoCheck)) {
            entityManager.remove(logInfoCheck);
            System.out.println("Пользователь ID: " + id + " удален");
        } else {
            System.out.println("Пользователь с ID: " + id + " не найден в БД");
        }
        entityManager.getTransaction().commit();
    }

    public void printLogInfo(Loginfo loginfo) {
        if(loginfo == null) {
            System.out.println("Пользователя с таким ID нет в базе данных");
        } else {
            System.out.println("ID пользователя : " + loginfo.getLogInfoId() +
                    " \tимя: " + loginfo.getLogName() + "" + " логин: " + loginfo.getLogIn() +
                    " password: " + loginfo.getPassWord() + " создан: " + loginfo.getCreateDate() +
                    " изменен: " + loginfo.getUpdateDate());
        }
    }
    public void printManyLogInfo (List<Loginfo> list) {
        for(Loginfo log: list) {
            printLogInfo(log);
        }
    }
    public void printLogInfoWithRoles(Loginfo loginfo) {
        if(loginfo == null) {
            System.out.println("Пользователя с таким ID нет в базе данных");
        } else {
            System.out.println("ID пользователя : " + loginfo.getLogInfoId() +
                    " \tимя: " + loginfo.getLogName() + "" + " логин: " + loginfo.getLogIn() +
                    " password: " + loginfo.getPassWord() + " создан: " + loginfo.getCreateDate() +
                    " изменен: " + loginfo.getUpdateDate());
            System.out.println("Роли пользователя: " + loginfo.getActivities());
        }
    }
}
