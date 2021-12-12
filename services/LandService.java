package edu.vinaenter.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.vinaenter.daos.LandDAO;
import edu.vinaenter.models.Land;

@Service
public class LandService {
	@Autowired
	private LandDAO landDAO;
	
	public List<Land> selectAll() {
		return landDAO.selectAll();
	}
	public List<Land> selectSlider(){
		return landDAO.selectSlider();
	}
	public int countAll() {
		return landDAO.countAll();
	}
	public List<Land> selectAllDateNew() {
		return landDAO.selectAllDateNew();
	}
	public List<Land> selectAllCountView() {
		return landDAO.selectAllCountView();
	}
	public List<Land> selectByCid(int cid){
		return landDAO.selectByCid(cid);
	}
	public Land selectByID(int id) {
		return landDAO.selectByID(id);
	}
	public int add(Land t, MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException  {
		Timestamp date_create = new Timestamp(new Date().getTime());
		t.setDate_create(date_create);
		String dirUpload = request.getServletContext().getRealPath("/WEB-INF/resources/upload");
		String fileName = multipartFile.getOriginalFilename();
		// Rename file
		fileName = FilenameUtils.getBaseName(fileName) + "-" + System.nanoTime() + "."
				+ FilenameUtils.getExtension(fileName);
		File dir = new File(dirUpload);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String filePath = dirUpload + File.separator + fileName;
		multipartFile.transferTo(new File(filePath));
		t.setPicture(fileName);
		System.out.println(dirUpload);
		return landDAO.add(t);
	}
	public int del(int id, HttpServletRequest request){
			String dirUpload = request.getServletContext().getRealPath("/WEB-INF/resources/upload");
			if (!landDAO.selectByID(id).getPicture().isEmpty()) {
				String filePath = dirUpload + File.separator + landDAO.selectByID(id).getPicture();
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
			}
		return landDAO.del(id);
	}
}
