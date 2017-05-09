package com.yiyexy.model.car;

/**
 * <p>Crete on 2017/05/07</p>
 *
 * @author stormma
 *
 * @description: <p>拼车信息的成员 model</p>
 */
public class Member {

	/**
	 * 拼车信息 id
	 */
	private Integer iid;

	/**
	 * 成员1id
	 */
	private Integer uid1;

	/**
	 * 成员2id
	 */
	private Integer uid2;

	/**
	 * 成员3id
	 */
	private Integer uid3;

	/**
	 * 成员4id
	 */
	private Integer uid4;

	/**
	 * 成员5id
	 */
	private Integer uid5;

	/**
	 * 成员6id
	 */
	private Integer uid6;

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public Integer getUid1() {
		return uid1;
	}

	public void setUid1(Integer uid1) {
		this.uid1 = uid1;
	}

	public Integer getUid2() {
		return uid2;
	}

	public void setUid2(Integer uid2) {
		this.uid2 = uid2;
	}

	public Integer getUid3() {
		return uid3;
	}

	public void setUid3(Integer uid3) {
		this.uid3 = uid3;
	}

	public Integer getUid4() {
		return uid4;
	}

	public void setUid4(Integer uid4) {
		this.uid4 = uid4;
	}

	public Integer getUid5() {
		return uid5;
	}

	public void setUid5(Integer uid5) {
		this.uid5 = uid5;
	}

	public Integer getUid6() {
		return uid6;
	}

	public void setUid6(Integer uid6) {
		this.uid6 = uid6;
	}

	@Override
	public String toString() {
		return "Member{" +
				"iid=" + iid +
				", uid1=" + uid1 +
				", uid2=" + uid2 +
				", uid3=" + uid3 +
				", uid4=" + uid4 +
				", uid5=" + uid5 +
				", uid6=" + uid6 +
				'}';
	}
}