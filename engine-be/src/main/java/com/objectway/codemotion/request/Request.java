package com.objectway.codemotion.request;

import java.io.Serializable;

import com.objectway.codemotion.beans.Region;
import com.objectway.codemotion.beans.RiskProfile;
import com.objectway.codemotion.beans.TimeHorizon;

import lombok.Data;

@Data
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RiskProfile riskProfile;
	private TimeHorizon timeHorizon;
	private Region region;
	
}
