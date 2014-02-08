package pms.logic;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityTransaction;

import pms.exceptions.PMSException;
import pms.model.Doctor;
import pms.model.Prescription;

public class PrescriptionService extends Service {
	private final static Logger LOGGER = Logger
			.getLogger(PrescriptionService.class.getName());

	public void savePrescription(Doctor doctor, byte[] rx, String fileName, String mimeType) throws PMSException {
		try {
			if(doctor==null){
				LOGGER.severe("Doctor not found. Cannot be null");
				throw new PMSException(bundle.getString("system.error"));
			}
			if(rx.length<1){
				LOGGER.severe("Zero byte prescription.");
				throw new PMSException(bundle.getString("system.error"));
			}

			Prescription presc = new Prescription();
			presc.setDoctor(doctor);
			presc.setPrescriptionImg(rx);
			presc.setUploadedOn(new Date());
			presc.setOriginalFileName(fileName);
			presc.setMimeType(mimeType);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(presc);
			tx.commit();
		} finally {
			closeEM();
		}
	}

}
