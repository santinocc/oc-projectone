package com.oc.projectone.model.responses;

import java.util.List;

import com.oc.projectone.model.firestations.FireInfo;

public class FireInfoResponse {
	
	public final String station;
	public final List<FireInfo> fireInfos;
	
	public static class FireInfoBuilder {
		private String station;
		private List<FireInfo> fireInfos;
		
		public FireInfoBuilder() {
		}
		
		public FireInfoBuilder station(String station) {
			this.station = station;
		    return this;
		}
		
		public FireInfoBuilder fireInfos(List<FireInfo> fireInfos) {
			this.fireInfos = fireInfos;
		    return this;
		}
		

		
		public FireInfoResponse build() {
			return new FireInfoResponse(station, fireInfos);
		}
	}
	
	public FireInfoResponse(String station, List<FireInfo> fireInfos) {
		this.station = station;
		this.fireInfos = fireInfos;
	}

}
