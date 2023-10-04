package javaProject8;

public class MembersVO {
	private int memberID;
	private String name;
	private String password;
	private String contact;
	private String address;
	
	int getMemberID() {
		return memberID;
	}
	void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getPassword() {
		return password;
	}
	void setPassword(String password) {
		this.password = password;
	}
	String getContact() {
		return contact;
	}
	void setContact(String contact) {
		this.contact = contact;
	}
	String getAddress() {
		return address;
	}
	void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "MembersVO [memberID=" + memberID + ", name=" + name + ", password=" + password + ", contact=" + contact
				+ ", address=" + address + "]";
	}
}
