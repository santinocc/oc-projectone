package com.oc.projectone.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FireStation {
	
	private Set<String> addresses = new HashSet<>();
	private String stationNumber;
	
	public FireStation(String stationNumber) {
		this.stationNumber = stationNumber;
	}
	
	public FireStation addAddress(String address) {
		addresses.add(address);
		return this;
	}
	
	public String getStationNumber() {
		return stationNumber;
	}
	
	public Set<String> getAddresses() {
		return addresses.stream().collect(Collectors.toSet());
	}
	
	public void setAddresses(Set<String> newAddresses) {
		this.addresses = newAddresses;
	}
	
	@Override
	public String toString() {
		return stationNumber.concat(": ") + String.join(", ", addresses);
	}
}
