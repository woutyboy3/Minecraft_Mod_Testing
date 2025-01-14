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

package net.minecraftforge.common.model.animation;

import net.minecraftforge.common.model.TRSRTransformation;

/**
 * Various implementations of IJointClip.
 */
public final class JointClips
{
    public static enum IdentityJointClip implements IJointClip
    {
        INSTANCE;

        @Override
        public TRSRTransformation apply(float time)
        {
            return TRSRTransformation.identity();
        }
    }

    public static class NodeJointClip implements IJointClip
    {
        private final IJoint child;
        private final IClip clip;

        public NodeJointClip(IJoint joint, IClip clip)
        {
            this.child = joint;
            this.clip = clip;
        }

        @Override
        public TRSRTransformation apply(float time)
        {
            return clip.apply(child).apply(time);
        }
    }
}