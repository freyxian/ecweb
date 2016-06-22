package com.echolab.commons.directory;

import java.io.File;
import java.io.FileFilter;

public class SuffixNameFilter implements FileFilter {
	
	private String suffix="";
	
	public  SuffixNameFilter( String v ) {
		
		suffix=v;
		return;
	}

	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		
		if ( pathname == null ) return false;
		if ( ! pathname.isFile() ) return false;
		
		String fileName=pathname.getName();
        if ( fileName.toLowerCase().endsWith(suffix) ) return true;
		
		return false;
	}

}
