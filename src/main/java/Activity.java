import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int activityId;
    @Column(name = "activity_role", length = 50, nullable = false)
    private String activityRole;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Loginfo_Activity",
            joinColumns = { @JoinColumn(name = "activity_id") },
            inverseJoinColumns = { @JoinColumn(name = "log_info_id") }
    )
    private Set<Loginfo> loginfos = new HashSet<>();

    public Activity() {
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityRole='" + activityRole + '\'' +
                '}';
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityRole() {
        return activityRole;
    }

    public void setActivityRole(String activityRole) {
        this.activityRole = activityRole;
    }

    public Set<Loginfo> getLoginfos() {
        return loginfos;
    }

    public void setLoginfos(Set<Loginfo> loginfos) {
        this.loginfos = loginfos;
    }
}
