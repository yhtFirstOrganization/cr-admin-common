package com.crAdmin.bean;

public class Region {
	private String regionId;

	private String pid;

	private String name;

	private String pname;

	private Integer status;

	private String ptree;

	private String pnameTree;

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname == null ? null : pname.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPtree() {
		return ptree;
	}

	public void setPtree(String ptree) {
		this.ptree = ptree == null ? null : ptree.trim();
	}

	public String getPnameTree() {
		return pnameTree;
	}

	public void setPnameTree(String pnameTree) {
		this.pnameTree = pnameTree == null ? null : pnameTree.trim();
	}
}