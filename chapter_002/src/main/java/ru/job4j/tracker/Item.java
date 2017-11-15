package ru.job4j.tracker;

// import java.util.UUID;

/**
 * Element of tracking system that can be task, bug or someone else.
 */
public class Item {
	/**
	 * Identifier.
	 */
//	private UUID id;
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

	public Item(String name, String desc) {
		this(name, desc, System.currentTimeMillis());
	}

	public Item(String name, String desc, long created) {
		// this.id = UUID.randomUUID();
		this.name = name;
		this.desc = desc;
		this.created = created;
	}

/*	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return this.id;
	}*/

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
}
