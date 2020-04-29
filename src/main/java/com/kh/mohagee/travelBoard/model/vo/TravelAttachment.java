package com.kh.mohagee.travelBoard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class TravelAttachment implements Serializable {
   
   private int bFileNo;
   private int bNo;
   private String bFileName;
   private String bFileType;
   private String bFilePath;
   private Date bFileDate;
   private int bFileLevel;
   private String bFileStatus;
   
   public TravelAttachment() {
      super();
   }

   public TravelAttachment(int bFileNo, int bNo, String bFileName, String bFilePath, Date bFileDate, int bFileLevel,
         String bFileStatus) {
      super();
      this.bFileNo = bFileNo;
      this.bNo = bNo;
      this.bFileName = bFileName;
      this.bFilePath = bFilePath;
      this.bFileDate = bFileDate;
      this.bFileLevel = bFileLevel;
      this.bFileStatus = bFileStatus;
   }


   public int getbFileNo() {
      return bFileNo;
   }


   public void setbFileNo(int bFileNo) {
      this.bFileNo = bFileNo;
   }


   public int getbNo() {
      return bNo;
   }


   public void setbNo(int bNo) {
      this.bNo = bNo;
   }


   public String getbFileName() {
      return bFileName;
   }


   public void setbFileName(String bFileName) {
      this.bFileName = bFileName;
   }


   public String getbFilePath() {
      return bFilePath;
   }


   public void setbFilePath(String bFilePath) {
      this.bFilePath = bFilePath;
   }


   public Date getbFileDate() {
      return bFileDate;
   }


   public void setbFileDate(Date bFileDate) {
      this.bFileDate = bFileDate;
   }


   public int getbFileLevel() {
      return bFileLevel;
   }


   public void setbFileLevel(int bFileLevel) {
      this.bFileLevel = bFileLevel;
   }


   public String getbFileStatus() {
      return bFileStatus;
   }


   public void setbFileStatus(String bFileStatus) {
      this.bFileStatus = bFileStatus;
   }
   
   
   public String getbFileType() {
      return bFileType;
   }


   public void setbFileType(String bFileType) {
      this.bFileType = bFileType;
   }

   @Override
   public String toString() {
      return "TravelAttachment [bFileNo=" + bFileNo + ", bNo=" + bNo + ", bFileName=" + bFileName + ", bFileType="
            + bFileType + ", bFilePath=" + bFilePath + ", bFileDate=" + bFileDate + ", bFileLevel=" + bFileLevel
            + ", bFileStatus=" + bFileStatus + "]";
   }
}