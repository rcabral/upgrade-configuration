package br.puc.rio.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("UpgradeConfiguration")
public class UpgradeConfiguration {
	
	@XStreamImplicit(itemFieldName="build")
	List<Build> builds;

	public UpgradeConfiguration(List<Build> builds) {
		this.builds = builds;
	}

	public List<Build> getBuilds() {
		return builds;
	}

}
