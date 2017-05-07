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
	private int iid;

	/**
	 * 成员1id
	 */
	private int uid1;

	/**
	 * 成员2id
	 */
	private int uid2;

	/**
	 * 成员3id
	 */
	private int uid3;

	/**
	 * 成员4id
	 */
	private int uid4;

	/**
	 * 成员5id
	 */
	private int uid5;

	/**
	 * 成员6id
	 */
	private int uid6;

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getUid1() {
		return uid1;
	}

	public void setUid1(int uid1) {
		this.uid1 = uid1;
	}

	public int getUid2() {
		return uid2;
	}

	public void setUid2(int uid2) {
		this.uid2 = uid2;
	}

	public int getUid3() {
		return uid3;
	}

	public void setUid3(int uid3) {
		this.uid3 = uid3;
	}

	public int getUid4() {
		return uid4;
	}

	public void setUid4(int uid4) {
		this.uid4 = uid4;
	}

	public int getUid5() {
		return uid5;
	}

	public void setUid5(int uid5) {
		this.uid5 = uid5;
	}

	public int getUid6() {
		return uid6;
	}

	public void setUid6(int uid6) {
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