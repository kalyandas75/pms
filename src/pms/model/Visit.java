package pms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the visit database table.
 * 
 */
@Entity
@NamedQuery(name="Visit.findAll", query="SELECT v FROM Visit v")
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_visit")
	private Date dateOfVisit;

	private String diagonosis;

	private int diastolic;

	private String medicine;

	@Column(name="precription_id")
	private int precriptionId;

	private int pulse;

	private String symptoms;

	private int systolic;

	private BigDecimal weight;


	//bi-directional many-to-one association to Patient
	@ManyToOne(fetch=FetchType.LAZY)
	private Patient patient;

	//bi-directional one-to-one association to Prescription
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", referencedColumnName="visit_id")
	private Prescription prescription;

	public Visit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfVisit() {
		return this.dateOfVisit;
	}

	public void setDateOfVisit(Date dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getDiagonosis() {
		return this.diagonosis;
	}

	public void setDiagonosis(String diagonosis) {
		this.diagonosis = diagonosis;
	}

	public int getDiastolic() {
		return this.diastolic;
	}

	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}

	public String getMedicine() {
		return this.medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public int getPrecriptionId() {
		return this.precriptionId;
	}

	public void setPrecriptionId(int precriptionId) {
		this.precriptionId = precriptionId;
	}

	public int getPulse() {
		return this.pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public String getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public int getSystolic() {
		return this.systolic;
	}

	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

}