package br.puc.rio.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Class used to deserialize upgrade-configuration.xml,
 * XML used for developer to keep the evolution of builds.
 */
@XStreamAlias("UpgradeConfiguration")
public class UpgradeConfiguration {
	
	@XStreamImplicit(itemFieldName="build")
	List<Build> builds;
	
	/**
	 * @param List of Builds.
	 */
	public UpgradeConfiguration(List<Build> builds) {
		this.builds = builds;
	}
		
	/**
	 ** @return List of Builds
	 */
	public List<Build> getBuilds() {
		Collections.sort(builds);
		return builds;
	}
	
	/**
	 * @return true if the is downgrade build and false otherwise. 
	 */
	public boolean isDowngrade() {
		return getDowngradeBuild().isPresent();
	}
	
	/**
	 * Note, builds are in the same ordering as upgrade-configuration.xml, 
	 * if there is more than one downgrade build, 
	 * the first downgrade build will be returned. 
	 * @return downgrade buid. 
	 */
	public Optional<Build> getDowngradeBuild() {
		for (Build build : builds) 
			if(build.isDowngrade())
				return Optional.of(build);
		
		return Optional.empty();
	}

}
