package br.puc.rio.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
		Collections.sort(builds);
		return builds;
	}
	
	public boolean isDowngrade() {
		return getDowngradeBuild().isPresent();
	}
	
	public Optional<Build> getDowngradeBuild() {
		for (Build build : builds) 
			if(build.isDowngrade())
				return Optional.of(build);
		
		return Optional.empty();
	}

}
