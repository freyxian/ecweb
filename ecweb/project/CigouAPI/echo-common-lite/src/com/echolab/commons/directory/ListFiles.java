package com.echolab.commons.directory;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.ArrayList;


public class ListFiles 
{
 

 public List<File> getFiles( String path, String surfix ) {
	 
	 
	 File folder = new File(path);
	 FileFilter filter = new SuffixNameFilter(surfix);
	 
	 
	 File[] files = folder.listFiles(filter);
	 
	 
	 List<File> fileList = new ArrayList<File>();
	 
	 for (int i = 0, j=files.length; i < j; i++) 
	 {
		 fileList.add(files[i]);
	 }
	 return (fileList);
	 
 }
 

} // end of class 

