package pms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prescriptions database table.
 * 
 */
@Entity
@Table(name="prescriptions")
@NamedQuery(name="Prescription.findAll", query="SELECT p FROM Prescription p")
public class Prescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="prescription_img")
	private byte[] prescriptionImg;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="processed_by")
	private Date processedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="processed_on")
	private Date processedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="uploaded_on")
	private Date uploadedOn;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_user_name")
	private Doctor doctor;

	//bi-directional one-to-one association to Visit
	@OneToOne(mappedBy="prescription", fetch=FetchType.LAZY)
	private Visit visit;
	
	@Column(name= "original_img_name")
	private String originalFileName;
	@Column(name= "mime_type")
	private String mimeType;

	public Prescription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPrescriptionImg() {
		return this.prescriptionImg;
	}

	public void setPrescriptionImg(byte[] prescriptionImg) {
		this.prescriptionImg = prescriptionImg;
	}

	public Date getProcessedBy() {
		return this.processedBy;
	}

	public void setProcessedBy(Date processedBy) {
		this.processedBy = processedBy;
	}

	public Date getProcessedOn() {
		return this.processedOn;
	}

	public void setProcessedOn(Date processedOn) {
		this.processedOn = processedOn;
	}

	public Date getUploadedOn() {
		return this.uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	@PrePersist
	public void beforeCreate(){
		this.uploadedOn = new Date();
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}