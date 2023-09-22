package xDatabase;

public class InsaVO {
	private int idx;
	private String name;
	private int age;
	private String gender;
	private String ipsail;
	
	private String strYY;
	private String strMM;
	private String strDD;
	
	int getIdx() {
		return idx;
	}
	void setIdx(int idx) {
		this.idx = idx;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	int getAge() {
		return age;
	}
	void setAge(int age) {
		this.age = age;
	}
	String getGender() {
		return gender;
	}
	void setGender(String gender) {
		this.gender = gender;
	}
	String getIpsail() {
		return ipsail;
	}
	void setIpsail(String ipsail) {
		this.ipsail = ipsail;
	}
	
	String getStrYY() {
		return strYY;
	}
	void setStrYY(String strYY) {
		this.strYY = strYY;
	}
	String getStrMM() {
		return strMM;
	}
	void setStrMM(String strMM) {
		this.strMM = strMM;
	}
	String getStrDD() {
		return strDD;
	}
	void setStrDD(String strDD) {
		this.strDD = strDD;
	}
	
	@Override
	public String toString() {
		return "InsaVO [idx=" + idx + ", name=" + name + ", age=" + age + ", gender=" + gender + ", ipsail=" + ipsail
				+ ", strYY=" + strYY + ", strMM=" + strMM + ", strDD=" + strDD + "]";
	}

	

}
