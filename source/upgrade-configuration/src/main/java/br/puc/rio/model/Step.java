package br.puc.rio.model;

import java.util.Optional;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * Class that implements a Step of a Build.
 *
 */
@XStreamAlias("Step")
public class Step implements Comparable<Step> {
	
	@XStreamAsAttribute
	private int number;
	@XStreamAlias("upgrade")
	private Action upgradeAction;
	@XStreamAlias("downgrade")
	private Action downgradeAction;
	
	/**
	 * Class that implements a Step of a Build.
	 * @param number - A sequencial number of a Step.
	 * @param upgrade - upgrade action
	 * @param downgrade - downgrade action
	 */
	public Step(int number, Action upgrade, Action downgrade) {
		this.number = number;
		this.upgradeAction = upgrade;
		this.downgradeAction = downgrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
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
		Step other = (Step) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public int compareTo(final Step otherStep) {
		return new CompareToBuilder().append(number, otherStep.number).toComparison();
	}
	
	/**
	 * Get Number of Step.
	 * @return int - Number of Step
	 */
	public int getNumber() {
		return number;
	}

	public Action getUpgradeAction() {
		return upgradeAction;
	}
	
	
	public Optional<Action> getDowngradeAction() {
		return Optional.ofNullable(downgradeAction);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("number", number).toString();
	}

}
