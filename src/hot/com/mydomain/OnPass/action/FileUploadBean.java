package com.mydomain.OnPass.action;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.text.AbstractDocument.Content;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mydomain.OnPass.model.File;
import com.mydomain.OnPass.model.User;
import com.sun.medialib.mlib.Image;

@Name("fileUploadBean")
@Scope(ScopeType.SESSION)
public class FileUploadBean{
	@Logger
	private Log log;
	ArrayList<File> files = new ArrayList<File>();
    private int uploadsAvailable = 1;
    private boolean autoUpload = false;
    private boolean useFlash = false;
    private int width;
    private int height;
  
    @In("entityManager")
	EntityManager em;
    public int getSize() {
        if (getFiles().size()>0){
            return getFiles().size();
        }else 
        {
            return 0;
        }
    }
    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer)object).getData());
    }
    public FileUploadBean() {
    }

 
    public void listener(UploadEvent event, User user) throws Exception{
        UploadItem item = event.getUploadItem();
        ImageIcon i = new ImageIcon(item.getData());
        File file = new File();
       // log.info(item.getFile().length());
        file.setLength(item.getData().length);
        log.info(file.getLength());
        file.setName(item.getFileName());
        log.info(file.getName());
        file.setData(item.getData());
        file.setHeight(i.getIconHeight());
        file.setWidth(i.getIconWidth());
        file.setUser(user);
        em.persist(file);
        files.add(file);
        uploadsAvailable--;
    }  
      
    public String clearUploadData() {
        files.clear();
        setUploadsAvailable(1);
        return null;
    }
    
    public long getTimeStamp(){
        return System.currentTimeMillis();
    }
    
    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) { 
        this.files = files;
    }

    public int getUploadsAvailable() {
        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {
        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }
    public void rePaint(OutputStream stream, Object object) throws IOException {
    //	List<File> files = em.createQuery("select f from File f where f.id = 373").getResultList();
    	Authenticator auth =(Authenticator)Component.getInstance("authenticator");
    	setWidth(500);
    	setHeight(300);
   
        stream.write(auth.getUserForPhishing().getFile().getData());
    	
        
    }
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String StyledImage(){
		return "width: "+getWidth()+"px; height:"+getHeight()+"px;";
	}
    

}