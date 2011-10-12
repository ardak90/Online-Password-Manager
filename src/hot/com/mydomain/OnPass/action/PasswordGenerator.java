package com.mydomain.OnPass.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ardak90
 */
public class PasswordGenerator {
    private int SpecialCharactersStatus,
                UpperLowercaseLettersStatus,
                NumbersStatus,
                LengthStatus;
                
                
    
  public PasswordGenerator(String str){
  setLengthStatus(str);
  setNumbersStatus(str);
  setSpecialCharactersStatus(str);
  setUpperLowercaseLettersStatus(str);    
  }
    

    public int getLengthStatus() {
        return LengthStatus;
    }

    public void setLengthStatus(String str){        
       if(str.length()>=10){
       this.LengthStatus = 1;       
       } else{
       this.LengthStatus = 0;
       }
    }

    public int getNumbersStatus() {
        return NumbersStatus;
    }

    public void setNumbersStatus(String str) {
       Pattern p = Pattern.compile(".*?[0-9].*");
       Matcher m = p.matcher(str); 
       boolean b = m.matches();
       if(b){
      this.NumbersStatus = 1;
       }else{
       this.NumbersStatus = 0;
       }
        
    }

    public int getSpecialCharactersStatus() {
        return SpecialCharactersStatus;
    }

    public void setSpecialCharactersStatus(String str) {
       Pattern p = Pattern.compile(".*?[\\p{Punct}].*");
       Matcher m = p.matcher(str); 
       boolean b = m.matches();
       if(b){
       this.SpecialCharactersStatus = 1;
       }else{
       this.SpecialCharactersStatus = 0;
       }
    }

    public int getUpperLowercaseLettersStatus() {
        return UpperLowercaseLettersStatus;
    }

    public void setUpperLowercaseLettersStatus(String str) {
        Pattern p = Pattern.compile(".*?[A-Z].*");
       Matcher m = p.matcher(str); 
       boolean b = m.matches();
       int i, i2;
       if(b){
       i = 1;    
       } else {
       i = 0;
       }
       p = Pattern.compile(".*?[a-z].*");
       m = p.matcher(str); 
       b = m.matches();
       if(b){
       i2 = 1;    
       } else {
       i2 = 0;
       }
       if((i==1)&&(i2==1)){
        this.UpperLowercaseLettersStatus = 1;
       }
       else{
        this.UpperLowercaseLettersStatus = 0;   
       } 
    }
     
    public int getPasswordStrength(String str){
    setLengthStatus(str);
    setNumbersStatus(str);
    setSpecialCharactersStatus(str);
    setUpperLowercaseLettersStatus(str);       
    int status=0;
    int calculate = getLengthStatus()+getNumbersStatus()+getSpecialCharactersStatus()+getUpperLowercaseLettersStatus();     
        
    if(calculate==0){
    status = 1;
    }
    if(calculate==1){
    status = 2;
    }
    if(calculate==2){
    status = 3;
    }
    if(calculate==3){
    status = 4;
    }
    if(calculate==4){
    status = 5;
    }
    return status;
    }
   
}
