/*
 * Minecraft Forge
 * Copyright (c) 2016-2019.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.client.event;

import net.minecraftforge.eventbus.api.Event;
import net.minecraft.client.renderer.WorldRenderer;

public class RenderWorldLastEvent extends net.minecraftforge.eventbus.api.Event
{
    private final WorldRenderer context;
    private final float partialTicks;
    public RenderWorldLastEvent(WorldRenderer context, float partialTicks)
    {
        this.context = context;
        this.partialTicks = partialTicks;
    }

    public WorldRenderer getContext()
    {
        return context;
    }

    public float getPartialTicks()
    {
        return partialTicks;
    }
}