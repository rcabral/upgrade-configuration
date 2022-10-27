package br.puc.rio.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * Class that implements a Build Information.
 * This class is persisted in the database to mark applied builds.
 * 
 */
@Entity
public class BuildInformation {
	@Id
	private String id;
	private int majorVersion;
	private int minorVersion;
	private int releaseVersion;
	private String buildNumber;
	private int buildSequence;
	private int steps;
	private LocalDateTime upgradeDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * Constructor Method.
	 */
	public BuildInformation() {
	}
	
	/**
	 * Constructor Method.
	 * @param build
	 * @param steps
	 * @param status
	 */
	public BuildInformation(Build build,int steps,Status status) {
		this.id = UUID.randomUUID().toString();
		this.majorVersion = build.getMajorVersion();
		this.minorVersion = build.getMinorVersion();
		this.releaseVersion = build.getReleaseVersion();
		this.buildNumber = build.getBuildNumber();
		this.buildSequence = build.getBuildSequence();
		this.steps = steps;
		this.upgradeDate = LocalDateTime.now();
		this.status = status;
	}
	
	/**
	 * Constructor Method.
	 * @param build
	 */
	public BuildInformation(Build build) {
		this.id = UUID.randomUUID().toString();
		this.majorVersion = build.getMajorVersion();
		this.minorVersion = build.getMinorVersion();
		this.releaseVersion = build.getReleaseVersion();
		this.buildNumber = build.getBuildNumber();
		this.buildSequence = build.getBuildSequence();
		this.upgradeDate = LocalDateTime.now();
	}
	
	/**
	 * Get Build.
	 * @return Build.
	 */
	public Build getBuild() {
		return new Build(majorVersion, minorVersion, releaseVersion, buildNumber, buildSequence);
	}
	
	/**
	 * Get id.
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get Major Version.
	 * @return int
	 */
	public int getMajorVersion() {
		return majorVersion;
	}
	
	/**
	 * Get Minot Version.
	 * @return int
	 */
	public int getMinorVersion() {
		return minorVersion;
	}
	
	/**
	 * Get Release Version.
	 * @return
	 */
	public int getReleaseVersion() {
		return releaseVersion;
	}
	
	/**
	 * Get Build Number.
	 * @return String.
	 */
	public String getBuildNumber() {
		return buildNumber;
	}
	
	/**
	 * Get Build Sequence.
	 * @return int
	 */
	public int getBuildSequence() {
		return buildSequence;
	}
	
	/**
	 * Get number of Steps.
	 * @return int 
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * Get the Build application date.
	 * @return LocalDateTime
	 */
	public LocalDateTime getUpgradeDate() {
		return upgradeDate;
	}
	
	/**
	 * Get Status
	 * @return Status
	 */
	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("majorVersion", majorVersion).append("minorVersion", minorVersion)
				.append("releaseVersion", releaseVersion).append("buildNumber", buildNumber)
				.append("buildSequence", buildSequence).append("steps", steps).append("upgradeDate",upgradeDate).append("status",status).toString();
	}
	
	/**
	 * @param  other BuildInformation to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
	 */	
	public int compareTo(final BuildInformation otherBuildInformation) {
		return new CompareToBuilder().append(majorVersion, otherBuildInformation.majorVersion).append(minorVersion, otherBuildInformation.minorVersion)
				.append(releaseVersion, otherBuildInformation.releaseVersion).append(buildNumber, otherBuildInformation.buildNumber)
				.append(buildSequence, otherBuildInformation.buildSequence).toComparison();
	}
	
	/**
	 * @param  other Build to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
	 */	
	public int compareTo(final Build build) {
		return new CompareToBuilder().append(majorVersion, build.getMajorVersion()).append(minorVersion, build.getMinorVersion())
				.append(releaseVersion, build.getReleaseVersion()).append(buildNumber, build.getBuildNumber())
				.append(buildSequence, build.getBuildSequence()).toComparison();
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
		BuildInformation other = (BuildInformation) obj;
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
	
	
	
	
	
	
	
	
	
	
	
	

}
