package br.puc.rio.model;

import java.util.List;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Build implements Comparable<Build> {
	@XStreamAsAttribute
	private int majorVersion;
	@XStreamAsAttribute
	private int minorVersion;
	@XStreamAsAttribute
	private int releaseVersion;
	@XStreamAsAttribute
	private String buildNumber;
	@XStreamAsAttribute
	private int buildSequence;
	List<Step> steps;
	private String message;
		
	public Build(int majorVersion, int minorVersion, int releaseVersion, String buildNumber, int buildSequence,
			List<Step> steps, String message) {
		super();
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.releaseVersion = releaseVersion;
		this.buildNumber = buildNumber;
		this.buildSequence = buildSequence;
		this.steps = steps;
		this.message = message;
	}
	
	public Build(int majorVersion, int minorVersion, int releaseVersion, String buildNumber, int buildSequence) {
		super();
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.releaseVersion = releaseVersion;
		this.buildNumber = buildNumber;
		this.buildSequence = buildSequence;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("majorVersion", majorVersion).append("minorVersion", minorVersion)
				.append("releaseVersion", releaseVersion).append("buildNumber", buildNumber)
				.append("buildSequence", buildSequence).append("message", message).toString();
	}

	@Override
	public int compareTo(final Build otherBuild) {
		return new CompareToBuilder().append(majorVersion, otherBuild.majorVersion).append(minorVersion, otherBuild.minorVersion)
				.append(releaseVersion, otherBuild.releaseVersion).append(buildNumber, otherBuild.buildNumber)
				.append(buildSequence, otherBuild.buildSequence).toComparison();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildNumber == null) ? 0 : buildNumber.hashCode());
		result = prime * result + buildSequence;
		result = prime * result + majorVersion;
		result = prime * result + minorVersion;
		result = prime * result + releaseVersion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Build other = (Build) obj;
		if (buildNumber == null) {
			if (other.buildNumber != null)
				return false;
		} else if (!buildNumber.equals(other.buildNumber))
			return false;
		if (buildSequence != other.buildSequence)
			return false;
		if (majorVersion != other.majorVersion)
			return false;
		if (minorVersion != other.minorVersion)
			return false;
		if (releaseVersion != other.releaseVersion)
			return false;
		return true;
	}
	
	public List<Step> getSteps() {
		return steps;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public int getReleaseVersion() {
		return releaseVersion;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public int getBuildSequence() {
		return buildSequence;
	}

	public String getMessage() {
		return message;
	}


}
