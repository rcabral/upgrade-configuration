package br.puc.rio.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

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
	private List<Step> steps;
	private String message;
	@XStreamAsAttribute
	@XStreamConverter(value=BooleanConverter.class, booleans={false}, strings={"true", "false"})
	private boolean downgrade;
		
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
		this.downgrade = false;
	}
	
	public Build(int majorVersion, int minorVersion, int releaseVersion, String buildNumber, int buildSequence,
			List<Step> steps, String message, boolean downgrade) {
		super();
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.releaseVersion = releaseVersion;
		this.buildNumber = buildNumber;
		this.buildSequence = buildSequence;
		this.steps = steps;
		this.message = message;
		this.downgrade = downgrade;
	}
	
	public Build(int majorVersion, int minorVersion, int releaseVersion, String buildNumber, int buildSequence) {
		super();
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.releaseVersion = releaseVersion;
		this.buildNumber = buildNumber;
		this.buildSequence = buildSequence;
		this.steps = new ArrayList<>();
		this.downgrade = false;
	}
	
	public Build(int majorVersion, int minorVersion, int releaseVersion, String buildNumber, int buildSequence,
			boolean downgrade) {
		super();
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.releaseVersion = releaseVersion;
		this.buildNumber = buildNumber;
		this.buildSequence = buildSequence;
		this.steps = new ArrayList<>();
		this.downgrade = downgrade;
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
	
	public int compareTo(final BuildInformation buildInformation) {
		return new CompareToBuilder().append(majorVersion, buildInformation.getMajorVersion()).append(minorVersion, buildInformation.getMinorVersion())
				.append(releaseVersion, buildInformation.getReleaseVersion()).append(buildNumber, buildInformation.getBuildNumber())
				.append(buildSequence, buildInformation.getBuildSequence()).toComparison();
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
		return message==null?this.toString():this.message;
	}

	public boolean isDowngrade() {
		return downgrade;
	}
	
	

}
