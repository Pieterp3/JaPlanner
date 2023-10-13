package game.combat;

import game.entity.live.LiveEntity;

public class Damage {
	
	private int amount;
	private DamageType type;
	private long time;
	private LiveEntity attacker;

	public Damage(LiveEntity attacker, int amount, DamageType type) {
		this.amount = amount;
		this.type = type;
		this.time = System.currentTimeMillis();
		this.attacker = attacker;
	}

	public int getAmount() {
		return amount;
	}

	public DamageType getType() {
		return type;
	}

	public long getTime() {
		return time;
	}

    public void setAmount(int i) {
		amount = i;
	}
	
	public LiveEntity getAttacker() {
		return attacker;
	}

}
