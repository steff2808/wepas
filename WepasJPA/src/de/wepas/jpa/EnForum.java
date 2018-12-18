package de.wepas.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the en_forum database table.
 * 
 */
@Entity
@NamedQueries(
		{ @NamedQuery(name = "EnForum.findAll",  query = "SELECT a FROM EnForum a ORDER BY a.idForum DESC")})
@Table(name = "en_forum")
public class EnForum implements Serializable
{
	private static final long serialVersionUID = 1724559554137904622L;

	@Id
	@Column(name = "id_forum")
	private int idForum;

	@Column(name = "at_forum_text1")
	private String atForumText1;

	@Column(name = "at_forum_text2")
	private String atForumText2;

	@Column(name = "at_forum_type")
	private String atForumType;

	@Column(name = "id_tipper")
	private int idTipper;

	public EnForum()
	{
	}

	public int getIdForum()
	{
		return this.idForum;
	}

	public void setIdForum(int idForum)
	{
		this.idForum = idForum;
	}

	public String getAtForumText1()
	{
		return this.atForumText1;
	}

	public void setAtForumText1(String atForumText1)
	{
		this.atForumText1 = atForumText1;
	}

	public String getAtForumText2()
	{
		return this.atForumText2;
	}

	public void setAtForumText2(String atForumText2)
	{
		this.atForumText2 = atForumText2;
	}

	public String getAtForumType()
	{
		return this.atForumType;
	}

	public void setAtForumType(String atForumType)
	{
		this.atForumType = atForumType;
	}

	public int getIdTipper()
	{
		return this.idTipper;
	}

	public void setIdTipper(int idTipper)
	{
		this.idTipper = idTipper;
	}

}