/**
 * MrCrayfish's Furniture Mod
 * Copyright (C) 2014  MrCrayfish (http://www.mrcrayfish.com/)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mrcrayfish.furniture.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;

public class WashingMachineRenderer extends TileEntitySpecialRenderer
{
	private EntityItem armour = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_)
	{
		TileEntityWashingMachine tileEntityWashingMachine = (TileEntityWashingMachine) tileEntity;
		Block block = tileEntity.getBlockType();
		int metadata = block.getMetaFromState(tileEntity.getWorld().getBlockState(tileEntity.getPos()));

		GL11.glPushMatrix();
		GL11.glTranslatef((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F);
		GL11.glRotatef(metadata * -90F, 0, 1, 0);
		this.armour.hoverStart = 0.0F;

		for (int i = 0; i < 4; i++)
		{
			if (tileEntityWashingMachine.getStackInSlot(i) != null)
			{
				double zOffset = getOffsetZ(metadata, i);
				armour.setEntityItemStack(tileEntityWashingMachine.getStackInSlot(i));
				GL11.glRotated(tileEntityWashingMachine.progress * 5, 0, 0, 1);
				Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(armour, 0.0, -0.35D, zOffset, 0.0F, 0.0F);
			}
		}
		
		GL11.glPopMatrix();
	}

	public double getOffsetZ(int metadata, int slot)
	{
		switch (slot)
		{
		case 0:
			return -0.2;
		case 1:
			return -0.1;
		case 2:
			return 0.0;
		case 3:
			return 0.1;
		}
		return 0;
	}
}
