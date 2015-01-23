package by.epam.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import by.epam.beans.Task;
import by.epam.consts.ConstantsError;
import by.epam.consts.ConstantsJSP;
import by.epam.dao.WorkServiceDAO;

//https://gist.github.com/keesun/1604411
//http://www.mkyong.com/spring-mvc/spring-mvc-file-upload-example/
@Controller
public class FileUploadController {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
	@Autowired
	private WorkServiceDAO workService;

	/*
	 * //================================
	 * 
	 * @RequestMapping(value = "/s/upload", method = RequestMethod.POST) public
	 * String fileUploadSubmit(@RequestParam("file") Part part){
	 * System.out.println(part.getName());
	 * System.out.println(part.getHeader("content-disposition"));
	 * System.out.println(part.getContentType());
	 * System.out.println(part.getSize()); try { part.write("sample"); } catch
	 * (IOException e) { throw new RuntimeException(e); } }
	 * //================================
	 * 
	 * @RequestMapping(value="/fileupload.do", method=RequestMethod.GET) public
	 * 
	 * @ResponseBody String provideUploadInfo() { return
	 * "You can upload a file by posting to this same URL."; }
	 * 
	 * @RequestMapping(value="/fileupload.do", method=RequestMethod.POST) public
	 * 
	 * @ResponseBody String handleFileUpload(@RequestParam("name") String name,
	 * 
	 * @RequestParam("file") MultipartFile file){ if (!file.isEmpty()) { try {
	 * byte[] bytes = file.getBytes(); BufferedOutputStream stream = new
	 * BufferedOutputStream(new FileOutputStream(new File(name)));
	 * stream.write(bytes); stream.close(); return "You successfully uploaded "
	 * + name + "!"; } catch (Exception e) { return "You failed to upload " +
	 * name + " => " + e.getMessage(); } } else { return "You failed to upload "
	 * + name + " because the file was empty."; } }
	 * //================================
	 */

	@RequestMapping(value = "/fileupload.do", method = RequestMethod.POST)
	protected String uploadProjectFile(HttpServletRequest req,
			HttpServletResponse response) {
		if (!ServletFileUpload.isMultipartContent(req)) {
			req.setAttribute(ConstantsJSP.ERROR, ConstantsJSP.FILE_ERROR);
			return "redirect:/home.do";
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		Long uid = UUID.randomUUID().getLeastSignificantBits();
		String uploadPath = req.getSession().getServletContext()
				.getRealPath("/resources")
				+ File.separator + uid + File.separator;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			String fileName = null;
			String description = null;
			int taskId = 0;
			List<FileItem> formItems = upload.parseRequest(req);
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					if (!item.isFormField()) {
						fileName = new File(item.getName()).getName();
						File storeFile = new File(uploadPath+fileName);
						item.write(storeFile);
						req.setAttribute("message",
								"Upload has been done successfully!");
					} else {
						String fieldname = item.getFieldName();
						if (fieldname.equals("description")) {
							description = item.getString();
						} else if (fieldname.equals("task_id")) {
							taskId = Integer.parseInt(item.getString());
						}
						if (fieldname.equals("description"))
							description = item.getString();
					}
				}
			}
			Task task = workService.getTaskById(taskId);
			if (task != null) {
				// save task attachment Attachment ex dao
				//uid filename description
				logger.info("uid="+uid);
				logger.info("description="+description);
				logger.info("fileName="+fileName);
				logger.info("taskId="+task.getId());
			}
		} catch (Exception ex) {
			logger.info("Error:" + ex.getMessage());
			req.setAttribute(ConstantsJSP.ERROR,
					ConstantsError.taskAttachmentError);
			return "redirect:/home.do";
		}
		//forward referer
		return "uploadDisplay";
	}

	public String fileupload(HttpServletRequest req, HttpServletResponse res) {
		logger.info(ConstantsJSP.EMPTY);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("file");
		if (file == null) {
			req.setAttribute(ConstantsJSP.ERROR, ConstantsJSP.FILE_ERROR);
			return "redirect:/home.do";
		}// "redirect:/project.do"// return "errorPage";
		String uploadDir = req.getSession().getServletContext()
				.getRealPath("/resources")
				+ "/" + req.getRemoteUser() + "/";
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		InputStream stream = null;
		OutputStream bos = null;
		try {
			stream = file.getInputStream();
			bos = new FileOutputStream(uploadDir + file.getOriginalFilename());
			int bytesRead;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// can't load file
			logger.info(e.getMessage());
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				logger.info(e.getMessage());
			}
		}

		req.setAttribute("friendlyName", file.getName());
		req.setAttribute("fileName", file.getOriginalFilename());
		req.setAttribute("contentType", file.getContentType());
		req.setAttribute("size", file.getSize() + " bytes");
		req.setAttribute("location", dirPath.getAbsolutePath() + "\\"
				+ file.getOriginalFilename());

		String link = req.getContextPath() + "/resources" + "/"
				+ req.getRemoteUser() + "/";
		req.setAttribute("link", link + file.getOriginalFilename());
		return "uploadDisplay";//
	}
}