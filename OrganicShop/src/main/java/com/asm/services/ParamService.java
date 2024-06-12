package com.asm.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	public String getString(String name, String defaultValue) {
		String value = request.getParameter(name);
		return (value != null) ? value : defaultValue;
	}
	public int getInt(String name, int defaultValue) {
		String value = request.getParameter(name);
		try {
			return (value != null) ? Integer.parseInt(value) : defaultValue;
		}catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	public double getDouble(String name, double defaultValue) {
		String value = request.getParameter(name);
		try {
			return (value != null) ? Double.parseDouble(value) : defaultValue;
		}catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = request.getParameter(name);
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}
	public Date getDate(String name, String pattern) {
		String value = request.getParameter(name);
		if(value == null) 
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(value);
		}catch (ParseException e) {
			throw new RuntimeException("Date format error", e);
		}
	}
	public File save(MultipartFile file, String path) {
		if(file.isEmpty()) {
			return null;
		}
		File dir = new File(request.getServletContext().getRealPath(path));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		try {
			File saveFile = new File(dir, file.getOriginalFilename());
			file.transferTo(saveFile);
			return saveFile;
		}catch (IOException e) {
			throw new RuntimeException("File save error",e);
		}
	}
}
