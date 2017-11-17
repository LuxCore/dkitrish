package ru.job4j.tracker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Element of tracking system that can be task, bug or someone else.
 */
public class Item {
	/**
	 * Identifier.
	 */
	private String id;

	/**
	 * Proposal`s name.
	 */
	private String name;

	/**
	 * Proposal`s description.
	 */
	private String desc;

	/**
	 * Date and time of created proposal in long presentation.
	 */
	private long created;

	/**
	 * Comments to proposal.
	 */
	private String[] comments;

	/**
	 * Inner counter for array of comments.
	 */
	private int commentInsertPosition;

	/**
	 * Constructs an item with given name and description. Date and time
	 * are taken from current system clock in milliseconds.
	 *
	 * @param name Name of item.
	 * @param desc Description of item.
	 */
	public Item(String name, String desc) {
		this(name, desc, System.currentTimeMillis());
	}

	/**
	 * Constructs an item using name, desc and date/time.
	 *
	 * @param name Name of item.
	 * @param desc Description of item.
	 * @param created Date and time of item creation in milliseconds.
	 */
	public Item(String name, String desc, long created) {
		this.name = name;
		this.desc = desc;
		this.created = created;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getCreated() {
		return this.created;
	}

	public void setComments(String[] comments) {
		this.comments = comments;
	}

	public String[] getComments() {
		return comments;
	}

	public void addComment(String comment) {
		if (commentInsertPosition < this.comments.length) {
			this.comments[commentInsertPosition++] = comment;
		}
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("[id = ").append(id).append(" | ")
			.append("name = ").append(name).append(" | ")
			.append("desc = ").append(desc).append(" | ")
			.append("created = ").append(sdf.format(new Date(created))).append("]");
		return sb.toString();
	}
}
