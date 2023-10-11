package game.entity;

import game.GameManager;
import game.definitions.Definition;
import game.entity.gameobject.ObjectManager;
import game.entity.npc.NpcManager;
import game.entity.player.PlayerManager;
import game.entity.projectile.ProjectileManager;
import game.items.banking.Bank;
import game.items.equipment.Equipment;
import game.items.inventory.Inventory;
import game.maps.location.Location;
import game.maps.movement.MovementManager;
import game.skills.SkillManager;

/**
 * TODO List:
    f. Quest system
    g. Dialogue system
    h. Shop system
    i. Market system
    
 */
public abstract class Entity {
	

    private MovementManager movement;

    private Inventory inventory;
    private Equipment equipment;
    private Bank bank;
    private Definition definition;
    private Location location;
    private SkillManager skillManager;
    private GameManager gameManager;
    private boolean spanwed;

    public Entity(GameManager gameManager, Definition definition, Location location, MovementManager movement) {
        this.definition = definition;
        this.gameManager = gameManager;
        this.skillManager = new SkillManager(this);
        this.inventory = new Inventory(this, 30);
        this.equipment = new Equipment(this, 14);
        this.bank = new Bank(this, 500);
        this.location = location;
        this.spanwed = false;
        this.movement = movement;
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public NpcManager getNpcManager() {
        return gameManager.getNpcManager();
    }

    public PlayerManager getPlayerManager() {
        return gameManager.getPlayerManager();
    }

    public ObjectManager getObjectManager() {
        return gameManager.getObjectManager();
    }

    public ProjectileManager getProjectileManager() {
        return gameManager.getProjectileManager();
    }

    public SkillManager getSkillManager() {
        return this.skillManager;
    }

    public void setMovement(MovementManager movement) {
        this.movement = movement;
    }

    public void spawn() {
        this.spanwed = true;
    }

    public void despawn() {
        this.spanwed = false;
    }

    public int getId() {
        return this.definition.getId();
    }

    public boolean isSpawned() {
        return this.spanwed;
    }

    public Location getLocation() {
        return this.location;
    }

    public Definition getDefinition() {
        return this.definition;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public Bank getBank() {
        return this.bank;
    }

    public abstract void tick();

    public MovementManager getMovement() {
        return movement;
    }

}
