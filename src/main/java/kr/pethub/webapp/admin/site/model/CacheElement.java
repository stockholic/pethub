package kr.pethub.webapp.admin.site.model;

import java.util.Date;

public class CacheElement{

	private String cacheNm;
	private String cacheKey;
	private long hitCount;
	private Date creationTime;
	private Date expirationTime;
	private Date lastAccessTime;
	private boolean isExpired;
	
	public String getCacheNm() {
		return cacheNm;
	}
	public void setCacheNm(String cacheNm) {
		this.cacheNm = cacheNm;
	}
	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	public long getHitCount() {
		return hitCount;
	}
	public void setHitCount(long hitCount) {
		this.hitCount = hitCount;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public boolean isExpired() {
		return isExpired;
	}
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
	
}
