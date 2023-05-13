import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "log_info")
public class Loginfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_info_id")
    private int logInfoId;
    @Column(name = "log_name", length = 50, nullable = false)
    private String logName;
    @Column(name = "log_in", length = 50, nullable = false)
    private String logIn;
    @Column(name = "pass_word", length = 50, nullable = false)
    private String passWord;
    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;
    @ManyToMany
    //@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Loginfo_Activity",
            joinColumns = { @JoinColumn(name = "log_info_id") },
            inverseJoinColumns = { @JoinColumn(name = "activity_id") }
    )
    private Set<Activity> activities = new HashSet<>();

    public Loginfo() {
    }

    @Override
    public String toString() {
        return "\nLoginfo{" +
                "logInfoId=" + logInfoId +
                ", logName='" + logName + '\'' +
                ", logIn='" + logIn + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", activities=" + activities +
                '}';
    }

    public int getLogInfoId() {
        return logInfoId;
    }

    public void setLogInfoId(int logInfoId) {
        this.logInfoId = logInfoId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
