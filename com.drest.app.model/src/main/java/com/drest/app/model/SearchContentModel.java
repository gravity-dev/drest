package com.drest.app.model;

import java.util.Date;

public class SearchContentModel implements IModel {

	private String urlIndex;

	private String tags;

	private String content;

	private String url;

	private Date creatioDate;

	private String crawlerType = "S";

	public String getUrlIndex() {
		return urlIndex;
	}

	public void setUrlIndex(String index) {
		this.urlIndex = index;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCrawlerType() {
		return crawlerType;
	}

	public void setCrawlerType(String crawler_type) {
		this.crawlerType = crawler_type;
	}

	public Date getCreationDate() {
		return creatioDate;
	}

	public void setCreationDate(Date creation_date) {
		this.creatioDate = creation_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getModelName() {
		return ModelConstants.MODEL_SEARCH_CONTENT;
	}

}
