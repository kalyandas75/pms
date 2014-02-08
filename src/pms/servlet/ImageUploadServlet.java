package pms.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pms.exceptions.PMSException;
import pms.logic.PrescriptionService;
import pms.model.Login;
import pms.util.JsonUtil;

/**
 * Servlet implementation class ImageUploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger
			.getLogger(ImageUploadServlet.class.getCanonicalName());
	ResourceBundle bundle = ResourceBundle.getBundle("Messages");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		request.setCharacterEncoding("utf8");
		final Part filePart = request.getPart("rx");
		final String fileName = getFileName(filePart);

		InputStream filecontent = null;
		try {
			Login user = (Login) request.getSession().getAttribute("user");
			if (user == null) {
				throw new PMSException(bundle.getString("session.invalid"));
			}

			filecontent = filePart.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				bos.write(bytes,0,read);
			}
			LOGGER.log(Level.INFO, "File{0} being uploaded",
					new Object[] { fileName });
			PrescriptionService service = new PrescriptionService();
			service.savePrescription(user.getDoctor(), bos.toByteArray(), fileName, getMimeType(fileName));
			bos.close();
			JsonUtil.writeMessage("success",
					bundle.getString("rx.saved.success"),
					response.getOutputStream());

		} catch (PMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonUtil.writeMessage("error", e.getMessage(),
					response.getOutputStream());

		} finally {
			if (filecontent != null) {
				filecontent.close();
			}
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	
	private String getMimeType(String fileName){
		fileName = fileName.toLowerCase();
		if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")){
			return "image/jpeg";
		}else if(fileName.endsWith(".gif")){
			return "image/gif";
		}else if(fileName.endsWith(".png")){
			return "image/png";
		}
		return null;
	}

}
