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

package net.minecraftforge.client.model;

import net.minecraft.client.renderer.texture.ISprite;
import net.minecraftforge.common.model.IModelState;

/**
 * Adapter from {@link IModelState} to the vanilla transform description class {@link ISprite}.
 */
public class BasicState implements ISprite
{
    private final IModelState defaultState;
    private final boolean uvLock;

    public BasicState(IModelState defaultState, boolean uvLock)
    {
        this.defaultState = defaultState;
        this.uvLock = uvLock;
    }

    @Override
    public IModelState getState()
    {
        return defaultState;
    }

    @Override
    public boolean isUvLock()
    {
        return uvLock;
    }
}