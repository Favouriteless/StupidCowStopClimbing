package com.favouriteless.scsc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LandRandomPos.class)
public class LandRandomPosMixin {

	@ModifyVariable(method= {"generateRandomPosTowardDirection"}, at = @At("STORE"), ordinal = 1)
	private static BlockPos modifyBlockPos(BlockPos blockpos, PathfinderMob pMob, int pRadius, boolean pShortCircuit, BlockPos pPos) {

		MutableBlockPos pos = blockpos.mutable();
		for(int i = 0; i < pRadius; i++) {
			if(!GoalUtils.isNotStable(pMob.getNavigation(), pos))
				return pos;
			pos.move(0, -1, 0);
		}

		return blockpos;
	}


}
