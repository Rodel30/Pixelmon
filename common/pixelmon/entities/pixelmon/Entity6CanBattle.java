package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import pixelmon.Pixelmon;
import pixelmon.battles.BattleController;
import pixelmon.battles.Moveset;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.stats.BattleStats;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity6CanBattle extends Entity5Rideable {
	public BattleStats battleStats = new BattleStats();
	public ArrayList<StatusEffectBase> status = new ArrayList<StatusEffectBase>();
	public Moveset moveset = new Moveset();
	public BattleController battleController;
	public boolean wasBattleInitiator = false;
	protected EntityTrainer trainer;

	public Entity6CanBattle(World par1World) {
		super(par1World);
		dataWatcher.addObject(15, ""); // Trainer Name
	}

	public void loadMoveset() {
		moveset = DatabaseMoves.GetInitialMoves(getName(), getLvl().getLevel());
	}

	public void StartBattle(IBattleParticipant p1, IBattleParticipant p2) {
		if (moveset.size() == 0)
			loadMoveset();

		battleController = new BattleController(p1, p2);
		wasBattleInitiator = true;
		p2.currentPokemon().battleController = battleController;
		if (p2.currentPokemon().moveset.size() == 0)
			p2.currentPokemon().loadMoveset();

		// pixelmon.isSwimming = false;
	}

	public void EndBattle() {
		// pixelmon.isSwimming = true;
		battleController = null;
	}
	
	public void setTrainer(EntityTrainer trainer){
		this.trainer = trainer;
		dataWatcher.updateObject(15, trainer.info.name);
	}
	
	public EntityTrainer getTrainer(){
		return trainer;
	}
	
	public String getTrainerName(){
		return dataWatcher.getWatchableObjectString(15);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (!worldObj.isRemote) {
			boolean flag = super.attackEntityFrom(par1DamageSource, par2);
			updateHealth();
			if (health - par2 < 0) {
				this.onDeath(par1DamageSource);
			}

			Entity entity = par1DamageSource.getEntity();
			if (getOwner() != null)
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT((EntityPixelmon) this);
			if (isValidTarget(entity)) {
				setAttackTarget((EntityLiving) entity);
				setTarget(entity);
			}
			return flag;
		}
		return false;
	}

	public void LearnMove() {
		if (moveset.size() >= 4) {
			ArrayList<Attack> attacks = getAttacksAtLevel(getLvl().getLevel());
			for (int i = 0; i < attacks.size(); i++)
				((EntityPlayerMP) getOwner()).openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), worldObj, getPokemonId(), attacks.get(i).attackIndex, 0);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (battleController != null && wasBattleInitiator)
			battleController.update();
	}

	public ArrayList<Attack> getAttacksAtLevel(int level) {
		return DatabaseMoves.getAttacksAtLevel(getName(), level);
	}

	public boolean LearnsAttackAtLevel(int level) {
		return DatabaseMoves.LearnsAttackAtLevel(getName(), level);
	}

	protected boolean isValidTarget(Entity entity) {
		return entity instanceof EntityPixelmon;
	}

	public boolean removeStatus(StatusEffectType s) {
		for (int i = 0; i < this.status.size(); i++) {
			StatusEffectBase base = this.status.get(i);
			if (base.type == s) {
				this.status.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		moveset.writeToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		moveset.readFromNBT(nbt);
	}
}
