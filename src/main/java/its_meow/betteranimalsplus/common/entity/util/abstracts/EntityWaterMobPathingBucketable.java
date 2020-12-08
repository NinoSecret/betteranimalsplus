package its_meow.betteranimalsplus.common.entity.util.abstracts;

import dev.itsmeow.imdlib.entity.util.IBucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class EntityWaterMobPathingBucketable extends EntityWaterMobPathing implements IBucketable {

    public EntityWaterMobPathingBucketable(EntityType<? extends EntityWaterMobPathingBucketable> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.registerFromContainerKey();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        this.writeFromContainerToEntity(compound);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.readFromContainerToEntity(compound);
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return !this.isFromContainer() && despawn(distanceToClosestPlayer);
    }

    @Override
    public boolean preventDespawn() {
        return this.isFromContainer();
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        if(this.processContainerInteract(player, hand)) {
            return ActionResultType.SUCCESS;
        }
        return super.func_230254_b_(player, hand);
    }

}
