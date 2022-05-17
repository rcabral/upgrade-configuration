package br.puc.rio.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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
		
	public BuildInformation() {
	}

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
	
	public Build getBuild() {
		return new Build(majorVersion, minorVersion, releaseVersion, buildNumber, buildSequence);
	}

	public String getId() {
		return id;
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

	public int getSteps() {
		return steps;
	}

	public LocalDateTime getUpgradeDate() {
		return upgradeDate;
	}

	public Status getStatus() {
		return status;
	}
	
	
	

}
