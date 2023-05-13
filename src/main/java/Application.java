import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        task_HomeWork_3_7();
    }

    public static void task_HomeWork_3_7() {
        System.out.println("Тестирование функциональности (методов LoginfoDAOImpl и ActivityDAOImpl");
        EntityManager entityManager = JPAUtility.getEntityManager();
        LoginfoDAOImpl logDao = new LoginfoDAOImpl();
        ActivityDAOImpl activityDAO = new ActivityDAOImpl();
        List<Loginfo> userLogs = new ArrayList<>();
        Loginfo loginfo = new Loginfo();
        Activity act = new Activity();

        System.out.println("Получить список пользователей из БД (без ролей)");
        userLogs = logDao.getAllLogInfo(entityManager);
        logDao.printManyLogInfo(userLogs);

        System.out.println("Получить конкретного пользователя (с его ролями) из БД;");
        loginfo = logDao.getLogInfoById(entityManager, 1);
        logDao.printLogInfoWithRoles(loginfo);

        System.out.println("Получить список пользователей по конкретной роли");
        act = activityDAO.getActivityById(entityManager, 4);
        System.out.println(act.getLoginfos().toString());

        System.out.println("Добавить нового пользователя с ролями в БД");
        loginfo = prepareNewLoginfo(prepareNewActivity(entityManager));
        logDao.createLogInfo(entityManager, loginfo);

        System.out.println("Редактирование существующего пользователя в БД");
        loginfo = prepareNewLoginfo(prepareNewActivity(entityManager));
        logDao.updateLogInfoById(entityManager, 4, loginfo);
        System.out.println("Получить конкретного пользователя (с его ролями) из БД;");
        loginfo = logDao.getLogInfoById(entityManager, 4);
        logDao.printLogInfoWithRoles(loginfo);

        System.out.println("Удалить пользователя в БД");
        logDao.deleteLogInfoById(entityManager, 9);
        System.out.println("Получить список пользователей из БД (без ролей)");
        userLogs = logDao.getAllLogInfo(entityManager);
        logDao.printManyLogInfo(userLogs);

        System.out.println("Добавить новую роль");
        activityDAO.createActivity(entityManager);

        entityManager.close();
        JPAUtility.close();
    }

    public static Set<Activity> prepareNewActivity(EntityManager entityManager) {
        Activity activity = new Activity();
        Set<Activity> activ = new HashSet<>();

        entityManager.getTransaction().begin();
        activity = entityManager.find(Activity.class, new Integer(2));
        entityManager.getTransaction().commit();
        activ.add(activity);

        entityManager.getTransaction().begin();
        activity = entityManager.find(Activity.class, new Integer(4));
        entityManager.getTransaction().commit();
        activ.add(activity);
/*
        entityManager.getTransaction().begin();
        activity = entityManager.find(Activity.class, new Integer(3));
        entityManager.getTransaction().commit();
        activ.remove(activity); */

        System.out.println("Подготовлены роли для добавления/ изменения пользователя");
        System.out.println(activ);
        return activ;
    }
    public static Loginfo prepareNewLoginfo(Set<Activity> activity) {
        Loginfo loginfoToAdd = new Loginfo();
        loginfoToAdd.setLogInfoId(4);
        //loginfoToAdd.setLogName("Новый_Тестировщик");
        loginfoToAdd.setLogName("Измененный_Тестировщик");
        //loginfoToAdd.setLogIn("loginAdded_1");
        loginfoToAdd.setLogIn("loginAdded_updated");
        //loginfoToAdd.setPassWord("passwordAdded_1");
        loginfoToAdd.setPassWord("passwordAdded_updated");
        loginfoToAdd.setActivities(activity);

        System.out.println("Подготовлены данные для добавления/ изменения пользователя");
        System.out.println(loginfoToAdd);
        return loginfoToAdd;
    }

}
