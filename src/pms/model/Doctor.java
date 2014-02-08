package pms.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the doctor database table.
 * 
 */
@Entity
@NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_user_name")
	private String loginUserName;

	@Column(name = "address_line1")
	private String addressLine1;

	@Column(name = "address_line2")
	private String addressLine2;

	@Column(name = "address_line3")
	private String addressLine3;

	@Column(name = "alternate_phone")
	private String alternatePhone;

	private String city;

	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_changed_by")
	private String lastChangedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_changed_on")
	private Date lastChangedOn;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	private String mobile;

	private String phone;

	private int pin;

	private String qualification;

	@Column(name = "registered_by")
	private byte[] registeredBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registered_on")
	private Date registeredOn;

	@Column(name = "registration_number")
	private String registrationNumber;

	private String sex;

	private String state;

	// bi-directional one-to-one association to Login
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="login_user_name", referencedColumnName="user_name")
	private Login login;

	// bi-directional many-to-one association to Patient
	@OneToMany(mappedBy = "doctor")
	private List<Patient> patients;

	// bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy = "doctor")
	private List<Prescription> prescriptions;

	public Doctor() {
	}

	public String getLoginUserName() {
		return this.loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return this.addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAlternatePhone() {
		return this.alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastChangedBy() {
		return this.lastChangedBy;
	}

	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

	public Date getLastChangedOn() {
		return this.lastChangedOn;
	}

	public void setLastChangedOn(Date lastChangedOn) {
		this.lastChangedOn = lastChangedOn;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPin() {
		return this.pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public byte[] getRegisteredBy() {
		return this.registeredBy;
	}

	public void setRegisteredBy(byte[] registeredBy) {
		this.registeredBy = registeredBy;
	}

	public Date getRegisteredOn() {
		return this.registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setDoctor(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setDoctor(null);

		return patient;
	}

	public List<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Prescription addPrescription(Prescription prescription) {
		getPrescriptions().add(prescription);
		prescription.setDoctor(this);

		return prescription;
	}

	public Prescription removePrescription(Prescription prescription) {
		getPrescriptions().remove(prescription);
		prescription.setDoctor(null);

		return prescription;
	}

	@PrePersist
	public void beforeCreate() {
		Date now = new Date();
		this.registeredOn = now;
		this.lastChangedOn = now;
	}

	@PreUpdate
	public void beforeUpdate() {
		Date now = new Date();
		this.lastChangedOn = now;
	}
}