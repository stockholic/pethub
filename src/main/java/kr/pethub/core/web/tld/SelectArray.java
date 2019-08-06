package kr.pethub.core.web.tld;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class SelectArray extends TagSupport{

	private String id;
	private String name;
	private String[] key;
	private String[] value;
	private String selectKey;
	private String onChange;
	private String style;
	private String clasz;
	private String checkMsg;
	private String all;

	public int doStartTag(){
		
		JspWriter out = pageContext.getOut();
		
		StringBuffer dataList = new StringBuffer();

		String id = (this.id != null) ? " id='" + this.id + "'" : "";
		String name = (this.name != null) ? " name='" + this.name + "'" : "";
		if(this.id != null && this.name == null)	name = " name='" + this.id + "'";
		
		String style = (this.style != null) ? " style='" + this.style + "'" : "";
		String onChange = (this.onChange != null) ? " onChange='" + this.onChange + "'" : "";
		String clasz = (this.clasz != null) ? " class='" + this.clasz + "'" : "";
		String checkMsg = (this.checkMsg != null) ? " checkMsg='" + this.checkMsg + "'" : "";
		String all = (this.all != null) ? "<option value=''>" + this.all + "</option>" : "";
		dataList.append("<select " + name + id + onChange  + clasz + style + checkMsg + ">");
		dataList.append(all);
		for(int i = 0; i < this.key.length; i++){
			String selected = this.key[i].equals(this.selectKey) ? " selected" : "";
			dataList.append("<option value='" + this.key[i] + "'" + selected + ">" + this.value[i] + "</option>");
		}
		dataList.append("</select>");
		
		try {
			out.println(dataList);
		} catch(IOException ioe){          
			ioe.printStackTrace(); 
		}
		return SKIP_BODY; 
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setKey(String[] key) {
		this.key = key;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void setClasz(String clasz) {
		this.clasz = clasz;
	}
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}
	public void setAll(String all) {
		this.all = all;
	}

}
