package pms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_name")
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "invalid_try_count")
	private int invalidTryCount;

	@Column(name = "is_administrator")
	private boolean isAdministrator;

	@Column(name = "is_doctor")
	private boolean isDoctor;

	@Column(name = "is_locked")
	private boolean isLocked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_logged_on")
	private Date lastLoggedOn;

	@Column(name = "lock_reason")
	private String lockReason;

	@Column(name = "locked_by")
	private String lockedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "locked_on")
	private Date lockedOn;

	private String password;

	// bi-directional one-to-one association to Administrator
	@OneToOne(mappedBy = "login", fetch = FetchType.LAZY)
	private Administrator administrator;

	// bi-directional one-to-one association to Doctor
	@OneToOne(mappedBy = "login", fetch = FetchType.EAGER)
	private Doctor doctor;

	public Login() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getInvalidTryCount() {
		return this.invalidTryCount;
	}

	public void setInvalidTryCount(int invalidTryCount) {
		this.invalidTryCount = invalidTryCount;
	}

	public boolean getIsAdministrator() {
		return this.isAdministrator;
	}

	public void setIsAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public boolean getIsDoctor() {
		return this.isDoctor;
	}

	public void setIsDoctor(boolean isDoctor) {
		this.isDoctor = isDoctor;
	}

	public boolean getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Date getLastLoggedOn() {
		return this.lastLoggedOn;
	}

	public void setLastLoggedOn(Date lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
	}

	public String getLockReason() {
		return this.lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
	}

	public String getLockedBy() {
		return this.lockedBy;
	}

	public void setLockedBy(String lockedBy) {
		this.lockedBy = lockedBy;
	}

	public Date getLockedOn() {
		return this.lockedOn;
	}

	public void setLockedOn(Date lockedOn) {
		this.lockedOn = lockedOn;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@PrePersist
	public void beforeCreate() {
		this.createdOn = new Date();
		if (this.getAdministrator() != null) {
			this.isAdministrator = true;
		}
		if (this.getDoctor() != null) {
			this.isDoctor = true;
		}
	}

}