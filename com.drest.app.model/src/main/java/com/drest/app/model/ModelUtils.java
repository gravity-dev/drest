package com.drest.app.model;

import java.util.ArrayList;
import java.util.List;

public class ModelUtils {

	private static final String SPACE = " ";

	public static String getTagsAsString(List<String> tags) {
		if (tags == null)
			return SPACE;
		StringBuffer sbuff = new StringBuffer();
		for (String tag : tags) {
			sbuff.append(tag);
			sbuff.append(SPACE);
		}

		return sbuff.toString();
	}

	public static List<String> getTagsAsList(String tags) {
		if (tags == null)
			return null;

		List<String> tagsList = new ArrayList<String>();

		for (String tag : tags.split(SPACE)) {
			if (tag != null && tag.trim().length() > 0)
				tagsList.add(tag);
		}
		return tagsList;
	}

}
