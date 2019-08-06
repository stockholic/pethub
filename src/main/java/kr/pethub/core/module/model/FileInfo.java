package kr.pethub.core.module.model;

public class FileInfo {
	
	private Integer fileSrl;
	private Integer fileRefSrl;
	private String filePath;
	private String fileSysNm;
	private String fileRealNm;
	private long fileSize;
	private String fileTp;
	
	public Integer getFileSrl() {
		return fileSrl;
	}
	public void setFileSrl(Integer fileSrl) {
		this.fileSrl = fileSrl;
	}
	public Integer getFileRefSrl() {
		return fileRefSrl;
	}
	public void setFileRefSrl(Integer fileRefSrl) {
		this.fileRefSrl = fileRefSrl;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSysNm() {
		return fileSysNm;
	}
	public void setFileSysNm(String fileSysNm) {
		this.fileSysNm = fileSysNm;
	}
	public String getFileRealNm() {
		return fileRealNm;
	}
	public void setFileRealNm(String fileRealNm) {
		this.fileRealNm = fileRealNm;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileTp() {
		return fileTp;
	}
	public void setFileTp(String fileTp) {
		this.fileTp = fileTp;
	}
	
}
