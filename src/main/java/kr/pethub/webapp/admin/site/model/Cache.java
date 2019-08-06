package kr.pethub.webapp.admin.site.model;

public class Cache{

	private String cacheNm;
	private String cacheKey;
	private long cacheSize;
	private long cacheHitCount;
	private long cacheExpiredCount;
	private long cacheRemoveCount;
	private long memorySize;
	
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
	public long getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}
	public long getCacheHitCount() {
		return cacheHitCount;
	}
	public void setCacheHitCount(long cacheHitCount) {
		this.cacheHitCount = cacheHitCount;
	}
	public long getCacheExpiredCount() {
		return cacheExpiredCount;
	}
	public void setCacheExpiredCount(long cacheExpiredCount) {
		this.cacheExpiredCount = cacheExpiredCount;
	}
	public long getCacheRemoveCount() {
		return cacheRemoveCount;
	}
	public void setCacheRemoveCount(long cacheRemoveCount) {
		this.cacheRemoveCount = cacheRemoveCount;
	}
	public long getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(long memorySize) {
		this.memorySize = memorySize;
	}
	
}
